package serviceproviders.navigation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helpers.DateTimeHelper;
import models.Reservation;
import models.Trip;
import models.TripDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static helpers.StringHelper.urlEncode;

public class BingMapsServiceProvider implements INavigationServiceProvider {
    // https://docs.microsoft.com/en-us/bingmaps/rest-services/routes/calculate-a-route
    private static final String BASE_URL = "http://dev.virtualearth.net/REST/v1/Routes";
    private static final String API_KEY = "AtnPnvBK7tubghxaPa5ETr4uGd3JnURD9fzIAWgqSSSIUaxuspEaMG6MlXEVVkhu";

    // optimize for time
    // do not include itinerary in result
    // do not allow the api to optimize the order of waypoints
    private static final String DEFAULT_PARAMETERS = "&optmz=time" +
            "&ra=excludeItinerary" +
            "&optWp=false" +
            "&key=" + API_KEY;

    @Override
    public List<Trip> getTripsForPickupPoint(List<Trip> trips, String pickupPoint, double delayRate) {
        return null;
    }

    @Override
    public List<Trip> getTripsForPickupPoint(List<Trip> trips, String pickupPoint) {
        return getTripsForPickupPoint(trips, pickupPoint, 10);
    }

    @Override
    public TripDetails getTripDetails(Trip trip, List<Reservation> reservations) {
        List<String> initialAddressList = getAddressList(trip, reservations);
        String waypoints = buildWaypoints(initialAddressList);

        BingMapResource mapResource = doBingMapRouteRequest(waypoints);
        if (mapResource == null) return null;

        return getTripDetails(mapResource, DateTimeHelper.fromString(trip.getArrival()));
    }

    private TripDetails getTripDetails(BingMapResource mapResource, LocalDateTime arrival) {
        LocalDateTime startTime = arrival.minusSeconds(mapResource.getTravelDuration());
        TripDetails tripDetails = new TripDetails();
        tripDetails.setEndTime(arrival);
        tripDetails.setStartTime(startTime);
        tripDetails.setDuration(mapResource.getTravelDuration());

        LinkedHashSet<String> stopAddressesSet = new LinkedHashSet<>();
        ArrayList<LocalDateTime> stopTimes = new ArrayList<>();
        stopTimes.add(startTime); // don't worry, LocalDateTime is immutable, so no need to clone :)
        int durationAccumulated = 0;
        for (BingMapResource.RouteSubLeg subLeg : mapResource.getRouteLeg().getRouteSubLegs()) {
            stopAddressesSet.add(subLeg.getStartWaypoint().getDescription());
            stopAddressesSet.add(subLeg.getEndWaypoint().getDescription());

            durationAccumulated += subLeg.getTravelDuration();
            stopTimes.add(startTime.plusSeconds(durationAccumulated));
        }

        tripDetails.setStopAdresses(new ArrayList<>(stopAddressesSet));
        tripDetails.setStopTimes(stopTimes);
        return tripDetails;
    }

    private List<String> getAddressList(Trip trip, List<Reservation> reservations) {
        // NOTE: It is important that the trip start and end address are first and last respectively.
        //       For all the elements in between, the order is irellevant, as the map api wil optimize

        // LinkedHashSet retains the order of the contents and allows only unique values
        LinkedHashSet<String> adressSet = new LinkedHashSet<>();
        adressSet.add(trip.getStartAddress().trim());

        for (Reservation reservation :
                reservations) {
            adressSet.add(reservation.getPickupAddress().trim());
            adressSet.add(reservation.getDropoffAddress().trim());
        }

        // ensure that the destination address is last
        adressSet.remove(trip.getDestinationAddress().trim());
        adressSet.add(trip.getDestinationAddress().trim());

        return new ArrayList<>(adressSet);
    }

    private String buildWaypoints(List<String> addresses) {
        StringBuilder result = new StringBuilder();
        int waypointCount = 0;

        for (String address : addresses) {
            waypointCount++;
            result.append(String.format("%s%s.%s=%s",
                    waypointCount == 1
                            ? "?"
                            : "&",
                    waypointCount == 1 || waypointCount == addresses.size()
                            ? "waypoint"
                            : "viaWaypoint",
                    waypointCount,
                    urlEncode(address)));

        }

        return result.toString();
    }

    private BingMapResource doBingMapRouteRequest(String waypoints) {
        try {
            String urlString = BASE_URL +
                    waypoints +
                    DEFAULT_PARAMETERS;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader inputReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = inputReader.readLine()) != null) {
                    response.append(inputLine);
                }

                inputReader.close();

                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(BingMapResource.class, new BingMapResourceDeserializer())
                        .create();

                return gson.fromJson(response.toString(), BingMapResource.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

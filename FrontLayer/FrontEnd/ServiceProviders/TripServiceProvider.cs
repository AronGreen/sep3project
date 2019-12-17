using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Data.Data;
using Data.Models.Entities;
using FrontEnd.Constants;
using FrontEnd.Helpers;

namespace FrontEnd.ServiceProviders
{
    public class TripServiceProvider : ITripServiceProvider
    {
        public List<Trip> GetFiltered(TripFilter filter)
        {
            var response = ApiHelpers.DoGet(Api.Trips.GetFiltered + $"" +
                             $"?driverEmail={filter.DriverEmail}" +
                             $"&passengerEmail={filter.PassengerEmail}" +
                             $"&minimumArrivalTime={filter.MinimumArrivalDate}" +
                             $"&maximumArrivalTime={filter.MaximumArrivalDate}" +
                             $"&pickupAddress={filter.PickupAddress}" +
                             $"&dropoffAddress={filter.DropoffAddress}");

            if (!response.IsSuccessStatusCode)
            {
                return null;
            }

            return JsonSerializer.Deserialize<List<Trip>>(
                response.Content.ReadAsStringAsync().GetAwaiter().GetResult());
        }

        public bool Create(Trip model, string token)
        {
            var response = ApiHelpers.DoPost(Constants.Api.Trips.Create, model, token);
            return response.IsSuccessStatusCode;
        }
    }
}

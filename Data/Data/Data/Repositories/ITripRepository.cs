using System.Collections.Generic;
using Data.Data.Entities;

namespace Data.Data.Repositories
{
    public interface ITripRepository
    {

        /// <summary>
        /// Attempt to put a Trip into the persistence model
        /// </summary>
        /// <param name="trip">The trip that should be added to the persistence model</param>
        /// <returns>Whether the Trip has been put into the persistence model</returns>
        bool CreateTrip(Trip trip);

        /// <summary>
        /// Attempt to delete a Trip from the storage
        /// </summary>
        /// <param name="tripId">the id of the Trip to delete</param>
        /// <param name="hardDelete">whether the Trip should be permanently removed, or just flagged as deleted.</param>
        /// <returns>true if the deletion was successful, false otherwise</returns>
        bool DeleteTrip(int tripId, bool hardDelete = false);

        /// <summary>
        /// Resolve a reservation. Flag it as accepted or rejected.
        /// </summary>
        /// <param name="tripId">the trip the reservation belongs to</param>
        /// <param name="reservationId">the id of the reservation</param>
        /// <param name="accept">true if the reservation should be accepted, false is it should be rejected</param>
        /// <returns></returns>
        bool ResolveReservation(int tripId, int reservationId, bool accept = true);

        /// <summary>
        /// Get the Trips that comply to a filter.
        /// </summary>
        /// <param name="filter">the filter that is used to select the Trips</param>
        /// <returns>the list of trips that comply to the filter</returns>
        IEnumerable<Trip> GetTrips(TripFilter filter = null);

        /// <summary>
        /// Retrieve one Trip from the Storage.
        /// </summary>
        /// <param name="tripId">the id of the Trip to be retrieved</param>
        /// <returns>the Trip with the specified id, or null if there was is no Trip with the specified id</returns>
        Trip GetTrip(int tripId);

    }

}
using System.Collections;
using System.Collections.Generic;
using Data.Data.Entities;

namespace Data.Data.Repositories
{
    public interface IReservationRepository
    {
        Reservation GetDummy();

        Reservation Create(Reservation reservation);

        Reservation Update(Reservation reservation);

        Reservation Delete(int id);

        Reservation GetById(int id);

        /// <summary>
        /// Get all the Reservations for a given Trip
        /// </summary>
        /// <param name="tripId"></param>
        /// <returns></returns>
        Reservation[] GetByTripId(int tripId);

        /// <summary>
        /// Get all the Reservations placed by a given user
        /// </summary>
        /// <param name="userId"></param>
        /// <returns></returns>
        Reservation[] GetByUserId(int userId);


    }
}
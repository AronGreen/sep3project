using Data.Models.Entities;

namespace Data.Repositories
{
    public interface IReservationRepository
    {

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
        /// <param name="email"></param>
        /// <returns></returns>
        Reservation[] GetByEmail(string email);

    }
}
using Data.Data.Entities;

namespace Data.Data.Repositories
{
    public class RepositoryProvider
    {

        public RepositoryProvider()
        {
            TripRepository = new TripRepository();
            ReservationRepository = new ReservationRepository();
        }

        public ITripRepository TripRepository { get; }
        public IReservationRepository ReservationRepository { get; }
    }
}
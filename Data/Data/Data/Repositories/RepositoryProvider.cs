using Data.Data.Entities;

namespace Data.Data.Repositories
{
    public class RepositoryProvider
    {

        public RepositoryProvider()
        {
            TripRepository = new TripRepository();
        }

        public ITripRepository TripRepository { get; }
    }
}
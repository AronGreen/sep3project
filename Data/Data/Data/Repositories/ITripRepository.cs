using System.Collections.Generic;
using Data.Data.Entities;

namespace Data.Data.Repositories
{
    public interface ITripRepository
    {
        Trip GetDummy();
        Trip Create(Trip trip);

        Trip Delete(int id);

        IEnumerable<Trip> GetFiltered(TripFilter filter = null);

        Trip GetById(int id);

    }

}
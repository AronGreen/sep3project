using System.Collections.Generic;
using Data.Models.Entities;

namespace Data.Data.Repositories
{
    public interface ITripRepository
    {
        Trip GetDummy();
        Trip Create(Trip trip);

        Trip Delete(int id);

        Trip[] GetFiltered(TripFilter filter = null);

        Trip GetById(int id);

    }

}
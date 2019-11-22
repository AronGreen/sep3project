using Data.Data;
using Data.Models.Entities;

namespace Data.Repositories
{
    public interface ITripRepository
    {

        Trip Create(Trip trip);

        Trip Delete(int id);

        Trip[] GetFiltered(TripFilter filter = null);

        Trip GetById(int id);

    }

}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Data.Data;
using Data.Models.Entities;

namespace FrontEnd.ServiceProviders
{
    public interface ITripServiceProvider
    {
        List<Trip> GetFiltered(TripFilter filter);

        bool Create(Trip model, string token);
    }
}

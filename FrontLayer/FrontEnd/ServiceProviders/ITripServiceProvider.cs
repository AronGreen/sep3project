using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Data.Models.Entities;

namespace FrontEnd.ServiceProviders
{
    public interface ITripServiceProvider
    {
        List<Trip> GetAll(string token);

        bool Create(Trip model, string token);
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Data.Models.Entities;
using FrontEnd.Helpers;

namespace FrontEnd.ServiceProviders
{
    public class TripServiceProvider : ITripServiceProvider
    {
        public List<Trip> GetAll(string token)
        {
            throw new NotImplementedException();
        }

        public bool Create(Trip model, string token)
        {
            var response = ApiHelpers.DoPost(Constants.Api.Trips.Create, model, token);
            return response.IsSuccessStatusCode;
        }
    }
}

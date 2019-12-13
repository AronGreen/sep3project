using System.Collections.Generic;
using System.Text.Json;
using Data.Data;
using Data.Models.Entities;
using Data.Network;
using Data.Repositories;

namespace Data.Logic.RequestTables
{
    public class TripRequestTableComposer : IRequestTableComposer
    {

        private readonly ITripRepository _tripRepository;

        public TripRequestTableComposer(ITripRepository tripRepository)
        {
            _tripRepository = tripRepository;
        }

        public void Compose(IDictionary<(string, string), Handler> map)
        {
            map.Add(("trip", "create"), CreateTrip());
            map.Add(("trip", "delete"), DeleteTrip());
            map.Add(("trip", "getFiltered"), GetFilteredTrips());
            map.Add(("trip", "getById"), GetById());
        }

        private Handler CreateTrip() => body =>
        {
            var result = _tripRepository.Create(JsonSerializer.Deserialize<Trip>(body));
            var status = result == null ? "internalError" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler DeleteTrip() => body =>
        {
            var result = _tripRepository.Delete(int.Parse(body));
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetFilteredTrips() => body =>
        {
            var result = _tripRepository.GetFiltered(JsonSerializer.Deserialize<TripFilter>(body));
            var status = result == null ? "internalError" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetById() => body =>
        {
            var result = _tripRepository.GetById(int.Parse(body));
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };
    }
}
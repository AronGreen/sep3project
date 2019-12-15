using System.Collections.Generic;
using System.Text.Json;
using Data.Models.Entities;
using Data.Network;
using Data.Repositories;

namespace Data.Logic.RequestTables
{
    public class ReservationRequestTableComposer : IRequestTableComposer
    {

        private readonly IReservationRepository _reservationRepository;

        public ReservationRequestTableComposer(IReservationRepository reservationRepository)
        {
            _reservationRepository = reservationRepository;
        }

        public void Compose(IDictionary<(string, string), Handler> map)
        {
            map.Add(("reservation", "create"), CreateReservation());
            map.Add(("reservation", "update"), UpdateReservation());
            map.Add(("reservation", "delete"), DeleteReservation());
            map.Add(("reservation", "getById"), GetById());
            map.Add(("reservation", "getByTripId"), GetByTripId());
            map.Add(("reservation", "getByEmail"), GetByEmail());
        }

        private Handler CreateReservation() => body =>
        {
            var result = _reservationRepository.Create(JsonSerializer.Deserialize<Reservation>(body));
            var status = result == null ? "badRequest" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler UpdateReservation() => body =>
        {
            var result = _reservationRepository.Update(JsonSerializer.Deserialize<Reservation>(body));
            var status = result == null ? "badRequest" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler DeleteReservation() => body =>
        {
            var result = _reservationRepository.Delete(int.Parse(body));
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetById() => body =>
        {
            var result = _reservationRepository.GetById(int.Parse(body));
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetByTripId() => body =>
        {
            var result = _reservationRepository.GetAllByTripId(int.Parse(body));
            var status = result == null ? "internalError" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetByEmail() => body =>
        {
            var result = _reservationRepository.GetByEmail(body);
            var status = result == null ? "internalError" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

    }
}
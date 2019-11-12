using System;
using System.Linq;
using System.Text.Json;
using Data.Data.Entities;
using Data.Data.Repositories;
using Data.Network;
using Microsoft.VisualBasic.CompilerServices;

namespace Data.Logic
{
    public class RequestHandler : IRequestHandler
    {

        private readonly ITripRepository _tripRepository;
        private readonly IReservationRepository _reservationRepository;

        public RequestHandler(RepositoryProvider repositoryProvider)
        {
            _tripRepository = repositoryProvider.TripRepository;
            _reservationRepository = repositoryProvider.ReservationRepository;
        }

        public Response Handle(Request req)
        {
            Console.WriteLine("Request:\n" + JsonSerializer.Serialize(req));

            // TODO I WILL DESIGN A FUCKING STATE MACHINE HERE!!!!!
            switch (req.Type)
            {
                case "trip":
                    switch (req.Operation)
                    {
                        case "getFiltered":
                            // TODO deserialize a filter from the request body
                            return new Response()
                            {
                                Status = "success",
                                Body = _tripRepository.GetFiltered().ToArray()
                            };
                        case "create":
                            return new Response()
                            {
                                Status = "success",
                                Body = _tripRepository.Create(JsonSerializer.Deserialize<Trip>(req.Body))
                            };
                    }
                    break;
                case "reservation":
                    switch (req.Operation)
                    {
                        case "getById":
                            return new Response()
                            {
                                Status = "success",
                                Body = _reservationRepository.GetById(int.Parse(req.Body))
                            };
                        case "getByTripId":
                            return new Response()
                            {
                                Status = "success",
                                Body = _reservationRepository.GetByTripId(int.Parse(req.Body))
                            };
                        case "create":
                            return new Response()
                            {
                                Status = "success",
                                Body = _reservationRepository.Create(JsonSerializer.Deserialize<Reservation>(req.Body))
                            };
                    }
                    break;
            }

            return new Response()
            {
                Status = "bad_request",
                Body = null
            };
        }
    }
}
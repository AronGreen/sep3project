using System;
using System.Collections.Generic;
using System.Text.Json;
using Data.Data.Entities;
using Data.Data.Repositories;
using Data.Network;

namespace Data.Logic
{
    public class RequestHandler : IRequestHandler
    {

        private readonly RepositoryProvider _repositoryProvider;

        public RequestHandler(RepositoryProvider repositoryProvider)
        {
            _repositoryProvider = repositoryProvider;
        }

        public Response Handle(Request req)
        {
            Console.WriteLine("Request: " + JsonSerializer.Serialize(req));

            // Return a dummy Response
            var resp = new Response()
            {
                Status = "success",
                Body = new Trip
                {
                    Id = 1,
                    Arrival = DateTime.Now,
                    BasePrice = 123,
                    CancellationFee = 12,
                    Deleted = null,
                    Description = "Big fat trip",
                    DestinationX = 213,
                    DestinationY = 543,
                    Driver = new User
                    {
                        Id = 2
                    },
                    PerKmPrice = 3,
                    Rules = new List<string>() { "NoSmoking" },
                    StartX = 43,
                    StartY = 12,
                    TotalSeats = 4
                }
            };
            Console.WriteLine("Response: " + JsonSerializer.Serialize(resp));

            return resp;
        }
    }
}
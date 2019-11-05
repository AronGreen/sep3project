using System;
using System.Linq;
using System.ComponentModel.DataAnnotations;
using Data.Data;
using Data.Data.Entities;
using Data.Logic;
using Data.Network;
using Microsoft.EntityFrameworkCore;

namespace Data
{
    
    class Program
    {
        static void Main(string[] args)

        {
            IRequestHandler requestHandler = new DummyRequestHandler();
            INetworkHandler networkHandler = new SocketHandler(requestHandler);

            using (var context = new TripContext())
            {
                context.Trips.Add(new Trip()
                {
                    Id = 1,
                    Arrival = DateTime.Now,
                    BasePrice = 10.0,
                    CancellationFee = 20.0,
                    PerKmPrice = 1.0,
                    Description = "Hello World",
                    Start = new Location(2.0, 1.0),
                    Destination = new Location(3.0, 4.0),
                    Driver = new User(),
                    TotalSeats = 3
                });
                
                context.Trips.Add(new Trip()
                {
                    Id = 2,
                    Arrival = DateTime.Now,
                    BasePrice = 10.0,
                    CancellationFee = 20.0,
                    PerKmPrice = 1.0,
                    Description = "Hello World",
                    Start = new Location(2.0, 1.0),
                    Destination = new Location(3.0, 4.0),
                    Driver = new User(),
                    TotalSeats = 3
                });
                
                context.Trips.Add(new Trip()
                {
                    Id = 3,
                    Arrival = DateTime.Now,
                    BasePrice = 10.0,
                    CancellationFee = 20.0,
                    PerKmPrice = 1.0,
                    Description = "Hello World",
                    Start = new Location(2.0, 1.0),
                    Destination = new Location(3.0, 4.0),
                    Driver = new User(),
                    TotalSeats = 3
                });

                context.SaveChanges();
            }

            using (var context = new TripContext())
            {
                var query = from t in context.Trips select t;

                Console.WriteLine();
                foreach (Trip trip in query.ToList())
                {
                    Console.WriteLine(trip.Id + " " + trip.Description);
                }
            }
        }
    }
    
}

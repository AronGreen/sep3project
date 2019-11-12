using System;
using System.Linq;
using Data.Data;
using Data.Data.Entities;
using Data.Data.Repositories;
using Data.Logic;
using Data.Network;

namespace Data
{
    
    class Program
    {
        static void Main(string[] args)
        {
            var repositoryProvider = new RepositoryProvider();
            IRequestHandler requestHandler = new RequestHandler(repositoryProvider);
            INetworkHandler networkHandler = new SocketHandler(requestHandler);

            var rep = repositoryProvider.TripRepository;

            rep.CreateTrip(new Trip()
            {
                Id = 1,
                Arrival = DateTime.Now,
                BasePrice = 1,
                CancellationFee = 1,
                Deleted = null,
                Description = "Hello World",
                DestinationX = 5,
                DestinationY = 2,
                Driver = new User(),
                PerKmPrice = 1
            });

            var trip = rep.GetTrips().ToList()[0];
            Console.WriteLine(trip.Description);
        }

    }
    
}

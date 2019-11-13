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

            var repo = new TripRepository();
            repo.Create(new Trip()
            {
                Id = 1,
                DestinationAddress = "Somewhere over the rainbow"
            });

            Console.WriteLine(repo.GetById(1).DestinationAddress);
        }

    }
    
}

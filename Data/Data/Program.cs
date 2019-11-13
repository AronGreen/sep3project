using System;
using System.Linq;
using Data.Data;
using Data.Data.Contexts;
using Data.Data.Repositories;
using Data.Logic;
using Data.Network;

namespace Data
{
    
    class Program
    {
        static void Main(string[] args)
        {
//            new ApplicationContext().Database.EnsureCreated();
            IRequestHandler requestHandler = new RequestHandler();
            INetworkHandler networkHandler = new SocketHandler(requestHandler);

//            var repo = new TripRepository();
//            repo.Create(new Trip()
//            {
//                DestinationAddress = "Somewhere over the rainbow",
//                Arrival = DateTime.Now,
//                BasePrice = 15.0,
//                PerKmPrice = 2.0,
//                CancellationFee = 20.0,
//                Description = "This is my special trip for ya",
//                Driver = new User()
//                {
//                    Name = "Miss Driver"
//                },
//                Rules = "No smoking no gangrape",
//                StartAddress = "Somewhere under the rainbow"
//            });
        }

    }
    
}

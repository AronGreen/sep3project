using System;
using System.Linq;
using Data.Composer;
using Data.Data;
using Data.Logic;
using Data.Network;
using Data.Repositories;
using Microsoft.Extensions.DependencyInjection;

namespace Data
{
    
    class Program
    {
        static void Main(string[] args)
        {
            new ApplicationContext().Database.EnsureCreated();

            var composition = new Composition();

            var networkHandler = composition.Get<INetworkHandler>();

            networkHandler.Start();
        }

    }
    
}

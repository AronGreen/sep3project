using System;
using System.Linq;
using System.ComponentModel.DataAnnotations;
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
        }
    }
    
}

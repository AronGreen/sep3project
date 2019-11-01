﻿using System;
using System.Text.Json;
using System.Threading.Tasks;
using Data.Network;

namespace Data.Logic
{
    public class DummyRequestHandler
    {
        public DummyRequestHandler()
        {
            // The real handler will have a IRepository interface injected into it
            // That's one of the reasons why we need this class. The other one is because I can't have a
            // delegate defined in a namespace...
        }

        // Represents the overall logic in the data server.
        // Also the method returned is supposed to route the request to data queries and validation
        // For now it's a dummy method
        public RequestHandler Handler
        {
            get
            {
                // The actual method for returning the Response
                return req =>
                {
                    Console.WriteLine("Request: " + JsonSerializer.Serialize(req));
                    
                    return new Response()
                    {
                        Status = "success",
                        Body = "{" +
                               "    msg: Hello World" +
                               "}"
                    };
                };
            }
        }
    }
}
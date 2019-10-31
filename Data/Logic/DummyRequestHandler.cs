using System;
using System.Text.Json;
using Data.Network;

namespace Data.Logic
{

    public class DummyRequestHandler
    {

        public DummyRequestHandler()
        {
            Handler = (req, res) =>
            {
                Console.WriteLine("Request: " + JsonSerializer.Serialize(req));
                res(new Response()
                {
                    Status = "success",
                    Body = "{" +
                           "    msg: Hello World" +
                           "}"
                });
            };
        }

        public RequestHandler Handler { get; } 

    }

}
using System;
using System.Text.Json;
using System.Threading.Tasks;
using Data.Data.Entities;
using Data.Network;

namespace Data.Logic
{
    public class DummyRequestHandler : IRequestHandler
    {
        public DummyRequestHandler()
        {
            // The real handler will have a IRepository interface injected into it
        }

        public Response Handle(Request req)
        {
            Console.WriteLine("Request: " + JsonSerializer.Serialize(req));
            
            // Return a dummy Response
            return new Response()
            {
                // The actual method for returning the Response
                return req =>
                {
                    Console.WriteLine("Request: " + JsonSerializer.Serialize(req));
                    
                    var response = new Response()
                    {
                        Status = "success",
                        Body = new Trip{Id =  0, Date = "Today", Title = "Big fat title"}
                    };
                    Console.WriteLine("Response: " + JsonSerializer.Serialize(response));
                    return response;
                };
            }
        }
    }
}
using System;
using System.Text.Json;
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
                Body = new Trip {Id = 1, Date = "Today", Title = "Big fat title"}
            };
            Console.WriteLine("Response: " + JsonSerializer.Serialize(resp));

            return resp;
        }
    }
}
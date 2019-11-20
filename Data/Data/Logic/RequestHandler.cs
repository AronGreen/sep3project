using System;
using System.Linq;
using System.Collections.Generic;
using System.Text.Json;
using Data.Data.Repositories;
using Data.Models.Entities;
using Data.Network;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Microsoft.VisualBasic.CompilerServices;

namespace Data.Logic
{
    public class RequestHandler : IRequestHandler
    {

        private readonly IRequestTable requestTable;

        public RequestHandler(IRequestTable requestTable)
        {
            this.requestTable = requestTable;
        }

        public Response Handle(Request req)
        {
            Console.WriteLine("Request:\n" + JsonSerializer.Serialize(req) + "\n---------------------------------------------\n");

            var response = requestTable.GetEntry(req.Type, req.Operation)(req.Body);

            Console.WriteLine("Response:\n" + JsonSerializer.Serialize(response) + "\n---------------------------------------------\n");

            return response;
        }
    }
}
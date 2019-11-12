using System;
using Data.Data.Contexts;
using Data.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Data.Repositories
{
    public class TripContext : BaseContext
    {

        public DbSet<Trip> Trips { get; set; }

    }
}
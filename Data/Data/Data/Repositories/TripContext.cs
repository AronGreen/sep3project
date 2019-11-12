using System;
using Data.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Data.Repositories
{
    public class TripContext : DbContext
    {

        public DbSet<Trip> Trips { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseInMemoryDatabase("db");

            base.OnConfiguring(optionsBuilder);
        }
        
    }
}
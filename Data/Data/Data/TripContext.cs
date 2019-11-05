using Data.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Data
{
    public class TripContext : DbContext
    {
        public TripContext()
        {

        }

        public DbSet<Trip> Trips { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseInMemoryDatabase("db");
            
            base.OnConfiguring(optionsBuilder);
        }
        
    }
}
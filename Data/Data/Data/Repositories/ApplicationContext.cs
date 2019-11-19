using Data.Models.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Data.Contexts
{
    public class ApplicationContext : DbContext
    {

        public DbSet<Trip> Trips { get; set; }
        public DbSet<Reservation> Reservations { get; set; }
        public DbSet<Account> Accounts { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder builder)
        {
            builder.UseSqlite("Data Source = database.db");

            base.OnConfiguring(builder);
        }

    }
}
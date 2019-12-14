using Data.Models.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Repositories
{
    public class ApplicationContext : DbContext
    {

        public DbSet<Trip> Trips { get; set; }
        public DbSet<Reservation> Reservations { get; set; }
        public DbSet<Account> Accounts { get; set; }
        public DbSet<Invoice> Invoices { get; set; }
        public DbSet<Review> Reviews { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder builder)
        {
            builder.UseSqlite("Data Source = database.db");

            base.OnConfiguring(builder);
        }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);

            builder.Entity<Reservation>()
                .HasOne<Trip>("Trip")
                .WithMany()
                .HasForeignKey("TripId")
                .OnDelete(DeleteBehavior.SetNull);

            builder.Entity<Trip>()
                .HasOne<Account>("Driver")
                .WithMany()
                .HasForeignKey("DriverEmail")
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<Reservation>()
                .HasOne<Account>("Passenger")
                .WithMany()
                .HasForeignKey("PassengerEmail")
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<Review>()
                .HasOne<Account>()
                .WithMany()
                .HasForeignKey("ReviewerEmail")
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<Review>()
                .HasOne<Account>()
                .WithMany()
                .HasForeignKey("RevieweeEmail")
                .OnDelete(DeleteBehavior.Cascade);



        }

    }
}
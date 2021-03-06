﻿using Data.Models.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Repositories
{
    public class ApplicationContext : DbContext
    {

        public DbSet<Trip> Trips { get; set; }
        public DbSet<Reservation> Reservations { get; set; }
        public DbSet<Account> Accounts { get; set; }
        public DbSet<Invoice> Invoices { get; set; }
        public DbSet<Notification> Notifications { get; set; }
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
                .HasOne<Trip>()
                .WithMany()
                .HasForeignKey("TripId")
                .OnDelete(DeleteBehavior.SetNull);
            builder.Entity<Reservation>()
                .HasOne<Account>()
                .WithMany()
                .HasForeignKey("PassengerEmail")
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<Trip>()
                .HasOne<Account>()
                .WithMany()
                .HasForeignKey("DriverEmail")
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<Notification>()
                .HasOne<Account>()
                .WithMany()
                .HasForeignKey("AccountEmail")
                .OnDelete(DeleteBehavior.Cascade);

            builder.Entity<Invoice>()
                .HasOne<Trip>()
                .WithMany()
                .HasForeignKey("TripId")
                .OnDelete(DeleteBehavior.SetNull);
            builder.Entity<Invoice>()
                .HasOne<Reservation>()
                .WithMany()
                .HasForeignKey("ReservationId")
                .OnDelete(DeleteBehavior.SetNull);

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
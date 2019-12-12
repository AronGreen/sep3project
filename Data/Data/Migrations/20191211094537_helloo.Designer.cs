﻿// <auto-generated />
using Data.Repositories;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace Data.Migrations
{
    [DbContext(typeof(ApplicationContext))]
    [Migration("20191211094537_helloo")]
    partial class helloo
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "3.0.0");

            modelBuilder.Entity("Data.Models.Entities.Account", b =>
                {
                    b.Property<string>("Email")
                        .HasColumnType("TEXT");

                    b.Property<int>("AccessLevel")
                        .HasColumnType("INTEGER");

                    b.Property<string>("DateOfBirth")
                        .HasColumnType("TEXT");

                    b.Property<string>("FirstName")
                        .HasColumnType("TEXT");

                    b.Property<string>("LastName")
                        .HasColumnType("TEXT");

                    b.Property<string>("Password")
                        .HasColumnType("TEXT");

                    b.Property<string>("PasswordSalt")
                        .HasColumnType("TEXT");

                    b.Property<string>("Phone")
                        .HasColumnType("TEXT");

                    b.HasKey("Email");

                    b.ToTable("Accounts");
                });

            modelBuilder.Entity("Data.Models.Entities.Reservation", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("DropoffAddress")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<string>("PassengerEmail")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<string>("PickupAddress")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<string>("PickupTime")
                        .HasColumnType("TEXT");

                    b.Property<string>("State")
                        .HasColumnType("TEXT");

                    b.Property<int>("TripId")
                        .HasColumnType("INTEGER");

                    b.HasKey("Id");

                    b.HasIndex("PassengerEmail");

                    b.HasIndex("TripId");

                    b.ToTable("Reservations");
                });

            modelBuilder.Entity("Data.Models.Entities.Trip", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("Arrival")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<double>("BasePrice")
                        .HasColumnType("REAL");

                    b.Property<double>("CancellationFee")
                        .HasColumnType("REAL");

                    b.Property<string>("Description")
                        .HasColumnType("TEXT");

                    b.Property<string>("DestinationAddress")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<string>("DriverEmail")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<double>("PerKmPrice")
                        .HasColumnType("REAL");

                    b.Property<string>("Rules")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<string>("StartAddress")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<int>("TotalSeats")
                        .HasColumnType("INTEGER");

                    b.HasKey("Id");

                    b.HasIndex("DriverEmail");

                    b.ToTable("Trips");
                });

            modelBuilder.Entity("Data.Models.Entities.Reservation", b =>
                {
                    b.HasOne("Data.Models.Entities.Account", "Passenger")
                        .WithMany()
                        .HasForeignKey("PassengerEmail")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Data.Models.Entities.Trip", "Trip")
                        .WithMany()
                        .HasForeignKey("TripId")
                        .OnDelete(DeleteBehavior.SetNull)
                        .IsRequired();
                });

            modelBuilder.Entity("Data.Models.Entities.Trip", b =>
                {
                    b.HasOne("Data.Models.Entities.Account", "Driver")
                        .WithMany()
                        .HasForeignKey("DriverEmail")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });
#pragma warning restore 612, 618
        }
    }
}
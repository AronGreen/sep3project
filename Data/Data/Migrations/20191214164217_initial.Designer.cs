﻿// <auto-generated />
using System;
using Data.Repositories;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace Data.Migrations
{
    [DbContext(typeof(ApplicationContext))]
    [Migration("20191214164217_initial")]
    partial class initial
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

            modelBuilder.Entity("Data.Models.Entities.Invoice", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<double>("Amount")
                        .HasColumnType("REAL");

                    b.Property<string>("PayeeEmail")
                        .HasColumnType("TEXT");

                    b.Property<string>("PayerEmail")
                        .HasColumnType("TEXT");

                    b.Property<int?>("ReservationId")
                        .HasColumnType("INTEGER");

                    b.Property<string>("State")
                        .IsRequired()
                        .HasColumnType("TEXT");

                    b.Property<int?>("TripId")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Type")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.HasIndex("ReservationId");

                    b.HasIndex("TripId");

                    b.ToTable("Invoices");
                });

            modelBuilder.Entity("Data.Models.Entities.Notification", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<string>("AccountEmail")
                        .HasColumnType("TEXT");

                    b.Property<string>("Date")
                        .HasColumnType("TEXT");

                    b.Property<int>("ItemId")
                        .HasColumnType("INTEGER");

                    b.Property<string>("Message")
                        .HasColumnType("TEXT");

                    b.Property<string>("Type")
                        .HasColumnType("TEXT");

                    b.HasKey("Id");

                    b.HasIndex("AccountEmail");

                    b.ToTable("Notifications");
                });

            modelBuilder.Entity("Data.Models.Entities.Reservation", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("INTEGER");

                    b.Property<int>("BookedSeats")
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

                    b.Property<int?>("TripId")
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

            modelBuilder.Entity("Data.Models.Entities.Invoice", b =>
                {
                    b.HasOne("Data.Models.Entities.Reservation", null)
                        .WithMany()
                        .HasForeignKey("ReservationId")
                        .OnDelete(DeleteBehavior.SetNull);

                    b.HasOne("Data.Models.Entities.Trip", null)
                        .WithMany()
                        .HasForeignKey("TripId")
                        .OnDelete(DeleteBehavior.SetNull);
                });

            modelBuilder.Entity("Data.Models.Entities.Notification", b =>
                {
                    b.HasOne("Data.Models.Entities.Account", null)
                        .WithMany()
                        .HasForeignKey("AccountEmail")
                        .OnDelete(DeleteBehavior.Cascade);
                });

            modelBuilder.Entity("Data.Models.Entities.Reservation", b =>
                {
                    b.HasOne("Data.Models.Entities.Account", null)
                        .WithMany()
                        .HasForeignKey("PassengerEmail")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Data.Models.Entities.Trip", null)
                        .WithMany()
                        .HasForeignKey("TripId")
                        .OnDelete(DeleteBehavior.SetNull);
                });

            modelBuilder.Entity("Data.Models.Entities.Trip", b =>
                {
                    b.HasOne("Data.Models.Entities.Account", null)
                        .WithMany()
                        .HasForeignKey("DriverEmail")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });
#pragma warning restore 612, 618
        }
    }
}

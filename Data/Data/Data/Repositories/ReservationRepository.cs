using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using Data.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Data.Repositories
{
    public class ReservationRepository : IReservationRepository
    {

        public ReservationRepository()
        {
            _context = new ReservationContext();
        }

        private readonly ReservationContext _context;

        public Reservation GetDummy()
        {
            return new Reservation()
            {
                Approved = null,
                DropoffAddress = "Some address",
                Passenger = new User()
                {
                    Name = "Passenger name"
                },
                PickupAddress = "Some address",
                PickupTime = null,
                Trip = null
            };
        }

        public Reservation Create(Reservation reservation)
        {
            // Add the Reservation to the database
            var r = _context.Reservations.Add(reservation).Entity;
            _context.SaveChanges();

            return r;
        }

        public Reservation Update(Reservation reservation)
        {
            // Update the Reservation and retrieve the new entity
            var r = _context.Reservations.Update(reservation).Entity;
            _context.SaveChanges();

            return r;
        }

        public Reservation Delete(int id)
        {
            // Get the Reservation from the database
            var r = _context.Reservations.Single(x => x.Id == id);
            
            // Delete the Reservation
            _context.Reservations.Remove(r);
            _context.SaveChanges();

            return r;
        }

        public Reservation GetById(int id)
        {
            // Select the only Reservation with the specified id
            var r = _context.Reservations.Single(x => x.Id == id);

            return r;
        }

        public IEnumerable<Reservation> GetByTripId(int tripId)
        {
            // Select the Reservations that have the Trip with the specified Id
            var r = _context.Reservations.Select(x => x)
                .Where(x => x.Trip.Id == tripId);

            return r;
        }

        public IEnumerable<Reservation> GetByUserId(int userId)
        {
            // Select the Reservations the passenger of which has the specified Id
            var r = _context.Reservations.Select(x => x)
                .Where(x => x.Passenger.Id == userId);

            return r;
        }
    }
}
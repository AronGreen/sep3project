using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using Data.Data.Contexts;
using Data.Models.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Data.Repositories
{
    public class ReservationRepository : IReservationRepository
    {

        private readonly ApplicationContext _context;

        public ReservationRepository(ApplicationContext context)
        {
            _context = context;
        }

        public Reservation Create(Reservation reservation)
        {
            // Add the Reservation to the database (result should be the same the entity passed in)
            var result = _context.Reservations.Add(reservation).Entity;
            _context.SaveChanges();

            return result;
        }

        public Reservation Update(Reservation reservation)
        {
            // Update the Reservation (result should be the same as the one passed in)
            var result = _context.Reservations.Update(reservation).Entity;
            _context.SaveChanges();

            return result;
        }

        public Reservation Delete(int id)
        {
            // Get the Reservation from the database
            var result = _context.Reservations.Single(x => x.Id == id);
            
            // Delete the Reservation
            _context.Reservations.Remove(result);
            _context.SaveChanges();

            return result;
        }

        public Reservation GetById(int id)
        {
            return _context.Reservations
                .Single(x => x.Id == id);
        }

        public Reservation[] GetByTripId(int tripId)
        {
            return _context.Reservations
                .Select(x => x)
                .Where(x => x.TripId == tripId)
                .ToArray();
        }

        public Reservation[] GetByEmail(string email)
        {
            return _context.Reservations
                .Select(x => x)
                .Where(x => x.Passenger.Email == email)
                .ToArray();
        }
    }
}
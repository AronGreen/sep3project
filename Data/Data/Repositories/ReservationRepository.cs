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
            try
            {
                // Add the Reservation to the database (result should be the same the entity passed in)
                var result = _context.Reservations.Add(reservation).Entity;
                _context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
            
        }

        public Reservation Update(Reservation reservation)
        {
            try
            {
                // Update the Reservation (result should be the same as the one passed in)
                var result = _context.Reservations.Update(reservation).Entity;
                _context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Reservation Delete(int id)
        {
            try
            {
                // Get the Reservation from the database
                var result = _context.Reservations.Single(x => x.Id == id);
            
                // Delete the Reservation
                _context.Reservations.Remove(result);
                _context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Reservation GetById(int id)
        {
            try
            {
                return _context.Reservations
                    .Single(x => x.Id == id);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
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
using System;
using System.Linq;
using Data.Models.Entities;

namespace Data.Repositories
{
    public class ReservationRepository : IReservationRepository
    {

        public Reservation Create(Reservation reservation)
        {
            using var context = new ApplicationContext();
            try
            {
                // Add the Reservation to the database (result should be the same the entity passed in)
                var result = context.Reservations.Add(reservation).Entity;
                context.SaveChanges();

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
            using var context = new ApplicationContext();
            try
            {
                // Update the Reservation (result should be the same as the one passed in)
                var result = context.Reservations.Update(reservation).Entity;
                context.SaveChanges();

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
            using var context = new ApplicationContext();
            try
            {
                // Get the Reservation from the database
                var result = context.Reservations.Single(x => x.Id == id);
            
                // Delete the Reservation
                context.Reservations.Remove(result);
                context.SaveChanges();

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
            using var context = new ApplicationContext();
            try
            {
                return context.Reservations
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
            using var context = new ApplicationContext();
            return context.Reservations
                .Select(x => x)
                .Where(x => x.TripId == tripId)
                .ToArray();
        }

        public Reservation[] GetByEmail(string email)
        {
            using var context = new ApplicationContext();
            return context.Reservations
                .Select(x => x)
                .Where(x => x.Passenger.Email == email)
                .ToArray();
        }
    }
}
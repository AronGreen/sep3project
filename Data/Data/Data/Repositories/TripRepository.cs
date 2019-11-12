using System;
using System.Collections.Generic;
using System.Linq;
using Data.Data.Entities;

namespace Data.Data.Repositories
{
    public class TripRepository : ITripRepository
    {

        private readonly TripContext _context;
        
        /// <summary>
        /// Creates a repository for accessing an Entity Framework database
        /// </summary>
        public TripRepository()
        {
            _context = new TripContext();
        }
        
        public bool CreateTrip(Trip trip)
        {
            try
            {
                _context.Trips.Add(trip);
                _context.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool DeleteTrip(int tripId, bool hardDelete = false)
        {
            try
            {
                // get trip from the db
                var trip = _context.Trips.Select(x => x).Single(x => x.Id == tripId);

                // return false if trip doesn't exist
                if (trip == null)
                    return false;

                if (hardDelete)
                {
                     //hard delete the trip
                    _context.Trips.Remove(trip);
                }
                else
                {
                    // flag the trip as deleted (soft delete)
                    trip.Deleted = DateTime.Now;
                    _context.Trips.Update(trip);
                }

                _context.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                // shit's gone wrong
                Console.WriteLine(e);
                return false;
            }
        }

        public bool ResolveReservation(int tripId, int reservationId, bool accept = true)
        {
            throw new System.NotImplementedException();
        }

        public IEnumerable<Trip> GetTrips(TripFilter filter = null)
        {
            // TODO for now returns all the trips
            
            var trips = _context.Trips.Select(trip => trip);
            return trips;
        }

        public Trip GetTrip(int tripId)
        {
            var trip = _context.Trips.Single(x => x.Id == tripId);
            return trip;
        }
    }
}
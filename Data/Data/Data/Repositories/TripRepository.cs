 using System;
using System.Collections.Generic;
using System.Linq;
 using Data.Data.Contexts;
 using Data.Models.Entities;

 namespace Data.Data.Repositories
{
    public class TripRepository : ITripRepository
    {

        private readonly ApplicationContext _context;
        
        /// <summary>
        /// Creates a repository for accessing an Entity Framework database
        /// </summary>
        public TripRepository()
        {
            _context = new ApplicationContext();
        }

        public Trip GetDummy()
        {
            return new Trip()
            {
                Arrival = DateTime.Now,
                BasePrice = 1,
                CancellationFee = 2,
                Description = "Dummy Trip",
                DestinationAddress = "Some address",
                Driver = new Account()
                {
                    Name = "Some Driver"
                }
            };
        }

        public Trip Create(Trip trip)
        {
            // Add the Trip to the database
            var t = _context.Trips.Add(trip).Entity;
            _context.SaveChanges();

            return t;
        }

        public Trip Delete(int id)
        {
            // Get the Trip from the database
            var t = _context.Trips.Single(x => x.Id == id);

            // Delete the Trip
            _context.Trips.Remove(t);
            _context.SaveChanges();

            return t;
        }

        public Trip[] GetFiltered(TripFilter filter = null)
        {
            // TODO for now returns all the trips
            
            var t = _context.Trips.Select(x => x);
            return t.ToArray();
        }

        public Trip GetById(int id)
        {
            var t = _context.Trips.Single(x => x.Id == id);
            return t;
        }
    }
}
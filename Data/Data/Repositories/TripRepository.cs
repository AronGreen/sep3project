 using System;
using System.Collections.Generic;
using System.Linq;
 using System.Security.Cryptography.X509Certificates;
 using Data.Data.Contexts;
 using Data.Models.Entities;
 using Microsoft.EntityFrameworkCore;

 namespace Data.Data.Repositories
{
    public class TripRepository : ITripRepository
    {

        private readonly ApplicationContext _context;
        
        /// <summary>
        /// Creates a repository for accessing an Entity Framework database
        /// </summary>
        public TripRepository(ApplicationContext context)
        {
            _context = context;
        }

        public Trip Create(Trip trip)
        {
            try
            {
                // Add the Trip to the database
                var result = _context.Trips.Add(trip).Entity;
                _context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Trip Delete(int id)
        {
            try
            {
                // Get the Trip from the database
                var result = _context.Trips.Single(x => x.Id == id);

                // Delete the Trip
                _context.Trips.Remove(result);
                _context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Trip[] GetFiltered(TripFilter filter = null)
        {
            // TODO add filtering

            return _context.Trips
                .Select(x => x)
                .ToArray();
        }

        public Trip GetById(int id)
        {
            try
            {
                var result = _context.Trips.Single(x => x.Id == id);
                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }
    }
}
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

        public Trip Create(Trip trip)
        {
            using var context = new ApplicationContext();
            try
            {
                // Add the Trip to the database
                var result = context.Trips.Add(trip).Entity;
                context.SaveChanges();

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
            using var context = new ApplicationContext();
            try
            {
                // Get the Trip from the database
                var result = context.Trips.Single(x => x.Id == id);

                // Delete the Trip
                context.Trips.Remove(result);
                context.SaveChanges();

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
            using var context = new ApplicationContext();
            // TODO add filtering

            return context.Trips
                .Select(x => x)
                .ToArray();
        }

        public Trip GetById(int id)
        {
            using var context = new ApplicationContext();
            try
            {
                var result = context.Trips.Single(x => x.Id == id);
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
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using Data.Data;
using Data.Models.Entities;
using Data.Models.Helpers;
using Microsoft.EntityFrameworkCore.ValueGeneration;

namespace Data.Repositories
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

            return context.Trips
                .Select(x => x)
                .AsEnumerable()
                .Where(x =>
                {
                    if (filter == null)
                        return true;

                    // Driver email filter
                    if (filter.DriverEmail != "" && filter.DriverEmail != x.DriverEmail)
                    {
                        return false;
                    }

                    // Passenger email filter
                    var reservations = context.Reservations
                        .Select(r => r)
                        .Where(r => r.TripId == x.Id)
                        .ToArray();
                    if (filter.PassengerEmail != "" && reservations.All(r => r.PassengerEmail != filter.PassengerEmail))
                    {
                        return false;
                    }

                    // Minimum Date filter
                    if (filter.MinimumArrivalDate != "")
                    {
                        var filterDate = DateTimeHelper.FromString(filter.MinimumArrivalDate);
                        var tripDate = DateTimeHelper.FromString(x.Arrival);
                        if (tripDate.CompareTo(filterDate) < 0)
                            return false;
                    }

                    // Maximum date filter
                    if (filter.MaximumArrivalDate != "")
                    {
                        var filterDate = DateTimeHelper.FromString(filter.MaximumArrivalDate);
                        var tripDate = DateTimeHelper.FromString(x.Arrival);
                        if (tripDate.CompareTo(filterDate) > 0)
                            return false;
                    }
                        
                    return true;
                })
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
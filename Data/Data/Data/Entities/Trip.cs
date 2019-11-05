using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Diagnostics.CodeAnalysis;

namespace Data.Data.Entities
{
    public class Trip
    {

        public Trip()
        {
            Rules = new List<string>();
        }

        /// <summary>
        /// The primary key value for the trip
        /// </summary>
        [Key]
        public int Id { get; set; }

        /// <summary>
        /// The user account of the driver that will drive during the trip.
        /// </summary>
        [NotNull] public User Driver { get; set; }

        /// <summary>
        /// The date and arrival time for the trip (Pickup times are calculated based on this).
        /// </summary>
        [NotNull]
        public DateTime Arrival { get; set; }

        /// <summary>
        /// The starting point of the trip. Where the driver leaves from.
        /// </summary>
        [NotNull]
        public Location Start { get; set; }

        /// <summary>
        /// The destination of the trip, where the driver is heading.
        /// </summary>
        [NotNull]
        public Location Destination { get; set; }

        /// <summary>
        /// The price that every passenger has to pay regardless of how long they are travelling.
        /// </summary>
        [NotNull]
        public double BasePrice { get; set; }
        
        /// <summary>
        /// The price passengers pay for every kilometer they travel with the driver.
        /// </summary>
        [NotNull]
        public double PerKmPrice { get; set; }
        
        /// <summary>
        /// The fee a passenger has to pay if they cancel the trip less than 24 hours before the ride.
        /// </summary>
        [NotNull]
        public double CancellationFee { get; set; }
        
        /// <summary>
        /// The set of rules the driver expects the passengers to adhere to while they are travelling together.
        /// </summary>
        public IEnumerable<string> Rules { get; }
        
        /// <summary>
        /// The total amount of seats for rent.
        /// </summary>
        [NotNull]
        public int TotalSeats { get; set; }
        
        /// <summary>
        /// An optional description of the trip. Can contain useful information.
        /// </summary>
        public string Description { get; set; }
    }
}

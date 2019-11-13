using System;
using System.Collections.Generic;
using System.Text;
using System.Text.Json.Serialization;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Diagnostics.CodeAnalysis;

namespace Data.Data.Entities
{
    public class Trip
    {

        /// <summary>
        /// The primary key value for the trip
        /// </summary>
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        /// <summary>
        /// The user account of the driver that will drive during the trip.
        /// </summary>
        public User Driver { get; set; }

        /// <summary>
        /// The date and arrival time for the trip (Pickup times are calculated based on this).
        /// </summary>
        public DateTime Arrival { get; set; }

        /// <summary>
        /// The starting address of the Trip
        /// </summary>
        public string StartAddress { get; set; }

        /// <summary>
        /// The destination address of the Trip
        /// </summary>
        public string DestinationAddress { get; set; }

        /// <summary>
        /// The price that every passenger has to pay regardless of how long they are travelling.
        /// </summary>
        public double BasePrice { get; set; }
        
        /// <summary>
        /// The price passengers pay for every kilometer they travel with the driver.
        /// </summary>
        public double PerKmPrice { get; set; }
        
        /// <summary>
        /// The fee a passenger has to pay if they cancel the trip less than 24 hours before the ride.
        /// </summary>
        public double CancellationFee { get; set; }
        
        /// <summary>
        /// The set of rules the driver expects the passengers to adhere to while they are travelling together.
        /// </summary>
        public string[] Rules { get; set; }
        
        /// <summary>
        /// The total amount of seats for rent.
        /// </summary>
        public int TotalSeats { get; set; }
        
        /// <summary>
        /// An optional description of the trip. Can contain useful information.
        /// </summary>
        public string Description { get; set; }

    }
}

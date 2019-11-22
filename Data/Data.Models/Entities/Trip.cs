using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace Data.Models.Entities
{
    public class Trip
    {

        /// <summary>
        /// The primary key value for the trip
        /// </summary>
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [JsonPropertyName("id")]
        public int Id { get; set; }

        /// <summary>
        /// The id of the driver user
        /// </summary>
        [JsonPropertyName("driverEmail")]
        [Required]
        public string DriverEmail { get; set; }

        /// <summary>
        /// The user account of the driver that will drive during the trip.
        /// </summary>
        [JsonPropertyName("driver")]
        public Account Driver { get; set; }

        /// <summary>
        /// The date and arrival time for the trip (Pickup times are calculated based on this).
        /// </summary>
        [JsonPropertyName("arrival")]
        [Required]
        public string Arrival { get; set; }

        /// <summary>
        /// The starting address of the Trip
        /// </summary>
        [JsonPropertyName("startAddress")]
        [Required]
        public string StartAddress { get; set; }

        /// <summary>
        /// The destination address of the Trip
        /// </summary>
        [JsonPropertyName("destinationAddress")]
        [Required]
        public string DestinationAddress { get; set; }

        /// <summary>
        /// The price that every passenger has to pay regardless of how long they are travelling.
        /// </summary>
        [JsonPropertyName("basePrice")]
        [Required]
        public double BasePrice { get; set; }

        /// <summary>
        /// The price passengers pay for every kilometer they travel with the driver.
        /// </summary>
        [JsonPropertyName("perKmPrice")]
        [Required]
        public double PerKmPrice { get; set; }

        /// <summary>
        /// The fee a passenger has to pay if they cancel the trip less than 24 hours before the ride.
        /// </summary>
        [JsonPropertyName("cancellationFee")]
        [Required]
        public double CancellationFee { get; set; }

        /// <summary>
        /// The set of rules the driver expects the passengers to adhere to while they are travelling together.
        /// </summary>
        [JsonPropertyName("rules")]
        [Required]
        public string Rules { get; set; }

        /// <summary>
        /// The total amount of seats for rent.
        /// </summary>
        [JsonPropertyName("totalSeats")]
        [Required]
        public int TotalSeats { get; set; }

        /// <summary>
        /// An optional description of the trip. Can contain useful information.
        /// </summary>
        [JsonPropertyName("description")]
        public string Description { get; set; }

//        /// <summary>
//        /// The list of passengers for the trip
//        /// </summary>
//        public ICollection<Account> Passengers { get; set; }

    }
}

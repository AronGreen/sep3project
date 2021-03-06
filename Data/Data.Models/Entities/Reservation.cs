﻿using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace Data.Models.Entities
{
    public class Reservation
    {
        /// <summary>
        /// The primary key of the Reservation
        /// </summary>
        [JsonPropertyName("id")]
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        /// <summary>
        /// The Id of the Trip the Reservation belongs to
        /// </summary>
        [JsonPropertyName("tripId")]
        public int? TripId { get; set; }

        /// <summary>
        /// The Id of the Passenger
        /// </summary>
        [JsonPropertyName("passengerEmail")]
        [Required]
        public string PassengerEmail { get; set; }

        /// <summary>
        /// The address the passenger is picked up from
        /// </summary>
        [JsonPropertyName("pickupAddress")]
        [Required]
        public string PickupAddress { get; set; }

        /// <summary>
        /// The address the passenger is going to
        /// </summary>
        [JsonPropertyName("dropoffAddress")]
        [Required]
        public string DropoffAddress { get; set; }

        /// <summary>
        /// The flag that indicates whether the driver has accepted the Reservation or not
        /// </summary>
        [JsonPropertyName("state")]
        public string State { get; set; }

        /// <summary>
        /// The time the driver specifies to meet and pick up the passenger(s)
        /// </summary>
        [JsonPropertyName("pickupTime")]
        public string PickupTime { get; set; }


        /// <summary>
        /// Passenger specifies how many seats he wants to book
        /// </summary>
        [JsonPropertyName("bookedSeats")]
        public int BookedSeats { get; set; }
    }
}

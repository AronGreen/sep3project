using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text;
using Microsoft.EntityFrameworkCore.Metadata.Internal;

namespace Data.Data.Entities
{
    public class Reservation
    {
        /// <summary>
        /// The primary key of the Reservation
        /// </summary>
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        /// <summary>
        /// The Trip the Reservation belongs to
        /// </summary>
        public Trip Trip { get; set; }

        /// <summary>
        /// The Passenger
        /// </summary>
        public User Passenger { get; set; }

        /// <summary>
        /// The address the passenger is picked up from
        /// </summary>
        public string PickupAddress { get; set; }

        /// <summary>
        /// The address the passenger is going to
        /// </summary>
        public string DropoffAddress { get; set; }

        /// <summary>
        /// The flag that indicates whether the driver has accepted the Reservation or not
        /// </summary>
        public DateTime? Approved { get; set; }

        /// <summary>
        /// The time the driver specifies to meet and pick up the passenger(s)
        /// </summary>
        public DateTime? PickupTime { get; set; }
    }
}

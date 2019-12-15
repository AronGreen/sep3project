using System;
using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace Data.Models.Entities
{
    public class Invoice
    {

        [JsonPropertyName("id")]
        [Key]
        public int Id { get; set; }

        [JsonPropertyName("tripId")]
        public int? TripId { get; set; }

        [JsonPropertyName("reservationId")]
        public int? ReservationId { get; set; }

        [JsonPropertyName("payerEmail")]
        public string PayerEmail { get; set; }

        [JsonPropertyName("payeeEmail")]
        public string PayeeEmail { get; set; }

        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("amount")]
        public double Amount { get; set; }

        [JsonPropertyName("state")]
        [Required]
        public string State { get; set; }

    }
}
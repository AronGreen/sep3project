using System;
using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace Data.Models.Entities
{
    public class Review
    {
        [JsonPropertyName("id")]
        [Key]
        public int Id { get; set; }

        [JsonPropertyName("reviewerEmail")]
        public String ReviewerEmail { get; set; }

        [JsonPropertyName("revieweeEmail")]
        public String RevieweeEmail { get; set; }

        [JsonPropertyName("content")]
        public String Content { get; set; }
    }
}

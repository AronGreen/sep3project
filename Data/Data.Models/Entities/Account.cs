using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace Data.Models.Entities
{
    public class Account
    {

        [JsonPropertyName("id")]
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        // Just a dummy data to have something
        [JsonPropertyName("name")]
        [Required]
        public string Name { get; set; }
    }
}
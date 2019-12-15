using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace Data.Models.Entities
{
    public class Notification
    {

        /*
         * When do we need notifications:
         *  !- A pending reservation has been accepted (notify passenger)
         *  !- A pending reservation has been rejected (notify passenger)
         *  !- An approved reservation has been deleted (notify driver)
         *  !- A reservation has been placed for a trip (notify driver)
         *  !- A trip has been cancelled (notify passengers)
         *  !- A payment has been issued (notify payer)
         *  !- A payment has been revoked (notify payer)
         *  !- A payment has been rejected (notify payer)
         */

        [Key]
        [JsonPropertyName("id")]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        [JsonPropertyName("accountEmail")]
        public string AccountEmail { get; set; }

        [JsonPropertyName("type")]
        public string Type { get; set; }

        [JsonPropertyName("itemId")]
        public int ItemId { get; set; }

        [JsonPropertyName("message")]
        public string Message { get; set; }

        [JsonPropertyName("date")]
        public string Date { get; set; }

    }
}
using System.Text.Json.Serialization;

namespace Data.Data
{
    public class TripFilter
    {

        [JsonPropertyName("flag")] 
        public bool Flag { get; set; }

    }
}
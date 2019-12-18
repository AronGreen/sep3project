using System;
using System.Text.Json.Serialization;

namespace Data.Data
{
    public class TripFilter
    {

        [JsonPropertyName("driverEmail")]
        public string DriverEmail { get; set; }

        [JsonPropertyName("passengerEmail")]
        public string PassengerEmail { get; set; }

        [JsonPropertyName("minimumArrivalDate")]
        public string MinimumArrivalDate { get; set; }
        
        [JsonPropertyName("maximumArrivalDate")]
        public string MaximumArrivalDate { get; set; }

        [JsonPropertyName("pickupAddress")]
        public string PickupAddress { get; set; }

        [JsonPropertyName("dropoffAddress")]
        public string DropoffAddress { get; set; }

        [JsonPropertyName("arrivalDate")]
        public string ArrivalDate { get; set; }

    }
}
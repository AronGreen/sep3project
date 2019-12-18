using System;
using System.Collections;
using System.Collections.Generic;
using System.Text.Json.Serialization;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion.Internal;

namespace Data.Models.Entities
{
    public class TripDetails
    {

        [JsonPropertyName("startTime")]
        public string StartTime { get; set; }

        [JsonPropertyName("endTime")]
        public string EndTime { get; set; }

        [JsonPropertyName("duration")]
        public int Duration { get; set; }

        [JsonPropertyName("stopAddresses")]
        public IList<string> StopAddresses { get; set; }

        [JsonPropertyName("stopTimes")]
        public IList<string> StopTimes { get; set; }
    }
}
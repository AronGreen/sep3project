using System.Text.Json;
using System.Text.Json.Serialization;

namespace Data.Network
{
    public class Response
    {

        /// <summary>
        /// The result of the Request. Success, Failure, etc.
        /// </summary>
        [JsonPropertyName("status")]
        public string Status { get; set; }
        
        /// <summary>
        /// Contains data about the Request.
        /// Could be the queried object, or the error message thrown.
        /// </summary>
        [JsonPropertyName("body")]
        public object Body { get; set; }

        /// <summary>
        /// Converts a Response object to a json format string.
        /// </summary>
        /// <returns>The json representation of the Response object.</returns>
        public string ToJson()
        {
            return JsonSerializer.Serialize(this);
        }

    }
}
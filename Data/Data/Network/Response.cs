using System.Text.Json;

namespace Data.Network
{
    public class Response
    {

        /// <summary>
        /// The result of the Request. Success, Failure, etc.
        /// </summary>
        public string Status { get; set; }
        
        /// <summary>
        /// Contains data about the Request.
        /// Could be the queried object, or the error message thrown.
        /// </summary>
        public string Body { get; set; }

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
using System.Text.Json;

namespace Data.Network
{
    
    /// <summary>
    /// Represents a data change or data query Request formulated by the Business Logic
    /// </summary>
    public class Request
    {

        /// <summary>
        /// The data type that is the subject to the request
        /// </summary>
        public string Type { get; set; }
        
        /// <summary>
        /// The operation to be used on the data collection
        /// </summary>
        public string Operation { get; set; }
        
        /// <summary>
        /// The information needed to perform the operation.
        /// Could be the object to be created, or the id of the object to be queried.
        /// </summary>
        public string Body { get; set; }

        /// <summary>
        /// Builds a request from a json format string
        /// Probably throws some fancy exception if the string is not a valid Request json.
        /// </summary>
        /// <param name="json">The json to Request is queried from.</param>
        /// <returns>The Request object queried from the json string.</returns>
        public static Request FromJson(string json)
        {
            return JsonSerializer.Deserialize<Request>(json);
        }

    }
}
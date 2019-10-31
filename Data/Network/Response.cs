using System.Text.Json;

namespace Data.Network
{
    public class Response
    {

        public string Status { get; set; }
        public string Body { get; set; }

        public string ToJson()
        {
            return JsonSerializer.Serialize(this);
        }

    }
}
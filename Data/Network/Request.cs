using System.Text.Json;

namespace Data.Network
{
    public class Request
    {

        public string Type { get; set; }
        public string Operation { get; set; }
        public string Body { get; set; }

        public static Request FromJson(string json)
        {
            return JsonSerializer.Deserialize<Request>(json);
        }

    }
}
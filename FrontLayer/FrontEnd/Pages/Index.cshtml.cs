using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using FrontEnd.Pages.Entities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;

namespace FrontEnd.Pages
{
    public class IndexModel : PageModel
    {
        public void OnGet()
        {

        }


        static async Task<string> GetTripAsync(string address)
        {
            HttpClient client = new HttpClient();
            Console.WriteLine("Fetching data...");
            string s = await client.GetStringAsync(address);
            return s;
        }

        static async Task<string> PostTrip(Trip trip, string address) {
            var client = new HttpClient();
            string jsonString = JsonConvert.SerializeObject(trip);
            var content = new StringContent(jsonString, Encoding.UTF8);

            HttpResponseMessage response = await client.PostAsync(address, content);

            return response.ToString();
        }
    }
}

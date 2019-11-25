using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using FrontEnd.Pages.Entities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;

namespace FrontEnd.Pages
{
    public class ManageTripsModel : PageModel
    {

        public string Message { get; set; } = "Initial message";
        public Trip trip = new Trip();
        public List<Trip> trips = new List<Trip>() {new Trip("TriperoPrimero","yesterday", "somewhere", "you are not going anywhere", 0), 
                                                    new Trip("LauTransport", "tomorrow", "Uni", "Home", 3) };
        public async Task OnPostTripAsync()
        {
            HttpClient client = new HttpClient();
            Console.WriteLine("Fetching data...");
            var s = await client.GetStringAsync("http://localhost:8080/Logic_war_exploded/trips/get");
            List<Trip> temp = JsonConvert.DeserializeObject<List<Trip>>(s);


            trips = temp;

     
        }

        public async Task OnPostSend()
        {
            HttpClient client = new HttpClient();
            
            var title = Request.Form["Title"];
            var date = Request.Form["Date"];
            var spoint = Request.Form["StartingPoint"];
            var epoint = Request.Form["EndingPoint"];
            int seats = Int32.Parse(Request.Form["AvailableSeats"]);

            Trip sendTrip = new Trip(title, date, spoint, epoint, seats);

            /*var json = JsonConvert.SerializeObject(sendTrip);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync("http://localhost:8080/api/trips/post", content);
*/
            trips.Add(sendTrip);

        }





        //static async Task<string> GetTripAsync(string address)
        //{
        //    HttpClient client = new HttpClient();
        //    Console.WriteLine("Fetching data...");
        //    string s = await client.GetStringAsync(address);
        //    return s;
        //}

    }
}
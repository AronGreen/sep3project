using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using FrontEnd.Pages.Entities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Newtonsoft.Json;

namespace FrontEnd.Pages
{
    public class ManageTripsModel : PageModel
    {
        [BindProperty]
        public int AvailableSeats { get; set; }
        public int ReservedSeats { get; set; }
        public SelectList Seats { get; set; }

        public Reservation Reservation { get; set; }

        
        public string Message { get; set; } = "Initial message";
        public Trip trip = new Trip();
        public List<Trip> trips = new List<Trip>() {new Trip("TriperoPrimero","yesterday", "somewhere", "you are not going anywhere", 0), 
                                                    new Trip("LauTransport", "tomorrow", "Uni", "Home", 3) };


        public void OnGet()
        {
            var listOfSeats = new List<int>
            { 1, 2, 3, 4, 5};

            Seats = new SelectList(listOfSeats);

        }
        public async Task OnPostTripAsync()
        {
            HttpClient client = new HttpClient();
            Console.WriteLine("Fetching data...");
            var s = await client.GetStringAsync("http://localhost:8080/Logic_war_exploded/trips/get");
            List<Trip> temp = JsonConvert.DeserializeObject<List<Trip>>(s);


            trips = temp;

     
        }

        public async Task OnPostSendAsync()
        {
            HttpClient client = new HttpClient();

            


            
            var title = Request.Form["Title"];
            var date = Request.Form["Date"];
            var spoint = Request.Form["StartingPoint"];
            var epoint = Request.Form["EndingPoint"];
            
            
            Trip sendTrip = new Trip(title, date, spoint, epoint, AvailableSeats);

            Debug.WriteLine("" + AvailableSeats);

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
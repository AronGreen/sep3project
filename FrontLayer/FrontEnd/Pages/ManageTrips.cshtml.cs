using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Text.Json;
using System.Net.Http.Headers;
using Data.Models.Entities;
using FrontEnd.ServiceProviders;

namespace FrontEnd.Pages
{
    public class ManageTripsModel : PageModel
    {
        private readonly ITripServiceProvider _tripServiceProvider = new TripServiceProvider();

        [BindProperty]
        public int AvailableSeats { get; set; }
        public int ReservedSeats { get; set; }
        public SelectList Seats { get; set; }

        [BindProperty]
        public Reservation Reservation { get; set; }

        
        public string Message { get; set; } = "Initial message";
        public Trip CreateTripModel = new Trip();
        public List<Trip> Trips = new List<Trip>();


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
            List<Trip> temp = JsonSerializer.Deserialize<List<Trip>>(s);


            Trips = temp;

     
        }

        public ActionResult OnPostCreate([FromForm] Trip trip)
        {
            trip.DriverEmail = Request.Cookies["EmailCookie"];
            //trip.TotalSeats = AvailableSeats;
            CreateTripModel.DriverEmail = Request.Cookies["EmailCookie"];
            var token = Request.Cookies["TokenCookie"];
            _tripServiceProvider.Create(trip, token);

            return Page();
        }

        public async Task OnPostReservationAsync([FromForm] Reservation reservation)
        {
            var shit = Request.Form["TripID"];
            var tripId = Int32.Parse(Request.Form["TripID"]);
            var pickUpPoint = Request.Form["PickUpPoint"];
            var pickUpTime = Request.Form["PickUpTime"];
            var dropOffPoint = Request.Form["DropOffPoint"];


            Reservation sendReservation = new Reservation()
            {
                TripId = tripId,
                PickupAddress = pickUpPoint,
                PickupTime = pickUpTime,
                DropoffAddress = dropOffPoint,
                PassengerEmail = Request.Cookies["EmailCookie"],


            };

            HttpClient client = new HttpClient();
            var token = Request.Cookies["TokenCookie"];
            var json = JsonSerializer.Serialize(sendReservation);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            string authenticationToken = Convert.ToBase64String(UTF8Encoding.UTF8.GetBytes($"{token}" + ":"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", authenticationToken);

            HttpResponseMessage response = await client.PostAsync("http://localhost:8080/Logic_war_exploded/reservations/create", content);

        }


        static async Task<string> GetTripAsync(string address)
        {
           HttpClient client = new HttpClient();
            Console.WriteLine("Fetching data...");
           string s = await client.GetStringAsync(address);
            return s;
        }

    }
}
 
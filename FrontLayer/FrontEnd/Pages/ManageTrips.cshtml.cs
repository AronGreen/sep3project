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
        public Trip trip = new Trip(1, "Titleee", "02.12.2018");
        public List<Trip> trips = new List<Trip>() {new Trip(312,"dsadsad","dsadsa") };
        public async Task OnPostTripAsync()
        {
            HttpClient client = new HttpClient();
            Console.WriteLine("Fetching data...");
            var s = await client.GetStringAsync("http://localhost:8080/api/trips/get/id");
            List<Trip> temp = JsonConvert.DeserializeObject<List<Trip>>(s);



            trips = temp;

     
        }

        public async void OnPostSend()
        {
            HttpClient client = new HttpClient();
            var id = Int32.Parse(Request.Form["Id"]);
            var title = Request.Form["Title"];
            var date = Request.Form["Date"];

            Trip sendTrip = new Trip(id, title, date);

            var json = JsonConvert.SerializeObject(sendTrip);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync("http://localhost:8080/api/trips/post", content);


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
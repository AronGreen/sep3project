﻿using System;
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
        public async void OnPostTrip()
        {
            HttpClient client = new HttpClient();
            Console.WriteLine("Fetching data...");
            var s = await client.GetStringAsync("http://localhost:8080/Logic_war_exploded/trips/get/1");
            Trip temp = JsonConvert.DeserializeObject<Trip>(s);


         
            trip = temp;
            trip = new Trip(2, "Hej", "Spader");
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

            HttpResponseMessage response = await client.PostAsync("http://localhost:8080/Logic_war_exploded/trips/put", content);


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
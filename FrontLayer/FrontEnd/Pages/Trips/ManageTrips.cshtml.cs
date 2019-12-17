﻿using System.Collections.Generic;
using Data.Data;
using Data.Models.Entities;
using Data.Models.Helpers;
using FrontEnd.ServiceProviders;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace FrontEnd.Pages.Trips
{
    public class ManageTripsModel : PageModel
    {

        private readonly ITripServiceProvider _tripServiceProvider = new TripServiceProvider();

        public List<Trip> Trips { get; set; }

        public IActionResult OnGet()
        {
            Trips = _tripServiceProvider.GetFiltered(new TripFilter() {DriverEmail = Request.Cookies["EmailCookie"]});
            return Page();
        }

        public IActionResult OnPostCreate([FromForm] Trip trip)
        {
            trip.DriverEmail = Request.Cookies["EmailCookie"];
            var token = Request.Cookies["TokenCookie"];

            // Put time in the right format
            var arrival = DateTimeHelper.FromDateTimeString(Request.Form["arrival"][0]);
            trip.Arrival = arrival;

            return _tripServiceProvider.Create(trip, token) ? OnGet() : NotFound();
        }

        public IActionResult OnPostTrip()
        {
            var token = Request.Cookies["TokenCookie"];
            var tripId = int.Parse(Request.Form["tripId"]);

            return _tripServiceProvider.Delete(tripId, token) ? OnGet() : NotFound();
        }
    }
}
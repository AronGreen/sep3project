using System;
using System.Collections.Generic;
using Data.Data;
using Data.Models.Entities;
using Data.Models.Helpers;
using FrontEnd.ServiceProviders;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace FrontEnd.Pages.Trips
{
    public class BrowseTripsModel : PageModel
    {
        private readonly ITripServiceProvider _tripServiceProvider = new TripServiceProvider();
        private readonly IReservationServiceProvider _reservationServiceProvider = new ReservationServiceProvider();

        public string UserEmail { get; set; }
        public List<Trip> Trips { get; set; } = new List<Trip>();

        // TODO this should be a get request - I was young and stupid
        public IActionResult OnPostTrips()
        {
            UserEmail = Request.Cookies["EmailCookie"];
            var arrival = DateTimeHelper.ToString(DateTime.Parse(Request.Form["arrival"][0]));
            var pickupAddress = Request.Form["pickupAddress"][0];
            var dropoffAddress = Request.Form["dropoffAddress"][0];
            var filter = new TripFilter()
            {
                PickupAddress = pickupAddress,
                DropoffAddress = dropoffAddress,
                ArrivalDate = arrival
            };

            Trips = _tripServiceProvider.GetFiltered(filter);
            return Page();
        }

        public IActionResult OnPostReserve()
        {
            var reservation = new Reservation()
            {
                TripId = int.Parse(Request.Form["tripId"]),
                PassengerEmail = Request.Cookies["EmailCookie"],
                BookedSeats = 1,
                PickupAddress = Request.Form["pickupAddressReservation"],
                DropoffAddress = Request.Form["dropoffAddressReservation"]
            };

            if (_reservationServiceProvider.Create(reservation, Request.Cookies["TokenCookie"]))
            {
                return Page();
            }
            return NotFound();
        }

    }
}
 
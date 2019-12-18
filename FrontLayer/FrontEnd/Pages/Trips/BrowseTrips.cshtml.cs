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
        private readonly IReviewServiceProvider _reviewServiceProvider = new ReviewServiceProvider();

        public string UserEmail { get; set; }
        public List<Trip> Trips { get; set; } = new List<Trip>();


        public string ErrorMessage { get; set; }
        public string SuccessMessage { get; set; }

        public IActionResult OnGet(string errorMessage = "", string successMessage = "")
        {
            ErrorMessage = errorMessage;
            SuccessMessage = successMessage;
            return Page();
        }

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
            return OnGet();
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

            return _reservationServiceProvider.Create(reservation, Request.Cookies["TokenCookie"])
                ? OnGet()
                : OnGet("Your reservation could not be created");
        }

        public IActionResult OnPostReview()
        {
            var content = Request.Form["content"];
            var reviewerEmail = Request.Cookies["EmailCookie"];
            var revieweeEmail = Request.Form["reviewee"];

            var review = new Review()
            {
                Content = content,
                RevieweeEmail = revieweeEmail,
                ReviewerEmail = reviewerEmail
            };

            return _reviewServiceProvider.Create(review, Request.Cookies["TokenCookie"])
                ? OnGet("", "Your review has been posted")
                : OnGet("Your review could not be posted");
        }

    }
}
 
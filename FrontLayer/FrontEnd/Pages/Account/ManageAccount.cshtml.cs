using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Data.Models.Entities;
using FrontEnd.Constants;
using FrontEnd.Helpers;
using FrontEnd.ServiceProviders;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace FrontEnd.Pages.Account
{
    public class ManageAccountModel : PageModel
    {

        private readonly IAccountServiceProvider _accountServiceProvider = new AccountServiceProvider();
        private readonly IReviewServiceProvider _reviewServiceProvider = new ReviewServiceProvider();

        public IList<Review> ReviewsReceived = new List<Review>();
        public IList<Review> ReviewsGiven = new List<Review>();

        public string ErrorMessage { get; set; }
        public string SuccessMessage { get; set; }

        public Data.Models.Entities.Account Account { get; set; }

        public IActionResult OnGet(string errorMessage = "", string successMessage = "")
        {
            ErrorMessage = errorMessage;
            SuccessMessage = successMessage;

            var email = Request.Cookies["EmailCookie"];
            Account = _accountServiceProvider.Get(email);

            ReviewsReceived = _reviewServiceProvider.GetAllByReviewee(email);
            ReviewsGiven = _reviewServiceProvider.GetAllByReviewer(email);

            return Page();
        }
        public IActionResult OnPostUpdate()
        {
            var token = Request.Cookies["TokenCookie"];
            var email = Request.Cookies["EmailCookie"];
            var password = Request.Form["password"];
            var firstName = Request.Form["firstName"];
            var lastName = Request.Form["lastName"];
            var dateOfBirth = Request.Form["dateOfBirth"];
            var phoneNumber = Request.Form["phoneNumber"];

            var account = new Data.Models.Entities.Account()
            {
                Email = email,
                FirstName = firstName,
                LastName = lastName,
                Password = password,
                DateOfBirth = dateOfBirth,
                Phone = phoneNumber,
            };

            return _accountServiceProvider.Update(account, token)
                ? OnGet()
                : OnGet("Your account could not be updated");
        }

        public IActionResult OnGetDelete()
        {
            var email = Request.Cookies["EmailCookie"];
            var token = Request.Cookies["TokenCookie"];
            var success = _accountServiceProvider.Delete(email, token);

            if (!success)
            {
                return OnGet("Your account could not be deleted");
            }

            Response.Cookies.Delete("EmailCookie");
            Response.Cookies.Delete("FirstNameCookie");
            Response.Cookies.Delete("LastNameCookie");
            Response.Cookies.Delete("PasswordCookie");
            Response.Cookies.Delete("DateOfBirthCookie");
            Response.Cookies.Delete("PhoneCookie");
            Response.Cookies.Delete("TokenCookie");

            return RedirectToPage("/Index");
        }
    }
}

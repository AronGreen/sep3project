using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Data.Models.Entities;
using FrontEnd.Pages.Entities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System.Text.Json;
using System.Net.Http.Headers;
using Microsoft.AspNetCore.Http;

namespace FrontEnd.Pages
{
    public class ManageAccountModel : PageModel
    {

        public List<Review> ReviewsReceived = new List<Review>();
        public List<Review> ReviewsGiven = new List<Review>();

        public async Task OnPostReviews() {

            var token = Request.Cookies["TokenCookie"];
            var email = Request.Cookies["EmailCookie"];
            HttpClient client = new HttpClient();
            string authenticationToken = Convert.ToBase64String(UTF8Encoding.UTF8.GetBytes($"{token}" + ":"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", authenticationToken);

            var s = await client.GetStringAsync("http://localhost:8080/Logic_war_exploded/reviews/getAllByReviewerEmail/" + $"{email}");

            List<Review> reviewsGiven = JsonSerializer.Deserialize<List<Review>>(s);
            ReviewsGiven = reviewsGiven;

            var z = await client.GetStringAsync("http://localhost:8080/Logic_war_exploded/reviews/getAllByRevieweeEmail/" + $"{email}");
            List<Review> reviewsReceived = JsonSerializer.Deserialize<List<Review>>(z);
            ReviewsReceived = reviewsReceived;
        }    
        public async Task<IActionResult> OnPostUpdateAsync()
        {

            var email = Request.Cookies["EmailCookie"];
            var password = Request.Form["password"];
            var firstName = Request.Form["firstName"];
            var lastName = Request.Form["lastName"];
            var dateOfBirth = Request.Form["dateOfBirth"];
            var phoneNumber = Request.Form["phoneNumber"];

            Account account = new Account()

            {
                Email = email,
                FirstName = firstName,
                LastName = lastName,
                Password = password,
                DateOfBirth = dateOfBirth,
                Phone = phoneNumber,



            };

            HttpClient client = new HttpClient();

            var token = Request.Cookies["TokenCookie"];
            string authenticationToken = Convert.ToBase64String(UTF8Encoding.UTF8.GetBytes($"{token}" + ":"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", authenticationToken);

            var json = JsonSerializer.Serialize(account);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            HttpResponseMessage response = await client.PutAsync("http://localhost:8080/Logic_war_exploded/accounts/update", content);
            if (response.StatusCode == System.Net.HttpStatusCode.OK)
            {
                var cookieOptions = new CookieOptions
                {

                    Expires = DateTime.Now.AddMonths(12),
                    Secure = true


                };

                Response.Cookies.Append("FirstNameCookie", $"{account.FirstName}", cookieOptions);
                Response.Cookies.Append("LastNameCookie", $"{account.LastName}", cookieOptions);
                Response.Cookies.Append("PhoneCookie", $"{account.Phone}", cookieOptions);




                return RedirectToPage("ManageAccount");
            }
            else {
                return RedirectToPage("ManageAccount");
            }









        }
        public async Task<IActionResult> OnPostDeleteAsync()
        {



            HttpClient client = new HttpClient();
            var email = Request.Cookies["EmailCookie"];
            var token = Request.Cookies["TokenCookie"];
            string authenticationToken = Convert.ToBase64String(UTF8Encoding.UTF8.GetBytes($"{token}" + ":"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", authenticationToken);
            HttpResponseMessage response = await client.DeleteAsync("http://localhost:8080/Logic_war_exploded/accounts/delete/" + $"{email}");

            Response.Cookies.Delete("EmailCookie");
            Response.Cookies.Delete("FirstNameCookie");
            Response.Cookies.Delete("LastNameCookie");
            Response.Cookies.Delete("PasswordCookie");
            Response.Cookies.Delete("DateOfBirthCookie");
            Response.Cookies.Delete("PhoneCookie");
            Response.Cookies.Delete("TokenCookie");


            return RedirectToPage("Index");
        }





    }
}

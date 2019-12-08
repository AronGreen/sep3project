using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Security.Claims;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Data.Models.Entities;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;


namespace FrontEnd.Pages
{
    public class IndexModel : PageModel
    {

        public bool RememberMe { get; set; }

        public string Token { get; set; }




        public async Task<IActionResult> OnPostLoginAsync()
        {
            var email = Request.Form["email"];
            var password = Request.Form["password"];



            HttpClient client = new HttpClient();
            string credentials = Convert.ToBase64String(UTF8Encoding.UTF8.GetBytes($"{email}:{password}"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", credentials);

            var response = await client.PostAsync("http://localhost:8080/Logic_war_exploded/authentication", new StringContent(""));
            var message = await response.Content.ReadAsStringAsync();
            var token = JsonSerializer.Deserialize<string>(message);






            if (response.IsSuccessStatusCode)
            {

                
                string authenticationToken = Convert.ToBase64String(UTF8Encoding.UTF8.GetBytes($"{token}" + ":"));
                client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", authenticationToken);

                var s = await client.GetStringAsync("http://localhost:8080/Logic_war_exploded/accounts/get/" + $"{email}");
                Account account = JsonSerializer.Deserialize<Account>(s);



                // Create the identity from the user info
                var claims = new List<Claim> {
                new Claim(ClaimTypes.NameIdentifier, email),
                new Claim(ClaimTypes.Role, "User") };


                var identity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);

                // Authenticate using the identity
                var principal = new ClaimsPrincipal(identity);
                await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, principal);




                //Values stored in the cookies for 30 minutes
                var cookieOptions = new CookieOptions
                {

                    Expires = DateTime.Now.AddMinutes(30),
                    Secure = true


                };
                Response.Cookies.Append("EmailCookie", $"{account.Email}", cookieOptions);
                Response.Cookies.Append("FirstNameCookie", $"{account.FirstName}", cookieOptions);
                Response.Cookies.Append("LastNameCookie", $"{account.LastName}", cookieOptions);
                Response.Cookies.Append("PasswordCookie", $"{account.Password}", cookieOptions);
                Response.Cookies.Append("DateOfBirth", $"{account.DateOfBirth}", cookieOptions);
                Response.Cookies.Append("PhoneCookie", $"{account.Phone}", cookieOptions);
                Response.Cookies.Append("TokenCookie", $"{token}", cookieOptions);



                return RedirectToPage("MainLoggedIn");


            }
            else {

                //ERROR MESSAGE
                return RedirectToPage("Index");
            }
            

        }
    }
}








































using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;
using FrontEnd.Pages.Entities;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;

namespace FrontEnd.Pages
{
    public class IndexModel : PageModel
    {
        
        public bool RememberMe { get; set; }

        public string Token { get; set; }



        public async Task<IActionResult> OnPostLoginAsync()
        {
            var username = Request.Form["username"];
            var password = Request.Form["password"];



           /* HttpClient client = new HttpClient();
            string credentials = Convert.ToBase64String(UTF8Encoding.UTF8.GetBytes($"{username}:{password}"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", credentials);
            var json = JsonConvert.SerializeObject(credentials);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            HttpResponseMessage response = await client.PostAsync("http://localhost:8080/Logic_war_exploded/auth", content);*/


            //TODO Include the logic here based on the HTTP response
            {
                Console.WriteLine("Wrong credentials");
            }
                {
                // Create the identity from the user info
                var claims = new List<Claim> {
                new Claim(ClaimTypes.NameIdentifier, username),
                new Claim(ClaimTypes.Name, username),
                new Claim(ClaimTypes.Role, "User") };


                var identity = new ClaimsIdentity(claims,CookieAuthenticationDefaults.AuthenticationScheme);

                    // Authenticate using the identity
                    var principal = new ClaimsPrincipal(identity);
                    await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, principal, new AuthenticationProperties { IsPersistent = RememberMe });
                }
                
            

            return RedirectToPage("MainLoggedIn");
        }

        


    }
    }



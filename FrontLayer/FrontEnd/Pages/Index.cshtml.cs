using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Security.Claims;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Data.Models.Entities;
using FrontEnd.Pages.Entities;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;



namespace FrontEnd.Pages
{
    public class IndexModel : PageModel
    {

        public bool RememberMe { get; set; }

        public string Token { get; set; }
        GlobalAccess GlobalAccess = GlobalAccess.Instance;



        public async Task OnPostLoginAsync()
        {
            var email = Request.Form["email"];
            var password = Request.Form["password"];



            HttpClient client = new HttpClient();
            string credentials = Convert.ToBase64String(UTF8Encoding.UTF8.GetBytes($"{email}:{password}"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", credentials);
            var json = JsonSerializer.Serialize(credentials);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            HttpResponseMessage response = await client.PostAsync("http://localhost:8080/Logic_war_exploded/auth", content);
            
            //TODO GET TOKEN AND STORE IT IN SINGLETON



            if (response.IsSuccessStatusCode)
            {
               


                // Create the identity from the user info
                var claims = new List<Claim> {
                new Claim(ClaimTypes.NameIdentifier, email),
                new Claim(ClaimTypes.Name, email),
                new Claim(ClaimTypes.Role, "User") };


                var identity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);

                // Authenticate using the identity
                var principal = new ClaimsPrincipal(identity);
                await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, principal, new AuthenticationProperties { IsPersistent = RememberMe });


                //SINGLETON SET ACCOUNT

                Console.WriteLine("Fetching data...");
                var s = await client.GetStringAsync("http://localhost:8080/Logic_war_exploded/accounts/get/" + $"{email}");
                var account = JsonSerializer.Deserialize<Account>(s);

                GlobalAccess.Instance.setAccount(account);

                RedirectToPage("MainLoggedIn");
            }
            else
            {
                // open popup with error message
            }







        }




    }
}
    



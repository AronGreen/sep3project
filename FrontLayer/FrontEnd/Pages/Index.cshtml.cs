﻿using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Security.Claims;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;
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
            var url = new Uri("http://localhost:8080/Logic_war_exploded/");
            using var client = new HttpClient();
            client.BaseAddress = url;
            var credentials = Convert.ToBase64String(UTF8Encoding.UTF8.GetBytes($"{email}:{password}"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", credentials);

            var response = await client.PostAsync("authentication", new StringContent(""));
            var message = response.Content.ReadAsStringAsync().GetAwaiter().GetResult();

            var token = JsonSerializer.Deserialize<TokenResponse>(message);

            if (response.StatusCode != System.Net.HttpStatusCode.OK) return RedirectToPage("Index");
            
            var authenticationToken = Convert.ToBase64String(UTF8Encoding.UTF8.GetBytes($"{token}" + ":"));
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", authenticationToken);

            var s = await client.GetStringAsync("http://localhost:8080/Logic_war_exploded/accounts/get/" + $"{email}");
            var account = JsonSerializer.Deserialize<Account>(s);

            // Create the identity from the user info
            var claims = new List<Claim> {
                new Claim(ClaimTypes.NameIdentifier, email),
                new Claim(ClaimTypes.Role, "User") };

            var identity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);

            // Authenticate using the identity
            var principal = new ClaimsPrincipal(identity);
            await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, principal);

            //Values stored in the cookies for 12 months
            var cookieOptions = new CookieOptions
            {
                Expires = DateTime.Now.AddMonths(12),
                Secure = true
            };
            Response.Cookies.Append("EmailCookie", $"{account.Email}", cookieOptions);
            Response.Cookies.Append("FirstNameCookie", $"{account.FirstName}", cookieOptions);
            Response.Cookies.Append("LastNameCookie", $"{account.LastName}", cookieOptions);
            Response.Cookies.Append("DateOfBirthCookie", $"{account.DateOfBirth}", cookieOptions);
            Response.Cookies.Append("PhoneCookie", $"{account.Phone}", cookieOptions);
            Response.Cookies.Append("TokenCookie", $"{token.Token}", cookieOptions);

            return RedirectToPage("MainLoggedIn");
        }

        private class TokenResponse
        {
            [JsonPropertyName("status")]
            public string Status { get; set; }
            [JsonPropertyName("token")]
            public string Token { get; set; }
        }
    }

   
}
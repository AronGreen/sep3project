using System;
using System.Collections.Generic;
using System.Security.Claims;
using System.Threading.Tasks;
using FrontEnd.ServiceProviders;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace FrontEnd.Pages
{
    public class IndexModel : PageModel
    {
        private readonly IAccountServiceProvider _accountServiceProvider = new AccountServiceProvider();

        public bool RememberMe { get; set; }

        public string Token { get; set; }

        public async Task<IActionResult> OnPostLoginAsync(
            [FromForm] string email,
            [FromForm] string password)
        {
            var token = _accountServiceProvider.Login(email, password);

            if (string.IsNullOrEmpty(token)) return RedirectToPage("Index", new { loginFail = true });

            var account = _accountServiceProvider.Get(email, token);

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
            Response.Cookies.Append("TokenCookie", $"{token}", cookieOptions);

            return RedirectToPage("Miscellaneous/MainLoggedIn");
        }
    }
}
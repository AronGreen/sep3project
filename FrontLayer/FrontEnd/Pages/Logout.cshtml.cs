using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace FrontEnd.Pages
{
    public class LogoutModel : PageModel
    {
        public ActionResult OnGet()
        {
            Response.Cookies.Delete("EmailCookie");
            Response.Cookies.Delete("FirstNameCookie");
            Response.Cookies.Delete("LastNameCookie");
            Response.Cookies.Delete("DateOfBirthCookie");
            Response.Cookies.Delete("PhoneCookie");
            Response.Cookies.Delete("TokenCookie");

            return RedirectToPage("Index");
        }
    }
}

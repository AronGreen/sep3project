using Microsoft.AspNetCore.Mvc.RazorPages;
using Data.Models.Entities;
using FrontEnd.ServiceProviders;
using Microsoft.AspNetCore.Mvc;

namespace FrontEnd.Pages
{
    public class RegisterModel : PageModel
    {
        private readonly IAccountServiceProvider _accountServiceProvider = new AccountServiceProvider();

        public IActionResult OnPostRegister([FromForm] Account account)
        {
            return RedirectToPage(_accountServiceProvider.Register(account) ? "Index" : "Register");
        }
    }
}
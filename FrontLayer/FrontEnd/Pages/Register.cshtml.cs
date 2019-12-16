using Microsoft.AspNetCore.Mvc.RazorPages;
using Data.Models.Entities;
using FrontEnd.ServiceProviders;
using Microsoft.AspNetCore.Mvc;

namespace FrontEnd.Pages
{
    public class RegisterModel : PageModel
    {
        private readonly IAccountServiceProvider _accountServiceProvider = new AccountServiceProvider();

        [BindProperty]
        public string ErrorMessage { get; set; } = "";

        public IActionResult OnPostRegister([FromForm] Account account)
        {
            var success = _accountServiceProvider.Register(account);
            if (success)
                return RedirectToPage("Index");
            return RedirectToPage("Register");
        }
    }
}
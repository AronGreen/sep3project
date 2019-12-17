using System;
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
            return _accountServiceProvider.Register(account) 
                ? RedirectToPage("Index", new { accountCreated = true }) 
                : RedirectToPage("Register", new { accountCreated = false });
        }
    }
}
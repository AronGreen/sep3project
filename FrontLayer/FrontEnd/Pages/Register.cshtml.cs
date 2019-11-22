using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Data.Models.Entities;
namespace FrontEnd.Pages
{
    public class RegisterModel : PageModel
    {
        public void OnGet()
        {
            var u = new User();
           
        }
    }
}
﻿
using Microsoft.AspNetCore.Mvc.RazorPages;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using FrontEnd.Pages.Entities;
using Data.Models.Entities;
using System.Net.Mime;
using System.Text.Json;
using System.Collections.Specialized;
using Microsoft.AspNetCore.Mvc;

namespace FrontEnd.Pages
{
    public class RegisterModel : PageModel
    {
        public string Message { get; set; }

        public void OnGet()
        {

        }
        public async Task<IActionResult> OnPostRegister()
        {
            string email = Request.Form["email"];
            string password = Request.Form["password"];
            string firstName = Request.Form["firstName"];
            string lastName = Request.Form["lastName"];
            string dateOfBirth = Request.Form["dateOfBirth"];
            string phoneNumber = Request.Form["phoneNumber"];

           Account account = new Account()

            {
                Email = email,
                Password = password,
                FirstName = firstName,
                LastName = lastName,
                DateOfBirth = dateOfBirth,
                Phone = phoneNumber,



            };
            System.Diagnostics.Debug.WriteLine(account);

            HttpClient client = new HttpClient();

            var json = JsonSerializer.Serialize(account);
            var content = new StringContent(json, Encoding.UTF8, MediaTypeNames.Application.Json);

            HttpResponseMessage response = await client.PostAsync("http://localhost:8080/Logic_war_exploded/accounts/create", content);



            if (response.StatusCode == System.Net.HttpStatusCode.OK)
            {

                return RedirectToPage("Index");
            }
            else {
                return RedirectToPage("Register");
            }

        }
    }
}
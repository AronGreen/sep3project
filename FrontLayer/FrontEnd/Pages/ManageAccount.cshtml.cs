using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Data.Models.Entities;
using FrontEnd.Pages.Entities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;

namespace FrontEnd.Pages
{
    public class ManageAccountModel : PageModel
    {

        GlobalAccess GlobalAccess = GlobalAccess.Instance;
        string Email = GlobalAccess.Instance.GetAccount().Email;
        public string Label = "Edit";

        



        public void onGet()
        {
            
        }
        

        public async Task onPutUpdateAsync()
        {

            var email = Request.Form["email"];
            var password = Request.Form["password"];
            var firstName = Request.Form["firstName"];
            var lastName = Request.Form["lastName"];
            var dateOfBirth = Request.Form["dateOfBirth"];
            var phoneNumber = Request.Form["phoneNumber"];

            Account account = new Account()

            {
                Email = email,
                FirstName = firstName,
                LastName = lastName,
                Password = password,
                DateOfBirth = dateOfBirth,
                Phone = phoneNumber,



            };

            HttpClient client = new HttpClient();

            var json = JsonConvert.SerializeObject(account);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            HttpResponseMessage response = await client.PutAsync("http://localhost:8080/Logic_war_exploded/reservations/update", content);  




        }
        public async Task onDeleteDeleteAsync() {

            HttpClient client = new HttpClient();
            HttpResponseMessage response = await client.DeleteAsync("http://localhost:8080/Logic_war_exploded/accounts/delete/" + $"{Email}" );
        }
        
            
           

                
    }
    }

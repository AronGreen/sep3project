using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text.Json;
using System.Threading;
using System.Threading.Tasks;
using FrontEnd.Pages.Entities;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace FrontEnd.Pages
{
    public class NotificationsModel : PageModel
    {

        [BindPropertyAttribute]
        public List<Notification> Notifications { get; set; }

        string mail;

        public async Task OnGet()
        {
            Notifications = new List<Notification>();
            mail = Request.Cookies["EmailCookie"];
            Thread thread = new Thread(async() => {
                while (true)
                {
                    await OnPostNotificationAsync();
                    Thread.Sleep(5000);
                }
            }
            );
            
        }

        private async Task OnPostNotificationAsync()
        {

            HttpClient client = new HttpClient();
            Console.WriteLine("Fetching data...");
            var s = await client.GetStringAsync("http://localhost:8080/Logic_war_exploded/notifications/get/" + mail + "?limit=20");
            List<Notification> temp = JsonSerializer.Deserialize<List<Notification>>(s);


            Notifications = temp;
        }
    }
}
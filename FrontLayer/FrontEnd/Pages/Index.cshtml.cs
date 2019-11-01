using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace FrontEnd.Pages
{
    public class IndexModel : PageModel
    {
        public void OnGet()
        {

        }


        static async Task<string> GetDataAsync(string address)
        {
            HttpClient client = new HttpClient();
            Console.WriteLine("Fetching data...");
            string s = await client.GetStringAsync(address);
            return s;
        }
    }
}

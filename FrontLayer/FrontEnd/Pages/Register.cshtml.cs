
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;



namespace FrontEnd.Pages
{
    public class RegisterModel : PageModel

    {
        
        public async Task OnPost()
        {
            var email = Request.Form["email"];
            var password = Request.Form["password"];
            var firstName = Request.Form["firstName"];
            var lastName = Request.Form["lastName"];
            var dateOfBirth = Request.Form["dateOfBirth"];
            var phoneNumber = Request.Form["phoneNumber"];

           /* Account account = new Account
            {
                Email = email,
                FirstName = firstName,
                LastName = lastName,
                Password = password,
                DateOfBirth = dateOfBirth,

                Phone = phoneNumber,
                //PasswordSalt  //TODO


            };

            HttpClient client = new HttpClient();

            var json = JsonConvert.SerializeObject(account);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync("http://localhost:8080/Logic_war_exploded/accounts/create", content);*/




        }
    }
}
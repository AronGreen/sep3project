using Data.Models.Entities;
using FrontEnd.Helpers;

namespace FrontEnd.ServiceProviders
{
    public class AccountServiceProvider : IAccountServiceProvider
    {
        public bool Register(Account account)
        {
            var response = ApiHelpers.DoPost(Constants.Api.Accounts.Create, account);

            return response.IsSuccessStatusCode;
        }
    }
}

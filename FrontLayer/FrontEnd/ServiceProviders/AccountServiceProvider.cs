using System;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;
using Data.Models.Entities;
using FrontEnd.Constants;
using FrontEnd.Helpers;

namespace FrontEnd.ServiceProviders
{
    public class AccountServiceProvider : IAccountServiceProvider
    {
        public Account Get(string email)
        {
            var response = ApiHelpers.DoGet(Constants.Api.Accounts.Get + $"/{email}");
            var json = response.Content.ReadAsStringAsync().GetAwaiter().GetResult();
            return JsonSerializer.Deserialize<Account>(json);
        }

        public bool Update(Account account, string token)
        {
            var response = ApiHelpers.DoPut(Api.Accounts.Update, account, token);
            return response.IsSuccessStatusCode;
        }

        public bool Delete(string email, string token)
        {
            return ApiHelpers.DoDelete(Api.Accounts.Delete + "/" + email, token).IsSuccessStatusCode;
        }

        public bool Register(Account account)
        {
            var response = ApiHelpers.DoPost(Constants.Api.Accounts.Create, account);

            return response.IsSuccessStatusCode;
        }

        public string Login(string email, string password)
        {
            var credentials = Convert.ToBase64String(
                Encoding.UTF8.GetBytes($"{email}:{password}"));
            var authenticationHeader = new AuthenticationHeaderValue("Basic", credentials);
            var response = ApiHelpers.DoPost(
                Constants.Api.Authentication.Authenticate,
                null,
                authenticationHeader);
            var message = response.Content.ReadAsStringAsync().GetAwaiter().GetResult();
           return JsonSerializer.Deserialize<TokenResponse>(message).Token;
        }


        private class TokenResponse
        {
            [JsonPropertyName("status")]
            public string Status { get; set; }
            [JsonPropertyName("body")]
            public string Token { get; set; }
        }
    }
}

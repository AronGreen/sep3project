using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using IdentityModel;
using IdentityModel.Client;
using Microsoft.AspNetCore.Http.Features;
using MobilePay.Extensions;
using MobilePay.Helpers;

namespace MobilePay.Services
{
    public class MobilePayService : IMobilePayService
    {

        private const string ClientId = "facaadf8-31fa-489d-99bd-42e42ff3b478";
        private const string ClientSecret = "dS6rC0sN3mT8lU5jO5aF3hD0rO8rK4wX5gA1fT4nC4eN0wC2rF";
        private const string RedirectUri = "http://mobilepayservice.azurewebsites.net/api/redirect";

        private string _idToken;
        private string _accessToken;
        private string _refreshToken;
        private DateTime _tokenExpiryTime;

        private (string State, byte[] CodeVerifier, string Nonce) _consentParameters;

        public string GetConsentUrl()
        {
            _consentParameters.State = GenerateState();
            _consentParameters.CodeVerifier = GenerateCodeVerifier();
            _consentParameters.Nonce = GenerateNonce();

            var url = "https://sandprod-admin.mobilepay.dk/account/connect/authorize" +
                         "?response_type=code id_token" +
                         "&response_mode=form_post" +
                         "&client_id=" + ClientId +
                         "&redirect_uri=" + RedirectUri +
                         "&scope=invoice openid transactionreporting offline_access" +
                         "&state=" + _consentParameters.State +
                         "&code_challenge=" + _consentParameters.CodeVerifier.Sha256Hash() +
                         "&code_challenge_method=S256" +
                         "&nonce=" + _consentParameters.Nonce +
                         "&merchant_vat=DK12345678";

            url = Base64Url.Encode(Encoding.UTF8.GetBytes(url));

            return url;
        }

        public string RequestTokens(string code, string state, string idToken)
        {
            if (state != _consentParameters.State)
                return "States don't match :(";

            var url = "https://api.sandbox.mobilepay.dk/";

            var client = new HttpClient();
            client.BaseAddress = new Uri(url);

            var request = new HttpRequestMessage(HttpMethod.Post, "merchant-authentication-openidconnect/connect/token");
            request.Content = new StringContent(
                "grant_type=authorization_code" +
                "&code=" + code +
                "&redirect_uri=" + RedirectUri + 
                "&code_verifier=" + _consentParameters.CodeVerifier.GetHexString() +
                "&client_id=" + ClientId +
                "&client_secret=" + ClientSecret +
                "&redirect_uri=" + RedirectUri,
                Encoding.UTF8,
                "application/x-www-form-urlencoded");
            return client.SendAsync(request).GetAwaiter().GetResult().ToString();
        }

        public void SetTokens(string idToken, string accessToken, string refreshToken, int expiresIn)
        {
            throw new NotImplementedException();
        }

        public void SendInvoice()
        {
            throw new NotImplementedException();
        }

        private void RefreshTokens() // Step 4
        {
            // Performs the post request to get the new tokens
        }

        private static byte[] GenerateCodeVerifier()
        {
            var bytes = Encoding.UTF8.GetBytes(StringHelper.GetRandomByteString(128));

            return bytes;
        }

        private static string GenerateState()
        {
            return StringHelper.GetRandomByteString(8);
        }

        private static string GenerateNonce()
        {
            return StringHelper.GetRandomByteString(64);
        }

        
    }
}

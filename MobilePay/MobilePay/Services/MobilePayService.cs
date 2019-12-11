using System;
using System.Collections.Generic;
using System.Globalization;
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

//        private const string ClientId = "facaadf8-31fa-489d-99bd-42e42ff3b478";
//        private const string ClientSecret = "dS6rC0sN3mT8lU5jO5aF3hD0rO8rK4wX5gA1fT4nC4eN0wC2rF";
        private const string ClientId = "viastudent";
        private const string ClientSecret = "8P5oCtMV8ILDCQAJNI65nKcz6VVyOpnwJ1Jz1Dk";
        private readonly string _redirectUri = HttpUtility.UrlEncode("https://mobilepayservice.azurewebsites.net/api/redirect");

        private string _code = "Hello World";

        private string _idToken;
        private string _accessToken;
        private string _refreshToken;
        private DateTime _tokenExpiryTime;

        private (string State, string Nonce) _consentParameters;
        private (string Verifier, string Challenge) _pkce;

        public string GetConsentUrl()
        {
            _consentParameters.State = GenerateState();
            _consentParameters.Nonce = GenerateNonce();
            _pkce = CreatePkce();

            var url = "https://sandprod-admin.mobilepay.dk/account/connect/authorize" +
                         "?response_type=code%20id_token" +
                         "&response_mode=form_post" +
                         "&client_id=" + ClientId +
                         "&redirect_uri=" + _redirectUri +
                         "&scope=invoice%20openid%20transactionreporting%20offline_access" +
                         "&state=" + _consentParameters.State +
                         "&code_challenge=" + _pkce.Challenge +
                         "&code_challenge_method=S256" +
                         "&nonce=" + _consentParameters.Nonce +
                         "&merchant_vat=DK12345678";

            return url;
        }

        public string RequestTokens(string code, string state, string idToken)
        {
            _code = code;
            if (state != _consentParameters.State)
                return "States don't match :("; // States don't match

            var url = "https://api.sandbox.mobilepay.dk/";

            var client = new HttpClient
            {
                BaseAddress = new Uri(url)
            };

            var request = new HttpRequestMessage(HttpMethod.Post, "merchant-authentication-openidconnect/connect/token")
            {
                Content = new FormUrlEncodedContent(
                    new[] 
                    {
                    new KeyValuePair<string, string>("grant_type", "authorization_code"),
                    new KeyValuePair<string, string>("code", code),
                    new KeyValuePair<string, string>("redirect_uri", _redirectUri),
                    new KeyValuePair<string, string>("code_verifier", _pkce.Verifier),
                    new KeyValuePair<string, string>("client_id", ClientId),
                    new KeyValuePair<string, string>("client_secret", ClientSecret)
                    })
//                    "&grant_type=authorization_code" +
//                    "&code=" + code +
//                    "&redirect_uri=" + _redirectUri +
//                    "&code_verifier=" + _consentParameters.CodeVerifier.GetHexString() +
//                    "&client_id=" + ClientId +
//                    "&client_secret=" + ClientSecret,
            };
            //            request.Content.Headers.Add("Content-Type", "application/x-www-form-urlencoded");
            client.SendAsync(request);
            return "blabla";
        }

        public void SetTokens(string idToken, string accessToken, string refreshToken, int expiresIn)
        {
            _idToken = idToken;
            _accessToken = accessToken;
            _refreshToken = refreshToken;
            _tokenExpiryTime = DateTime.Now.AddSeconds(expiresIn - 1);
        }

        public void SendInvoice()
        {
            throw new NotImplementedException();
        }

        public string GetTokens()
        {
            return "Id token: " + _idToken + "\n" +
                   "Access token: " + _accessToken + "\n" +
                   "Refresh token: " + _refreshToken + "\n" +
                   "Expires at: " + _tokenExpiryTime.ToString(CultureInfo.InvariantCulture);
        }

        public string GetCode()
        {
            return _code;
        }

        public string GetCodeVerifier()
        {
            return _pkce.Verifier;
        }

        public string GetCodeChallenge()
        {
            return _pkce.Challenge;
        }

        private void RefreshTokens() // Step 4
        {
            // Performs the post request to get the new tokens
        }

        private static string GenerateState()
        {
            return StringHelper.GetRandomByteString(8);
        }

        private static string GenerateNonce()
        {
            return StringHelper.GetRandomByteString(50);
        }

        private static (string verifier, string challenge) CreatePkce()
        {
            using var sha256 = SHA256.Create();

            // Create verifier (base64 encoded)
            var bytes = ByteHelper.GetRandomBytes(32);
            var verifier = Base64Url.Encode(bytes);

            // Create challenge (base64 encoded from UTF8 bytes of verifier)
            var hashedBytes = sha256.ComputeHash(Encoding.UTF8.GetBytes(verifier));
            var challenge = Base64Url.Encode(hashedBytes);

            return (verifier, challenge);
        }

        
    }
}

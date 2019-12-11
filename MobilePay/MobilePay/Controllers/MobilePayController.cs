using System.ComponentModel.DataAnnotations;
using System.Text;
using System.Text.Json.Serialization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Routing;
using MobilePay.Extensions;
using MobilePay.Services;
using Newtonsoft.Json;

namespace MobilePay.Controllers
{
    [ApiController]
    [Route("/api")]
    public class MobilePayController : ControllerBase
    {
        private readonly IMobilePayService _mobilePayService;

        public MobilePayController(IMobilePayService mobilePayService)
        {
            _mobilePayService = mobilePayService;
        }

        [HttpGet]
        [Route("getTokens")]
        public string GetTokens()
        {
            return _mobilePayService.GetTokens();
        }

        [HttpGet]
        [Route("getConsentUrl")]
        public string GetConsentUrl()
        {
            return _mobilePayService.GetConsentUrl();
        }

        [HttpGet]
        [Route("getCode")]
        public string GetCode()
        {
            return _mobilePayService.GetCode() ?? "Null code";
        }

        [HttpGet]
        [Route("getCodeVerifier")]
        public string GetCodeVerifier()
        {
            return _mobilePayService.GetCodeVerifier();
        }

        [HttpGet]
        [Route("getCodeChallenge")]
        public string GetCodeChallenge()
        {
            return _mobilePayService.GetCodeChallenge();
        }

        [HttpGet]
        [Route("initiateConsent")]
        public IActionResult InitiateConsent()
        {
            return Redirect(_mobilePayService.GetConsentUrl());
        }

        [HttpPost]
        [Route("redirect")]
        public IActionResult RedirectCode([FromForm] MobilePayResponse response)
        {
            if (response.Code != "")
            {
                _mobilePayService.RequestTokens(response.Code, response.State, response.IdToken);
                return Ok();
            }
            _mobilePayService.SetTokens(response.IdToken, response.AccessToken, response.RefreshToken,
                response.ExpiresIn);
            return Ok("Tokens have been set, yaay!");
        }

    }

    public class MobilePayResponse
    {
        [JsonPropertyName("id_token")]
        public string IdToken { get; set; }
        [JsonPropertyName("code")]
        public string Code { get; set; }
        [JsonPropertyName("state")]
        public string State { get; set; }
        [JsonPropertyName("access_token")]
        public string AccessToken { get; set; }
        [JsonPropertyName("refresh_token")]
        public string RefreshToken { get; set; }
        [JsonPropertyName("expires_in")]
        public int ExpiresIn { get; set; }
        [JsonPropertyName("token_type")]
        public string TokenType { get; set; }
    }
}
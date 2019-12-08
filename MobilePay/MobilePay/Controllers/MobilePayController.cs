using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Routing;
using MobilePay.Services;

namespace MobilePay.Controllers
{
    [ApiController]
    [Route("api")]
    public class MobilePayController : ControllerBase
    {
        private readonly IMobilePayService _mobilePayService;

        public MobilePayController(IMobilePayService mobilePayService)
        {
            _mobilePayService = mobilePayService;
        }

        [HttpGet]
        [Route("initiateConsent")]
        public IActionResult InitiateConsent()
        {
            return Redirect(_mobilePayService.GetConsentUrl());
        }

        [HttpGet]
        [Route("redirect")]
        public string RedirectConsent(
            string code,
            string state,
            [FromQuery(Name = "id_token")] string idToken)
        {
            return _mobilePayService.RequestTokens(code, state, idToken);
        }

        [HttpGet]
        [Route("redirect")]
        public string RedirectTokens(
            [FromQuery(Name = "id_token")] string idToken,
            [FromQuery(Name = "access_token")] string accessToken,
            [FromQuery(Name = "refresh_token")] string refreshToken,
            [FromQuery(Name = "expires_in")] int expiresIn,
            [FromQuery(Name = "token_type")] string tokenType)
        {
            if (tokenType != "Bearer")
                return "Token type is not 'Bearer'";
            _mobilePayService.SetTokens(idToken, accessToken, refreshToken, expiresIn);
            return "Tokens have been set, yeeey!!";
        }

    }
}
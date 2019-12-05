using Microsoft.AspNetCore.Mvc;
using MobilePay.Services;

namespace MobilePay.Controllers
{

    [ApiController]
    [Route("api")]
    public class TestController : ControllerBase
    {

        private readonly CountService _countService;

        public TestController(CountService countService)
        {
            _countService = countService;
        }

        [HttpGet]
        public int Get()
        {
            return _countService.Count;
        }

        [HttpGet]
        [Route("count")]
        public void Count()
        {
            _countService.Count++;
        }
    }
}
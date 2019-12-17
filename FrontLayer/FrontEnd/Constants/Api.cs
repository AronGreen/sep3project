using Microsoft.Extensions.FileSystemGlobbing.Internal;

namespace FrontEnd.Constants
{
    public static class Api
    {
        private const string BaseUrl = "http://localhost:8080/Logic_war_exploded/";
        
        public static class Accounts
        {
            public const string Create = BaseUrl + "accounts/create";
            public const string Get = BaseUrl + "accounts/get";
        }

        public static class Authentication
        {
            public const string Authenticate = BaseUrl + "authentication";
        }

        public static class Trips
        {
            private const string Base = BaseUrl + "trips/";

            public const string Create = Base + "create";
            public const string GetFiltered = Base + "get";
        }

        public static class Reservations
        {
            public const string Create = BaseUrl + "reservations/create";
        }
    }
}

﻿namespace FrontEnd.Constants
{
    public static class Api
    {
        private const string BaseUrl = "http://localhost:8080/Logic_war_exploded/";

        public static class Accounts
        {
            public const string Create = BaseUrl + "accounts/create";
        }

        public static class Trips
        {
            public const string Create = BaseUrl + "trips/create";
        }

        public static class Reservations
        {
            public const string Create = BaseUrl + "reservations/create";
        }
    }
}

using System;

namespace Data.Models.Helpers
{
    public class DateTimeHelper
    {

        public static DateTime FromString(string s)
        {
            var arr = s.Split("-");
            return new DateTime(
                int.Parse(arr[0]),
                int.Parse(arr[1]),
                int.Parse(arr[2]),
                int.Parse(arr[3]),
                int.Parse(arr[4]),
                0
                );
        }

        public static string ToString(DateTime date)
        {
            return string.Join("-",
                date.Year,
                date.Month,
                date.Date,
                date.Hour,
                date.Minute);
        }

    }
}
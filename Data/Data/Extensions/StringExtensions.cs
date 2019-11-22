using System;

namespace Data.Extensions
{
    public static class StringExtensions
    {

        public static String BeautifyJson(this string str)
        {
            return str
                .Replace(",", ",\n")
                .Replace("{", "{\n")
                .Replace("}", "\n}")
                .Replace("\\u0022", "\"");
        }
        
    }
}
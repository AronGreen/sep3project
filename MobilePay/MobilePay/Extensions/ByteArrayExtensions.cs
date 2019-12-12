using System.Security.Cryptography;
using System.Text;

namespace MobilePay.Extensions
{
    public static class ByteArrayExtensions
    {

        public static string GetHexString(this byte[] bytes)
        {
            var builder = new StringBuilder();
            foreach (var b in bytes)
            {
                builder.Append(b.ToString("x2"));
            }

            return builder.ToString();
        }

        public static string Sha256Hash(this byte[] bytes)
        {
            using var sha256 = SHA256.Create();

            var hashedBytes = sha256.ComputeHash(bytes);
            
            return hashedBytes.GetHexString();
        }

    }
}
using System.Security.Cryptography;

namespace MobilePay.Helpers
{
    public sealed class ByteHelper
    {

        public static byte[] GetRandomBytes(int count = 16)
        {
            var bytes = new byte[count];
            var rng = new RNGCryptoServiceProvider();

            rng.GetBytes(bytes);

            return bytes;
        }

    }
}
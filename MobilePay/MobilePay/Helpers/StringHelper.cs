using System;
using System.Security.Cryptography;
using System.Text;
using MobilePay.Extensions;

namespace MobilePay.Helpers
{
    public sealed class StringHelper
    {

        public static string GetRandomByteString(int byteCount = 16)
        {
            var bytes = ByteHelper.GetRandomBytes(byteCount);

            return bytes.GetHexString();
        }

    }
}
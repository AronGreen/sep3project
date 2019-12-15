using System;
using System.ComponentModel;
using System.IO;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Security.Cryptography;
using System.Text;

namespace Data.Security
{
    public class EncryptionHelper
    {

        private static readonly byte[] Key = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

        public static string EncryptString(string plainText)
        {
            using var aes = Aes.Create();
            if (aes == null)
                throw new NullReferenceException();

            var iv = GenerateIv();
            aes.Key = Key;
            aes.IV = iv;

            var cryptoTransform = aes.CreateEncryptor(aes.Key, aes.IV);

            using var memoryStream = new MemoryStream();
            using var cryptoStream = new CryptoStream(memoryStream, cryptoTransform, CryptoStreamMode.Write);
            using (var streamWriter = new StreamWriter(cryptoStream))
            {
                streamWriter.Write(plainText);
            }

            return Convert.ToBase64String(iv) + ":" + Convert.ToBase64String(memoryStream.ToArray());
        }

        public static string DecryptString(string cipherText)
        {
            var parts = cipherText.Split(":");
            var iv = Convert.FromBase64String(parts[0]);
            var bytes = Convert.FromBase64String(parts[1]);
            
            using var aes = Aes.Create();
            aes.Key = Key;
            aes.IV = iv;
            var cryptoTransform = aes.CreateDecryptor(aes.Key, aes.IV);

            using var memoryStream = new MemoryStream(bytes);
            using var cryptoStream = new CryptoStream((Stream) memoryStream, cryptoTransform, CryptoStreamMode.Read);
            using var streamReader = new StreamReader((Stream) cryptoStream);
            return streamReader.ReadToEnd();
        }

        private static byte[] GenerateIv()
        {
            var bytes = new byte[16];
            var rng = new RNGCryptoServiceProvider();
            rng.GetBytes(bytes);

            return bytes;
        }
    }
}
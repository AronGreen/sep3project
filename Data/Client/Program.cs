using System;

namespace client
{
    class Program
    {
        static void Main(string[] args)
        {
            var client = new Client();
            client.AddReceiver(msg => Console.WriteLine("Response: " + msg));

            while (true)
            {
                string msg = Console.ReadLine();

                client.Send(msg);
            }
        }
    }
}

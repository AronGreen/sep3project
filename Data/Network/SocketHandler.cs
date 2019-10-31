using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using Data.Logic;

namespace Data.Network
{
    public class SocketHandler : INetworkHandler
    {
        private RequestHandler handlers;

        public SocketHandler(RequestHandler handler)
        {
            handlers += handler;

            new Thread(() =>
            {
                byte[] ip = new byte[] {127, 0, 0, 1};
                var listener = new TcpListener(new IPAddress(ip), 3000);
                listener.Start();
                
                Console.WriteLine("SocketHandler is listening...");

                while (true)
                {
                    var client = listener.AcceptTcpClient();
                    Console.WriteLine("New connection opened");

                    new Thread(() => RequestLoop(client.GetStream())).Start();
                }
            }).Start();
        }

        private void RequestLoop(NetworkStream stream)
        {
            byte[] bytes = new byte[1024];
            int bytesRead = stream.Read(bytes, 0, bytes.Length);
            string json = Encoding.ASCII.GetString(bytes, 0, bytesRead);

            handlers(
                new Request()
                {
                    Body = "Body",
                    Operation = "get",
                    Type = "Type"
                },
                res =>
                {
                    bytes = Encoding.ASCII.GetBytes(res.ToJson());
                    stream.Write(bytes);
                });
        }
    }
}
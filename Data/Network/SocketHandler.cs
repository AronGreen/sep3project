using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using Data.Logic;

namespace Data.Network
{
    
    /// <summary>
    /// A Network Handler that can establish network connection via a json socket protocol.
    /// </summary>
    public class SocketHandler : INetworkHandler
    {
        
        // The handler that represents the business logic
        private RequestHandler handler;

        /// <summary>
        /// Starts accepting incoming socket connections in the background.
        /// </summary>
        /// <param name="handler">The Request Handler to be called when a Request arrives</param>
        public SocketHandler(RequestHandler handler)
        {
            this.handler = handler;

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

                    new Thread(() => RequestStart(client.GetStream())).Start();
                }
            }).Start();
        }

        
        // Reads the request from the socket, forwards it to the Request Handler,
        // then writes the Response to the socket.
        private void RequestStart(NetworkStream stream)
        {
            byte[] bytes = new byte[1024];
            int bytesRead = stream.Read(bytes, 0, bytes.Length);
            string json = Encoding.ASCII.GetString(bytes, 0, bytesRead);

            var res = handler(
                new Request()
                {
                    Body = "Body",
                    Operation = "get",
                    Type = "Type"
                });
            
            bytes = Encoding.ASCII.GetBytes(res.ToJson());
            stream.Write(bytes);
        }
    }
}
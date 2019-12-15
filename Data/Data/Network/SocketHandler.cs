using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Text.Json;
using System.Threading;
using Data.Logic;
using Data.Security;

namespace Data.Network
{
    /// <summary>
    /// A Network Handler that can establish network connection via a json socket protocol.
    /// </summary>
    public class SocketHandler : INetworkHandler
    {
        // The handler that represents the business logic
        private readonly IRequestHandler _requestHandler;

        /// <summary>
        /// Starts accepting incoming socket connections in the background.
        /// </summary>
        /// <param name="requestHandler">The Request Handler object that represents the Business Logic</param>
        public SocketHandler(
            IRequestHandler requestHandler)
        {
            _requestHandler = requestHandler;

        }


        // Reads the request from the socket, forwards it to the Request Handler,
        // then writes the Response to the socket.
        private void RequestStart(NetworkStream stream)
        {
            // Declare byte buffer, read input, and convert it to string
            byte[] bytes = new byte[4096];
            int bytesRead = stream.Read(bytes, 0, bytes.Length);
            string json = EncryptionHelper.DecryptString(Encoding.UTF8.GetString(bytes, 0, bytesRead));
            Console.WriteLine(Encoding.UTF8.GetString(bytes));
            var req = JsonSerializer.Deserialize<Request>(json);

            // Forward request to the Logic, and retrieve the Response
            var res = _requestHandler.Handle(req);
            Console.WriteLine(EncryptionHelper.EncryptString(res.ToJson()));
            // Encode Response to a json string and write it to Network Stream
            bytes = Encoding.UTF8.GetBytes(EncryptionHelper.EncryptString(res.ToJson()));
            stream.Write(bytes);
        }

        public void Start()
        {
            // This thread accepts connections, and forwards them to another handling thread
            new Thread(() =>
            {
                // Start the listener thread on 127.0.0.1:3000
                byte[] ip = new byte[] { 127, 0, 0, 1 };
                var listener = new TcpListener(new IPAddress(ip), 3000);
                listener.Start();

                Console.WriteLine("SocketHandler is listening...");

                while (true)
                {
                    // Accept clients
                    var client = listener.AcceptTcpClient();

                    // Forward the Network Stream to a handling thread
                    new Thread(() => RequestStart(client.GetStream())).Start();
                }
            }).Start();
        }
    }
}
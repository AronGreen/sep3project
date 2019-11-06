using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace client
{

    public delegate void Receiver(string msg);

    class Client
    {

        private Receiver receivers;
        private NetworkStream stream;

        public Client()
        {
            var client = new TcpClient();
            client.Connect("127.0.0.1", 3000);
            stream = client.GetStream();

            new Thread(readLoop).Start();
        }

        public void Send(string msg)
        {
            byte[] bytes = Encoding.ASCII.GetBytes(msg);
            stream.Write(bytes);
        }
        
        private void readLoop()
        {
            while (true)
            {
                byte[] bytes = new byte[1024];
                int bytesRead = stream.Read(bytes, 0, bytes.Length);
                string msg = Encoding.ASCII.GetString(bytes, 0, bytesRead);
                receivers(msg);
            }
        }

        public void AddReceiver(Receiver receiver)
        {
            receivers += receiver;
        }

    }
}

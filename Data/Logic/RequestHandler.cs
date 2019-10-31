using System;
using Data.Network;

namespace Data.Logic
{
    
    public delegate void RequestHandler(Request req, Action<Response> res);
        
}
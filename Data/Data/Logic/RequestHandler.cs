using System;
using Data.Network;

namespace Data.Logic
{
    
    // A method that is called by the Network Handler to ask for a Response for a Request
    public delegate Response RequestHandler(Request req);
        
}
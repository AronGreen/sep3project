using System;
using Data.Network;

namespace Data.Logic
{
    
    /// <summary>
    /// Represents the Business Logic in the data server.
    /// Handles incoming Requests.
    /// </summary>
    public interface IRequestHandler
    {
        
        /// <summary>
        /// Handles an incoming Request.
        /// </summary>
        /// <param name="req">The Request to be handled</param>
        /// <returns>The Response to the Request</returns>
        Response Handle(Request req);
        
    }
        
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Data.Models.Entities;

namespace FrontEnd.ServiceProviders
{
    public interface IAccountServiceProvider
    {
        bool Register(Account account);
    }
}

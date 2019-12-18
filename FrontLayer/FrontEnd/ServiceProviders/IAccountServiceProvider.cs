using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Data.Models.Entities;

namespace FrontEnd.ServiceProviders
{
    public interface IAccountServiceProvider
    {
        Account Get(string email);
        bool Update(Account account, string token);
        bool Delete(string email, string token);
        bool Register(Account account);
        string Login(string email, string password);

    }
}

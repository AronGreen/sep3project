using System;
using System.Collections.Generic;
using System.Text;
using Data.Data.Entities;

namespace Data.Data.Repositories
{
    interface IUserRepository
    {

        User Create(User user);

    }
}

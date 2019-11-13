using System;
using System.Collections.Generic;
using System.Text;
using Data.Data.Contexts;
using Data.Data.Entities;

namespace Data.Data.Repositories
{
    class UserRepository : IUserRepository
    {

        private readonly ApplicationContext _context;
        public UserRepository()
        {
            _context = new ApplicationContext();
        }
        public User Create(User user)
        {
            var u = _context.Users.Add(user).Entity;
            _context.SaveChanges();

            return u;
        }
    }
}

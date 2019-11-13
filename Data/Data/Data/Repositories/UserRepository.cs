using System.Linq;
using Data.Data.Contexts;
using Data.Models.Entities;

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

        public User Update(User user)
        {
            var u = _context.Users.Update(user).Entity;
            _context.SaveChanges();

            return u;
        }

        public User GetById(int id)
        {
            var u = _context.Users.Single(x => x.Id == id);

            return u;
        }

        public User[] GetAll()
        {
            var u = _context.Users.Select(x => x).ToArray();

            return u;
        }
    }
}

using System.Linq;
using Data.Data.Contexts;
using Data.Models.Entities;

namespace Data.Data.Repositories
{
    class AccountRepository : IAccountRepository
    {

        private readonly ApplicationContext _context;
        public AccountRepository()
        {
            _context = new ApplicationContext();
        }
        public Account Create(Account account)
        {
            var u = _context.Accounts.Add(account).Entity;
            _context.SaveChanges();

            return u;
        }

        public Account Update(Account account)
        {
            var u = _context.Accounts.Update(account).Entity;
            _context.SaveChanges();

            return u;
        }

        public Account GetById(int id)
        {
            var u = _context.Accounts.Single(x => x.Id == id);

            return u;
        }

        public Account[] GetAll()
        {
            var u = _context.Accounts.Select(x => x).ToArray();

            return u;
        }
    }
}

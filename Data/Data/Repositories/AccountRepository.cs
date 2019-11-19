using System.Linq;
using Data.Data.Contexts;
using Data.Models.Entities;

namespace Data.Data.Repositories
{
    class AccountRepository : IAccountRepository
    {

        private readonly ApplicationContext _context;

        public AccountRepository(
            ApplicationContext context)
        {
            _context = context;
        }
        public Account Create(Account account)
        {
            var result = _context.Accounts.Add(account).Entity;
            _context.SaveChanges();

            return result;
        }

        public Account Update(Account account)
        {
            var result = _context.Accounts.Update(account).Entity;
            _context.SaveChanges();

            return result;
        }

        public Account Delete(string email)
        {
            var result = _context.Accounts.Single(x => x.Email == email);

            _context.Accounts.Remove(result);
            _context.SaveChanges();

            return result;
        }

        public Account GetByEmail(string email)
        {
            return _context.Accounts
                .Single(x => x.Email == email);
        }

        public Account[] GetAll()
        {
            return _context.Accounts
                .Select(x => x)
                .ToArray();
        }
    }
}

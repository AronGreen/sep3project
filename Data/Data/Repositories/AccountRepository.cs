using System;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using Data.Data.Contexts;
using Data.Models.Entities;

namespace Data.Data.Repositories
{
    class AccountRepository  : IAccountRepository
    {

        private readonly ApplicationContext _context;

        public AccountRepository(
            ApplicationContext context)
        {
            _context = context;
        }
        public Account Create(Account account)
        {
            try
            {
                var result = _context.Accounts.Add(account).Entity;
                _context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Account Update(Account account)
        {
            try
            {
                var result = _context.Accounts.Update(account).Entity;
                _context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Account Delete(string email)
        {
            try
            {
                var result = _context.Accounts.Single(x => x.Email == email);

                _context.Accounts.Remove(result);
                _context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Account GetByEmail(string email)
        {
            try
            {
                return _context.Accounts
                    .Single(x => x.Email == email);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Account[] GetAll()
        {
            return _context.Accounts
                .Select(x => x)
                .ToArray();
        }

        public string GetPasswordByEmail(string email)
        {
            try
            {
                return _context.Accounts
                    .Select(x => new {x.Email, x.Password})
                    .Where(x => x.Email == email)
                    .Select(x => x.Password)
                    .ToArray()[0];
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
            
        }
    }
}

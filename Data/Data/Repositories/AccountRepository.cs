using System;
using System.Linq;
using Data.Models.Entities;

namespace Data.Repositories
{
    class AccountRepository  : IAccountRepository
    {

        public Account Create(Account account)
        {
            using var context = new ApplicationContext();
            try
            {
                var result = context.Accounts.Add(account).Entity;
                context.SaveChanges();

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
            using var context = new ApplicationContext();
            try
            {
                var result = context.Accounts.Update(account).Entity;
                context.SaveChanges();

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
            using var context = new ApplicationContext();
            try
            {
                var result = context.Accounts.Single(x => x.Email == email);

                context.Accounts.Remove(result);
                context.SaveChanges();

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
            using var context = new ApplicationContext();
            try
            {
                return context.Accounts
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
            using var context = new ApplicationContext();
            return context.Accounts
                .Select(x => x)
                .ToArray();
        }

        public string GetPasswordByEmail(string email)
        {
            using var context = new ApplicationContext();
            try
            {
                return context.Accounts
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

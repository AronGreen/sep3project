using Data.Models.Entities;

namespace Data.Data.Repositories
{
    interface IAccountRepository
    {
        Account Create(Account account);
        Account Update(Account account);
        Account Delete(string email);
        Account GetByEmail(string email);
        Account[] GetAll();

    }
}

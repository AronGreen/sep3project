using Data.Models.Entities;

namespace Data.Data.Repositories
{
    interface IUserRepository
    {

        User Create(User user);

        User Update(User user);

        User GetById(int id);

        User[] GetAll();

    }
}

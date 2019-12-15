using Data.Models.Entities;

namespace Data.Repositories
{
    public interface INotificationRepository
    {

        Notification Create(Notification notification);
        Notification[] GetAllByAccountEmail(string accountEmail, int limit = -1);
        Notification[] DeleteAllByTypeAndItemId(string type, int itemId);

    }
}
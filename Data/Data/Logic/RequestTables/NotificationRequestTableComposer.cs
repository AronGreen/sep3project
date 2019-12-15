using System.Collections.Generic;
using System.Text.Json;
using Data.Models.Entities;
using Data.Network;
using Data.Repositories;
using SQLitePCL;

namespace Data.Logic.RequestTables
{
    public class NotificationRequestTableComposer : IRequestTableComposer
    {

        private readonly INotificationRepository _notificationRepository;

        public NotificationRequestTableComposer(INotificationRepository notificationRepository)
        {
            _notificationRepository = notificationRepository;
        }

        public void Compose(IDictionary<(string, string), Handler> map)
        {
            map.Add(("notification", "create"), Create());
            map.Add(("notification", "getAllByAccountEmail"), GetAllByAccountEmail());
            map.Add(("notification", "deleteAllByTypeAndItemId"), DeleteAllByTypeAndItemId());
        }

        private Handler Create() => body =>
        {
            var result = _notificationRepository.Create(JsonSerializer.Deserialize<Notification>(body));
            var status = result == null ? "badRequest" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetAllByAccountEmail() => body =>
        {
            var limit = -1;
            if (body.Contains("/"))
            {
                limit = int.Parse(body.Split("/")[1]);
                body = body.Split("/")[0];
            }

            var result = _notificationRepository.GetAllByAccountEmail(body, limit);
            var status = result == null ? "internalError" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler DeleteAllByTypeAndItemId() => body =>
        {
            var type = body.Split("/")[0];
            var itemId = int.Parse(body.Split("/")[1]);

            var result = _notificationRepository.DeleteAllByTypeAndItemId(type, itemId);
            var status = result == null ? "internalError" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };
    }
}
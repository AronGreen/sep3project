using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Security.Cryptography;
using Data.Models.Entities;
using Microsoft.Extensions.Options;

namespace Data.Repositories
{
    public class NotificationRepository : INotificationRepository
    {
        public Notification Create(Notification notification)
        {
            using var context = new ApplicationContext();
            try
            {
                notification = context.Notifications.Add(notification).Entity;
                context.SaveChanges();

                return notification;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Notification[] GetAllByAccountEmail(string accountEmail, int limit = -1)
        {
            using var context = new ApplicationContext();
            try
            {

                var result = context.Notifications
                    .Select(x => x)
                    .Where(x => x.AccountEmail == accountEmail)
                    .OrderBy(x => x.Date);
                return limit >= 0 ? result.Take(limit).ToArray() : result.ToArray();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Notification[] DeleteAllByTypeAndItemId(string type, int itemId)
        {
            using var context = new ApplicationContext();
            try
            {
                var notifications = context.Notifications
                    .Select(x => x)
                    .Where(x => x.Type == type && x.ItemId == itemId)
                    .ToArray();
                context.Notifications.RemoveRange(notifications);
                context.SaveChanges();

                return notifications;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }
    }
}
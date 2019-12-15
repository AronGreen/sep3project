package services;

import models.Notification;
import models.response.NotificationListResponse;
import models.response.NotificationResponse;

public interface INotificationService {

    NotificationResponse create(Notification notification);
    NotificationListResponse getAllByAccountEmail(String accountEmail);
    NotificationListResponse getAllByAccountEmail(String accountEmail, int limit);
    NotificationListResponse deleteAllByTypeAndItemId(String type, int itemId);

}

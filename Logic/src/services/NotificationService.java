package services;

import com.sun.corba.se.impl.protocol.INSServerRequestDispatcher;
import helpers.JsonConverter;
import models.Notification;
import models.response.NotificationListResponse;
import models.response.NotificationResponse;

import javax.xml.crypto.Data;

public class NotificationService implements INotificationService {

    private DataConnection connection;

    public NotificationService() {
        connection = DataConnection.INSTANCE;
    }

    @Override
    public NotificationResponse create(Notification notification) {
        DataRequest request = new DataRequest("notification", "create", JsonConverter.toJson(notification));

        return JsonConverter.fromJson(connection.sendRequest(request), NotificationResponse.class);
    }

    @Override
    public NotificationListResponse getAllByAccountEmail(String accountEmail) {
        DataRequest request = new DataRequest("notification", "getAllByAccountEmail", accountEmail);

        return JsonConverter.fromJson(connection.sendRequest(request), NotificationListResponse.class);
    }

    @Override
    public NotificationListResponse getAllByAccountEmail(String accountEmail, int limit) {
        return getAllByAccountEmail(accountEmail + "/" + limit);
    }

    @Override
    public NotificationListResponse deleteAllByTypeAndItemId(String type, int itemId) {
        DataRequest request = new DataRequest("notification", "deleteAllByTypeAndItemId", type + "/" + itemId);

        return JsonConverter.fromJson(connection.sendRequest(request), NotificationListResponse.class);
    }
}

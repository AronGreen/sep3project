package services;

import com.google.gson.reflect.TypeToken;
import helpers.JsonConverter;
import models.Account;

import javax.xml.crypto.Data;

public class AccountService  implements IAccountService{

    private DataConnection connection;

    public AccountService() {
        connection = DataConnection.INSTANCE;
    }

    @Override
    public DataResponse<String> create(Account account) {
        DataRequest request = new DataRequest("account", "create", JsonConverter.toJson(account));

        String json = connection.sendRequest(request);

        return DataResponse.fromJson(json, String.class);
    }

    @Override
    public DataResponse<String> update(Account account) {
        DataRequest request = new DataRequest("account", "update", JsonConverter.toJson(account));

        String json = connection.sendRequest(request);

        return DataResponse.fromJson(json, String.class);
    }

    @Override
    public DataResponse<String> delete(String email) {
        DataRequest request = new DataRequest("account", "delete", email);

        String json = connection.sendRequest(request);

        return DataResponse.fromJson(json, String.class);
    }

    @Override
    public DataResponse<String> getAll() {
        DataRequest request = new DataRequest("account", "getAll", null);

        String json = connection.sendRequest(request);

        return DataResponse.fromJson(json, String.class);
    }

    @Override
    public DataResponse<String> getByEmail(String email) {
        DataRequest request = new DataRequest("account", "getByEmail", email);

        String json = connection.sendRequest(request);

        return DataResponse.fromJson(json, String.class);
    }

    @Override
    public DataResponse<String> getPasswordByEmail(String email) {
        DataRequest request = new DataRequest("account", "getPasswordByEmail", email);

        String json = connection.sendRequest(request);

        return DataResponse.fromJson(json, String.class);
    }
}

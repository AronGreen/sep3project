package services;

import helpers.JsonConverter;
import models.Account;
import models.response.AccountListResponse;
import models.response.AccountResponse;
import models.response.StringResponse;

public class AccountService  implements IAccountService{

    private DataConnection connection;

    public AccountService() {
        connection = DataConnection.INSTANCE;
    }

    @Override
    public AccountResponse create(Account account) {
        DataRequest request = new DataRequest("account", "create", JsonConverter.toJson(account));

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, AccountResponse.class);
    }

    @Override
    public AccountResponse update(Account account) {
        DataRequest request = new DataRequest("account", "update", JsonConverter.toJson(account));

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, AccountResponse.class);
    }

    @Override
    public AccountResponse delete(String email) {
        DataRequest request = new DataRequest("account", "delete", email);

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, AccountResponse.class);
    }

    @Override
    public AccountListResponse getAll() {
        DataRequest request = new DataRequest("account", "getAll", null);

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, AccountListResponse.class);
    }

    @Override
    public AccountResponse getByEmail(String email) {
        DataRequest request = new DataRequest("account", "getByEmail", email);

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, AccountResponse.class);
    }

    @Override
    public StringResponse getPasswordByEmail(String email) {
        DataRequest request = new DataRequest("account", "getPasswordByEmail", email);

        String json = connection.sendRequest(request);

        return JsonConverter.fromJson(json, StringResponse.class);
    }
}

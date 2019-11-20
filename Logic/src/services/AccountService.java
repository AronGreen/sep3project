package services;

import models.Account;

public class AccountService  implements IAccountService{

    private DataConnection connection;

    public AccountService() {
        connection = DataConnection.INSTANCE;
    }

    @Override
    public DataResponse<Account> getByEmail(String email) {
        DataRequest request = new DataRequest("account", "getByEmail", email);
        String json = connection.sendRequest(request);
        return DataResponse.fromJson(json, Account.class);
    }
}

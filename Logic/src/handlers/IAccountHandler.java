package handlers;

import models.Account;
import models.response.AccountListResponse;
import models.response.AccountResponse;
import services.DataResponse;

public interface IAccountHandler {

    AccountResponse create(Account account);
    AccountResponse update(Account account);
    AccountResponse delete(String email);
    AccountListResponse getAll();
    AccountResponse getByEmail(String email);
    DataResponse getPasswordByEmail(String email);

}

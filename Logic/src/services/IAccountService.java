package services;

import models.Account;
import models.response.AccountListResponse;
import models.response.AccountResponse;

public interface IAccountService {

    AccountResponse create(Account account);
    AccountResponse update(Account account);
    AccountResponse delete(String email);
    AccountListResponse getAll();
    AccountResponse getByEmail(String email);
    AccountResponse getPasswordByEmail(String email);
}

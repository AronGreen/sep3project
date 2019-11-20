package services;

import models.Account;

public interface IAccountService {
    DataResponse<Account> getByEmail(String email);
}

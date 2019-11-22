package services;

import models.Account;

public interface IAccountService {

    DataResponse<String> create(Account account);
    DataResponse<String> update(Account account);
    DataResponse<String> delete(String email);
    DataResponse<String> getAll();
    DataResponse<String> getByEmail(String email);
    DataResponse<String> getPasswordByEmail(String email);
}

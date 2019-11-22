package handlers;

import models.Account;
import services.DataResponse;

public interface IAccountHandler {

    DataResponse<String> create(Account account);
    DataResponse<String> update(Account account);
    DataResponse<String> delete(String email);
    DataResponse<String> getAll();
    DataResponse<String> getByEmail(String email);
    DataResponse<String> getPasswordByEmail(String email);

}

package handlers;

import models.Account;
import services.AccountService;
import services.DataResponse;
import services.IAccountService;

public class AccountHandler implements IAccountHandler {

    private IAccountService accountService;

    public AccountHandler() {
        accountService = new AccountService();
    }

    @Override
    public DataResponse<String> create(Account account) {
        return accountService.create(account);
    }

    @Override
    public DataResponse<String> update(Account account) {
        return accountService.update(account);
    }

    @Override
    public DataResponse<String> delete(String email) {
        return accountService.delete(email);
    }

    @Override
    public DataResponse<String> getAll() {
        return accountService.getAll();
    }

    @Override
    public DataResponse<String> getByEmail(String email) {
        return accountService.getByEmail(email);
    }

    @Override
    public DataResponse<String> getPasswordByEmail(String email) {
        return accountService.getPasswordByEmail(email);
    }
}

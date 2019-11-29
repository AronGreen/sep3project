package handlers;

import helpers.Password;
import models.Account;
import models.response.AccountListResponse;
import models.response.AccountResponse;
import services.AccountService;
import services.DataResponse;
import services.IAccountService;

public class AccountHandler implements IAccountHandler {

    private IAccountService accountService;

    public AccountHandler() {
        accountService = new AccountService();
    }

    @Override
    public AccountResponse create(Account account) {
        String plaintextPw = account.getPassword();
        try {
            account.setPassword(Password.getSaltedHash(plaintextPw));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountService.create(account);
    }

    @Override
    public AccountResponse update(Account account) {
        return accountService.update(account);
    }

    @Override
    public AccountResponse delete(String email) {
        return accountService.delete(email);
    }

    @Override
    public AccountListResponse getAll() {
        return accountService.getAll();
    }

    @Override
    public AccountResponse getByEmail(String email) {
        return accountService.getByEmail(email);
    }

    @Override
    public DataResponse getPasswordByEmail(String email) {
        return accountService.getPasswordByEmail(email);
    }
}

package handlers;

import helpers.Password;
import helpers.StringHelper;
import models.Account;
import models.response.AccountListResponse;
import models.response.AccountResponse;
import services.AccountService;
import models.response.StringResponse;
import services.IAccountService;

public class AccountHandler implements IAccountHandler {

    /*
     * BIG NOTE!!!
     * If you encounter a bug where the hashed password is "HashThrewException", then it's because the hash algorithm
     * in Password throws an exception and returned this value instead.
     */

    private IAccountService accountService;

    public AccountHandler() {
        accountService = new AccountService();
    }

    @Override
    public AccountResponse create(Account account) {
        account.setPassword(
                hashPassword(account.getPassword()));
        
        AccountResponse response = accountService.create(account);
        removePassword(response.getBody());

        return response;
    }

    @Override
    public AccountResponse update(Account account) {
        if (!StringHelper.isNullOrEmpty(account.getPassword())) {
            account.setPassword(
                    hashPassword(account.getPassword()));
        }
        else {
            account.setPassword(accountService.getPasswordByEmail(account.getEmail()).getBody());
        }

        AccountResponse response = accountService.update(account);
        removePassword(response.getBody());

        return response;
    }

    @Override
    public AccountResponse delete(String email) {
        AccountResponse response = accountService.delete(email);
        removePassword(response.getBody());

        return response;
    }

    @Override
    public AccountListResponse getAll() {
        AccountListResponse response = accountService.getAll();
        for (Account acc : response.getBody()) {
            removePassword(acc);
        }

        return response;
    }

    @Override
    public AccountResponse getByEmail(String email) {
        AccountResponse response = accountService.getByEmail(email);
        removePassword(response.getBody());

        return response;
    }

    @Override
    public StringResponse getPasswordByEmail(String email) {
        return accountService.getPasswordByEmail(email);
    }

    private String hashPassword(String password) {
        return Password.getSaltedHash(password);
    }

    private void removePassword(Account account) {
        account.setPassword("");
    }
}

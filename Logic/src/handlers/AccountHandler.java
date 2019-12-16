package handlers;

import constants.ResponseStatus;
import helpers.Password;
import helpers.StringHelper;
import models.Account;
import models.response.AccountListResponse;
import models.response.AccountResponse;
import services.AccountService;
import models.response.StringResponse;
import services.IAccountService;

public class AccountHandler implements IAccountHandler {

    private IAccountService accountService;

    public AccountHandler() {
        accountService = new AccountService();
    }

    @Override
    public AccountResponse create(Account account) {
        if (account == null) {
            return new AccountResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }
        account.setPassword(
                hashPassword(account.getPassword()));

        if (isInvalid(account)) {
            return new AccountResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }
        AccountResponse response = accountService.create(account);

        if (response.getBody() != null)
            removePassword(response.getBody());

        return response;
    }

    @Override
    public AccountResponse update(Account account) {
        if (account == null) {
            return new AccountResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }
        if (!StringHelper.isNullOrEmpty(account.getPassword())) {
            account.setPassword(
                    hashPassword(account.getPassword()));
        }
        else {
            account.setPassword(accountService.getPasswordByEmail(account.getEmail()).getBody());
        }

        if (isInvalid(account)) {
            return new AccountResponse(ResponseStatus.SOCKET_BAD_REQUEST, null);
        }
        AccountResponse response = accountService.update(account);
        if (response.getBody() != null)
            removePassword(response.getBody());

        return response;
    }

    @Override
    public AccountResponse delete(String email) {
        AccountResponse response = accountService.delete(email);
        if (response.getBody() != null)
            removePassword(response.getBody());

        return response;
    }

    @Override
    public AccountListResponse getAll() {
        AccountListResponse response = accountService.getAll();
        if (response.getBody() != null)
            for (Account acc : response.getBody()) {
                removePassword(acc);
            }

        return response;
    }

    @Override
    public AccountResponse getByEmail(String email) {
        AccountResponse response = accountService.getByEmail(email);
        if (response.getBody() != null)
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

    private boolean isInvalid(Account account) {
        return StringHelper.isNullOrEmpty(account.getFirstName()) ||
                StringHelper.isNullOrEmpty(account.getLastName()) ||
                StringHelper.isNullOrEmpty(account.getDateOfBirth()) ||
                StringHelper.isNullOrEmpty(account.getPhone()) ||
                StringHelper.isNullOrEmpty(account.getPassword());
    }
}

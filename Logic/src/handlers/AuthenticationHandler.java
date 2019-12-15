package handlers;

import constants.ResponseStatus;
import helpers.Password;
import models.Account;
import models.response.AccountResponse;
import services.AccountService;
import models.response.StringResponse;
import services.AuthTokenService;
import services.IAccountService;

import static constants.ResponseStatus.*;

public class AuthenticationHandler implements IAuthenticationHandler {

    private IAccountService service;

    public AuthenticationHandler() {
        service = new AccountService();
    }

    @Override
    public StringResponse authenticate(String email, String password) {
        AccountResponse userResponse = service.getByEmail(email);
        // TODO: If response status is not "success", return an error or smth
        Account storedAccount = userResponse.getBody();

        storedAccount.setPassword(service.getPasswordByEmail(email).getBody());

        try {
            if (Password.check(password, storedAccount.getPassword())) {
                AuthTokenService token = AuthTokenService.getInstance();
                return new StringResponse(SOCKET_SUCCESS, token.add(storedAccount));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new StringResponse(SOCKET_BAD_REQUEST, null);
        }
        return new StringResponse(SOCKET_UNAUTHORIZED, null);
    }
}

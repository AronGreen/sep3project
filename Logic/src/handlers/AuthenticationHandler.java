package handlers;

import helpers.Password;
import models.Account;
import models.response.AccountResponse;
import services.AccountService;
import services.AuthTokenService;
import services.DataResponse;
import services.IAccountService;

import static constants.Status.*;

public class AuthenticationHandler implements IAuthenticationHandler {

    private IAccountService service;

    public AuthenticationHandler() {
        service = new AccountService();
    }

    @Override
    public DataResponse authenticate(String email, String password) {
        AccountResponse userResponse = service.getByEmail(email);
        // TODO: If response status is not "success", return an error or smth
        Account storedAccount = userResponse.getBody();

        storedAccount.setPassword(service.getPasswordByEmail(email).getBody());

        try {
            if (Password.check(password, storedAccount.getPassword())) {
                AuthTokenService token = AuthTokenService.getInstance();
                return new DataResponse(SOCKET_SUCCESS, token.add(storedAccount));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DataResponse(SOCKET_FAILURE, null);
        }
        return new DataResponse(SOCKET_UNAUTHORIZED, null);
        // return 401
    }
}

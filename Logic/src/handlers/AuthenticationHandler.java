package handlers;

import constants.ResponseStatus;
import helpers.Password;
import helpers.StringHelper;
import models.Account;
import models.response.AccountResponse;
import services.AccountService;
import models.response.StringResponse;
import services.AuthTokenService;
import services.IAccountService;

import javax.ws.rs.core.HttpHeaders;

import static constants.ResponseStatus.*;

public class AuthenticationHandler implements IAuthenticationHandler {

    private IAccountService service;
    private AuthTokenService tokenService = AuthTokenService.getInstance();

    public AuthenticationHandler() {
        service = new AccountService();
    }

    @Override
    public StringResponse authenticate(String email, String password) {
        AccountResponse userResponse = service.getByEmail(email);

        if (!userResponse.getStatus().equals(SOCKET_SUCCESS)) {
            return new StringResponse(SOCKET_BAD_REQUEST, null);
        }

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

    @Override
    public boolean isAuthenticated(String email) {
        return tokenService.validate(email, 1);
    }

    @Override
    public String getEmail(String token) {
        return tokenService.getEmail(token);
    }

    @Override
    public String getEmail(HttpHeaders headers) {
        return getEmail(StringHelper.getTokenFromHttpHeaders(headers));
    }
}

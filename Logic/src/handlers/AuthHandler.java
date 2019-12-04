package handlers;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import helpers.AuthToken;
import helpers.JsonConverter;
import helpers.Password;
import models.Account;
import models.response.AccountResponse;
import services.AccountService;
import services.DataResponse;
import services.IAccountService;

import static constants.Status.*;

public class AuthHandler implements IAuthHandler {

    private IAccountService service;

    public AuthHandler() {
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
                AuthToken token = AuthToken.getInstance();
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

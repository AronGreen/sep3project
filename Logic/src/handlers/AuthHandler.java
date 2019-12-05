package handlers;

import helpers.AuthToken;
import helpers.Password;
import models.Account;
import models.response.AccountResponse;
import services.AccountService;
import models.response.StringResponse;
import services.IAccountService;

import static constants.ResponseStatus.*;

public class AuthHandler implements IAuthHandler {

    private IAccountService service;

    public AuthHandler() {
        service = new AccountService();
    }

    @Override
    public StringResponse authenticate(String email, String password) {
        AccountResponse userResponse = service.getByEmail(email);

        Account storedAccount = userResponse.getBody();
//        Account object = new Gson().fromJson(new Gson().toJson(((LinkedTreeMap<String, Object>) userResponse.getBody())), Account.class)

        try {
            if (Password.check(password, storedAccount.getPassword())) {
                AuthToken token = AuthToken.getInstance();
                return new StringResponse(SOCKET_SUCCESS, token.add(storedAccount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StringResponse(SOCKET_UNAUTHORIZED, null);
        // return 401
    }
}

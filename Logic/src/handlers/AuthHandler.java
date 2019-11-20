package handlers;

import helpers.AuthToken;
import helpers.Password;
import models.Account;
import services.AccountService;
import services.DataResponse;
import services.IAccountService;

public class AuthHandler implements IAuthHandler {

    private IAccountService service;

    public AuthHandler() {
        service = new AccountService();
    }

    @Override
    public DataResponse<String> authenticate(String email, String password) {
        DataResponse<Account> userResponse = service.getByEmail(email);

        Account storedAccount = userResponse.getBody();

        try {
            if (Password.check(password, storedAccount.getPassword())){
                AuthToken token = AuthToken.getInstance();
//                return token.add(storedAccount);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        // return 401
    }
}

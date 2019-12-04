package handlers;

import services.DataResponse;

public interface IAuthenticationHandler {

    DataResponse authenticate(String email, String password);
}

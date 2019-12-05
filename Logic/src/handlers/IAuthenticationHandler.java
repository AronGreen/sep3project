package handlers;

import models.response.StringResponse;

public interface IAuthenticationHandler {

    StringResponse authenticate(String email, String password);
}

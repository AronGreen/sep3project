package handlers;

import models.response.StringResponse;

import javax.ws.rs.core.HttpHeaders;

public interface IAuthenticationHandler {

    StringResponse authenticate(String email, String password);

    boolean isAuthenticated(String email);

    String getEmail(String token);
    String getEmail(HttpHeaders headers);
}

package handlers;

import models.response.StringResponse;

public interface IAuthHandler {

    StringResponse authenticate(String email, String password);
}

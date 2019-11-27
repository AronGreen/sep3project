package handlers;

import services.DataResponse;

public interface IAuthHandler {

    DataResponse authenticate(String email, String password);
}

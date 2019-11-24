package handlers;

import services.DataResponse;

public interface IAuthHandler {

    DataResponse<String> authenticate(String email, String password);
}
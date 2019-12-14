package controllers;

import static constants.ResponseStatus.*;

public class StatusMapper {

    public static int map(String dataResponseStatus) {
        // TODO extend according to code
        switch (dataResponseStatus) {
            case SOCKET_SUCCESS:
                return HTTP_SUCCESS;
            case SOCKET_FORBIDDEN:
                return HTTP_FORBIDDEN;
            case SOCKET_UNAUTHORIZED:
                return HTTP_UNAUTHORIZED;
            case SOCKET_BAD_REQUEST:
                return HTTP_BAD_REQUEST;
        }
        return 501; // not implemented;
    }
}

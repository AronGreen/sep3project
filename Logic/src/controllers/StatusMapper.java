package controllers;

import static constants.Status.*;

public class StatusMapper {

    public static int map(String dataResponseStatus) {
        // TODO extend according to code
        switch (dataResponseStatus) {
            case SOCKET_SUCCESS:
                return HTTP_SUCCESS;
            case SOCKET_FAILURE:
                return HTTP_FAILURE;
            case SOCKET_UNAUTHORIZED:
                return HTTP_UNAUTHORIZED;
        }
        return 404;
    }
}

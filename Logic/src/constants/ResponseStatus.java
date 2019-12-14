package constants;

public class ResponseStatus {
    public static final String SOCKET_SUCCESS = "success";
    public static final String SOCKET_BAD_REQUEST = "badRequest";
    public static final String SOCKET_UNAUTHORIZED = "unauthorized";
    public static final String SOCKET_FORBIDDEN = "forbidden";
    public static final String SOCKET_INTERNAL_ERROR = "internalError";

    public static final int HTTP_SUCCESS = 200;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    public static final int HTTP_NOT_IMPLEMENTED = 501;
}

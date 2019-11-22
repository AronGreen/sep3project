package controllers;

public class StatusMapper {

    public static int map(String dataResponseStatus) {
        // TODO extend according to code
        switch (dataResponseStatus) {
            case "success":
                return 200;
            case "failed":
                return 400;
        }
        return 404;
    }
}

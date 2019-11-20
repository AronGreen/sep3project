package controllers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.StringTokenizer;
import java.util.Base64;

@Path("/auth")
public class AuthController {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic ";

    @POST
    public String authenticate(@Context HttpHeaders httpheaders){

        try {
            String encodedCredentials = httpheaders.getRequestHeader(AUTHORIZATION_PROPERTY)
                    .get(0)
                    .replace(AUTHENTICATION_SCHEME , "");


            String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials.getBytes()));

            final StringTokenizer tokenizer = new StringTokenizer(decodedCredentials, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            return username + ", " + password;
        } catch (Exception e) {
            e.printStackTrace();
            return "No credentials, no cookies";
        }

    }
}

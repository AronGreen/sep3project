package helpers;

import constants.Authentication;

import javax.ws.rs.core.HttpHeaders;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.StringTokenizer;

import static constants.Authentication.*;

public class StringHelper {

    public static boolean isNullOrEmpty(String str){
        return str == null || str.length() == 0;
    }

    public static boolean equals_ignoreCase(String str1, String str2){
        return str1.toLowerCase().equals(str2.toLowerCase());
    }

    // TODO: Rename this ugly s.o.a.b
    public static boolean startsWith_ignoreCase(String prefix, String str){
        return str.toLowerCase().startsWith(prefix.toLowerCase());
    }

    public static boolean containsIgnoreCase(String whole, String part){
        return whole.toLowerCase().contains(part.toLowerCase());
    }

    public static String getTokenFromHttpHeaders(HttpHeaders httpHeaders){
        if (httpHeaders == null) return "";

        String authorizationHeader = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (StringHelper.isNullOrEmpty(authorizationHeader) ||
                !StringHelper.startsWith_ignoreCase(AUTHENTICATION_SCHEME + " ", authorizationHeader)) {
            return "";
        }

        String headerToken = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        String decodedToken = new String(Base64.getDecoder().decode(headerToken.getBytes()));
        StringTokenizer tokenizer = new StringTokenizer(decodedToken, ":");
        final String token = tokenizer.nextToken();
        if (StringHelper.isNullOrEmpty(token)) return "";
        return token;
    }

    public static String[] getCredentialsFromHttpHeaders(HttpHeaders httpHeaders){
        if (httpHeaders == null) return null;

        String authorizationHeader = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (StringHelper.isNullOrEmpty(authorizationHeader) ||
                !StringHelper.startsWith_ignoreCase(AUTHENTICATION_SCHEME + " ", authorizationHeader)) {
            return null;
        }

        String headerCredentials = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        String decodedCredentials = new String(Base64.getDecoder().decode(headerCredentials.getBytes()));
        StringTokenizer tokenizer = new StringTokenizer(decodedCredentials, ":");
        final String[] credentials = new String[2];
        credentials[0] = tokenizer.nextToken();
        credentials[1] = tokenizer.nextToken();
        return credentials;
    }

    public static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }
}

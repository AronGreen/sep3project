package services;

import constants.Authentication;
import helpers.StringHelper;
import models.Account;

import javax.ws.rs.core.HttpHeaders;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton service for handling authentication/authorization tokens
 */
public class AuthTokenService {

    private static AuthTokenService INSTANCE;
    private static final Lock lock = new ReentrantLock();
    private static final int TIMEOUT_MINUTES = 40;
    private static final int CLEANUP_INTERVAL_MINUTES = 20;

    private Map<String, TokenData> map;

    private AuthTokenService() {
        map = new HashMap<>();

        TimerTask cleanupTask = new CleanupTask();
        Timer timer = new Timer();
        timer.schedule(cleanupTask, CLEANUP_INTERVAL_MINUTES * 1000 * 60);
    }

    public static AuthTokenService getInstance() {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null) {
                    INSTANCE = new AuthTokenService();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Adds an account to the active tokens and returns its token string.
     * Returns an empty string on error.
     * Tokens will be removed after 40 minutes of inactivity (no validation requests on the token)
     * @param account the account to generate a token for
     * @return the generated token
     */
    public String add(Account account) {
        if (StringHelper.isNullOrEmpty(account.getEmail())) {
            return "";
        }

        String token = UUID.randomUUID().toString();
        TokenData tokenData = new TokenData(account.getEmail(), account.getAccessLevel());

        map.put(token, tokenData);

        return token;
    }

    /**
     * Revokes the access granted by the given token
     * @param token the token to revoke
     */
    public void revoke(String token) {
        map.remove(token);
    }

    /**
     * Checks if a given token exists, has not expired and has the given access level.
     * @param token the token to validate
     * @param accessLevel the desired access level
     * @return true if token is active and has the given access level or above
     */
    public boolean validate(String token, int accessLevel) {
        TokenData data = map.get(token);
        if (data == null) return false;
        LocalDateTime expired = LocalDateTime.now().minusMinutes(TIMEOUT_MINUTES);
        if (data.getLastUsed().isBefore(expired)) {
            revoke(token);
            return false;
        }
        data.updateLastUsed();
        return hasAccess(accessLevel, data.getAccessLevel());
    }

    /**
     * A helper method that tries to get an access token from the given HttpHeaders object.
     * @param httpHeaders HttpHeaders object with a Authorization header
     * @param accessLevel the desired access level
     * @return true if the headers have a valid, active token of the given access level
     */
    public boolean validate(HttpHeaders httpHeaders, int accessLevel) {
        if (httpHeaders == null) return false;

        String authorizationHeader = httpHeaders.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (StringHelper.isNullOrEmpty(authorizationHeader) ||
                !StringHelper.startsWith_ignoreCase(Authentication.AUTHENTICATION_SCHEME + " ", authorizationHeader)) {
            return false;
        }

        String headerToken = authorizationHeader
                .substring(Authentication.AUTHENTICATION_SCHEME.length()).trim();

        final String decodedToken = new String(Base64.getDecoder().decode(headerToken.getBytes()));
        final StringTokenizer tokenizer = new StringTokenizer(decodedToken, ":");
        final String token = tokenizer.nextToken();

        return validate(token, accessLevel);
    }

    public String getEmail(String token) {
        TokenData tokenData = map.get(token);
        if (tokenData == null) {
            return "";
        }
        LocalDateTime expired = LocalDateTime.now().minusMinutes(TIMEOUT_MINUTES);
        if (tokenData.getLastUsed().isBefore(expired)) {
            revoke(token);
            return "";
        }
        tokenData.updateLastUsed();
        return tokenData.email;
    }

    public String getEmail(HttpHeaders headers) {
        if (headers == null) return "";

        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (StringHelper.isNullOrEmpty(authorizationHeader) ||
                !StringHelper.startsWith_ignoreCase(Authentication.AUTHENTICATION_SCHEME + " ", authorizationHeader)) {
            return "";
        }

        String headerToken = authorizationHeader
                .substring(Authentication.AUTHENTICATION_SCHEME.length()).trim();

        final String decodedToken = new String(Base64.getDecoder().decode(headerToken.getBytes()));
        final StringTokenizer tokenizer = new StringTokenizer(decodedToken, ":");
        final String token = tokenizer.nextToken();

        return getEmail(token);
    }

    private boolean hasAccess(int desiredLevel, int actualLevel) {
        return desiredLevel <= actualLevel;
    }

    private class CleanupTask extends TimerTask {
        @Override
        public void run() {
            if (!map.isEmpty()) {
                LocalDateTime expiry = LocalDateTime.now().minusMinutes(TIMEOUT_MINUTES);
                ArrayList<String> toRemove = new ArrayList<>();

                for (Map.Entry<String, TokenData> entry : map.entrySet()) {
                    TokenData data = entry.getValue();
                    if (data.getLastUsed().isBefore(expiry)) {
                        toRemove.add(entry.getKey());
                    }
                }
                for (String key :
                        toRemove) {
                    map.remove(key);
                }
            }
        }
    }

    private static class TokenData {
        private String email;
        private int accessLevel;
        private LocalDateTime lastUsed;

        TokenData(String email, int role) {
            this.email = email;
            this.accessLevel = role;
            lastUsed = LocalDateTime.now();
        }

        public String getEmail() {
            return email;
        }

        int getAccessLevel() {
            return accessLevel;
        }

        LocalDateTime getLastUsed() {
            return lastUsed;
        }

        void updateLastUsed() {
            lastUsed = LocalDateTime.now();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof TokenData)) return false;
            TokenData other = (TokenData) obj;
            return this.email.equals(other.getEmail());
        }
    }
}

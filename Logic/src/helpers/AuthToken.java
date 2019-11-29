package helpers;

import models.Account;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AuthToken {

    private static AuthToken INSTANCE;
    private static final Lock lock = new ReentrantLock();
    private static final int TIMEOUT_MINUTES = 180;
    private static final int CLEANUP_INTERVAL_MINUTES = 20;

    // NOTE: Would be nice to have a bi-directional map to work with
    // Guava from google could do the trick
    private Map<String, TokenData> map;
    private Map<TokenData, String> reverseMap;

    private AuthToken() {
        map = new HashMap<>();
        reverseMap = new HashMap<>();

        TimerTask cleanupTask = new CleanupTask();
        Timer timer = new Timer();
        timer.schedule(cleanupTask, CLEANUP_INTERVAL_MINUTES * 1000 * 60);
    }

    public static AuthToken getInstance() {
        if (INSTANCE == null) {
            synchronized (lock) {
                if (INSTANCE == null) {
                    INSTANCE = new AuthToken();
                }
            }
        }
        return INSTANCE;
    }

    public String add(Account account) {
        if(account.getEmail() == null || account.getEmail().equals("")){
            return "";
        }
        if (account.getRoles() == null || account.getRoles().length() == 0) {
            account.setRoles("NONE");
        }
        TokenData compareTokenData = TokenData.compareToken(account.getEmail());
        if (reverseMap.containsKey(compareTokenData)){
            return reverseMap.get(compareTokenData);
        }

        String token = UUID.randomUUID().toString();
        TokenData tokenData = new TokenData(account.getEmail(), account.getRoles());

        map.put(token, tokenData);
        reverseMap.put(tokenData, token);

        return token;
    }

    public void revoke(String token) {
        map.remove(token);
    }

    public boolean hasRole(String token, String role) {
        TokenData data = map.get(token);
        LocalDateTime expired = LocalDateTime.now().minusMinutes(TIMEOUT_MINUTES);
        if (data.getLastUsed().isBefore(expired)) {
            map.remove(token);
            return false;
        }
        data.updateLastUsed();
        String roles = data.getRoles();
        return roles.contains(role);
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
        private String roles;
        private LocalDateTime lastUsed;

        TokenData(String email, String roles) {
            this.email = email;
            this.roles = roles;
            lastUsed = LocalDateTime.now();
        }

        public String getEmail() {
            return email;
        }

        String getRoles() {
            return roles;
        }

        LocalDateTime getLastUsed() {
            return lastUsed;
        }

        void updateLastUsed() {
            lastUsed = LocalDateTime.now();
        }

        static TokenData compareToken(String email){
            return new TokenData(email, "N/A");
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof  TokenData)) return false;
            TokenData other = (TokenData) obj;
            return this.email.equals(other.getEmail());
        }
    }
}

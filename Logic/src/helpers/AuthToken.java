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

    private HashMap<String, TokenData> _map;

    private AuthToken() {
        _map = new HashMap<>();

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
        if (account.getRoles() == null || account.getRoles().length() == 0) {
            throw new IllegalArgumentException("List of roles must not be empty");
        }
        String token = UUID.randomUUID().toString();

        _map.put(token, new TokenData(account.getEmail(), account.getRoles()));

        return token;
    }

    public void revoke(String token) {
        _map.remove(token);
    }

    public boolean hasRole(String token, String role) {
        TokenData data = _map.get(token);
        LocalDateTime expired = LocalDateTime.now().minusMinutes(TIMEOUT_MINUTES);
        if (data.getLastUsed().isBefore(expired)) {
            _map.remove(token);
            return false;
        }
        data.updateLastUsed();
        String roles = data.getRoles();
        return roles.contains(role);
    }

    private class CleanupTask extends TimerTask {
        @Override
        public void run() {
            if (!_map.isEmpty()) {
                LocalDateTime expiry = LocalDateTime.now().minusMinutes(TIMEOUT_MINUTES);
                ArrayList<String> toRemove = new ArrayList<>();

                for (Map.Entry<String, TokenData> entry : _map.entrySet()) {
                    TokenData data = entry.getValue();
                    if (data.getLastUsed().isBefore(expiry)) {
                        toRemove.add(entry.getKey());
                    }
                }
                for (String key :
                        toRemove) {
                    _map.remove(key);
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
    }
}

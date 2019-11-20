package helpers;

import models.Account;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AuthToken {

    private static AuthToken INSTANCE;
    private static final Lock lock = new ReentrantLock();
    private static final int TIMEOUT_MINUTES = 180;
    private static final int CLEANUP_INTERVAL_MINUTES = 20;

    private LocalDateTime lastCleanup;
    private HashMap<String, TokenData> _map;

    private AuthToken() {
        _map = new HashMap<>();
        lastCleanup = LocalDateTime.now();
    }

    public static AuthToken getInstance(){
        if (INSTANCE == null){
            synchronized (lock) {
                if (INSTANCE == null) {
                    INSTANCE = new AuthToken();
                }
            }
        }
        return INSTANCE;
    }

    public String add(Account account){
        if (account.getRoles() == null || account.getRoles().length() == 0){
            throw  new IllegalArgumentException("List of roles must not be empty");
        }
        String token = UUID.randomUUID().toString();

        _map.put(token, new TokenData(account.getEmail(), account.getRoles()));

        return token;
    }

    public void revoke(String token){
        if(_map.containsKey(token)){
            _map.remove(token);
        }
    }

    public boolean hasRole(String token, String role){
        TokenData data = _map.get(token);
        LocalDateTime expired = LocalDateTime.now().minusMinutes(TIMEOUT_MINUTES);
        if (data.getLastUsed().isBefore(expired)){
            _map.remove(token);
            return false;
        }
        data.updateLastUsed();
        String roles = data.getRoles();
        return roles.contains(role);
    }

    private void cleanUp(){
        if (lastCleanup.isBefore(LocalDateTime.now().minusMinutes(CLEANUP_INTERVAL_MINUTES))){
           // TODO: clean map
        }
    }

    private class TokenData{
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

        public String getRoles() {
            return roles;
        }

        public LocalDateTime getLastUsed() {
            return lastUsed;
        }
        public void updateLastUsed(){
            lastUsed = LocalDateTime.now();
        }
    }
}

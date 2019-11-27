package models.response;

import models.Account;

public class AccountResponse {

    String status;
    private Account body;

    public AccountResponse(String status, Account body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getBody() {
        return body;
    }

    public void setBody(Account body) {
        this.body = body;
    }
}

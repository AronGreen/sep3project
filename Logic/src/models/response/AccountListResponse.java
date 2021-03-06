package models.response;

import models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountListResponse {

    String status;
    private List<Account> body;

    public AccountListResponse(String status, List<Account> body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Account> getBody() {
        return body;
    }

    public void setBody(List<Account> body) {
        this.body = body;
    }
}

package models.response;

import models.Account;

import java.util.ArrayList;

public class AccountListResponse {

    String status;
    private ArrayList<Account> body;

    public AccountListResponse(String status, ArrayList<Account> body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Account> getBody() {
        return body;
    }

    public void setBody(ArrayList<Account> body) {
        this.body = body;
    }
}

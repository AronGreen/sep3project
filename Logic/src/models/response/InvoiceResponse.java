package models.response;

import models.Invoice;

public class InvoiceResponse {

    private String status;
    private Invoice body;

    public InvoiceResponse(String status, Invoice body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public Invoice getBody() {
        return body;
    }
}

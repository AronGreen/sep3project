package models.response;

import models.Invoice;

import java.util.List;

public class InvoiceListResponse {

    private String status;
    private List<Invoice> body;

    public InvoiceListResponse(String status, List<Invoice> body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public List<Invoice> getBody() {
        return body;
    }
}

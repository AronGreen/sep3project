package serviceproviders.payment;

import models.PaymentMethod;

public class Payment {

    private int id;
    private int invoiceId;
    private PaymentMethod payer;
    private PaymentMethod payee;
    private double amount;
    private PaymentState state;

    public Payment(int invoiceId, PaymentMethod payer, PaymentMethod payee, double amount) {
        this.invoiceId = invoiceId;
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(PaymentState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public PaymentMethod getPayer() {
        return payer;
    }

    public PaymentMethod getPayee() {
        return payee;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentState getState() {
        return state;
    }
}

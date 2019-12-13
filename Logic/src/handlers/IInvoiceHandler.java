package handlers;

import models.Invoice;

public interface IInvoiceHandler {

    Invoice create(Invoice invoice);
    boolean accountHasUnpaidInvoice(String accountEmail);

}

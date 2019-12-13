package services;

import models.Invoice;
import models.response.InvoiceListResponse;
import models.response.InvoiceResponse;

public interface IInvoiceService {

    InvoiceResponse create(Invoice invoice);
    InvoiceResponse updateState(int id, String state);
    InvoiceResponse getById(int id);
    InvoiceListResponse getAllByPayerEmail(String payerEmail);
    InvoiceListResponse getAllUnpaidByPayerEmail(String payerEmail);

}

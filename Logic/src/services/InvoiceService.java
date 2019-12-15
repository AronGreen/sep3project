package services;

import helpers.JsonConverter;
import models.Invoice;
import models.response.InvoiceListResponse;
import models.response.InvoiceResponse;

public class InvoiceService implements IInvoiceService {

    private DataConnection connection;

    public InvoiceService() {
        connection = DataConnection.INSTANCE;
    }

    @Override
    public InvoiceResponse create(Invoice invoice) {
        DataRequest request = new DataRequest("invoice", "create", JsonConverter.toJson(invoice));

        InvoiceResponse response = JsonConverter.fromJson(connection.sendRequest(request), InvoiceResponse.class);

        return response;
    }

    @Override
    public InvoiceResponse updateState(int id, String state) {
        Invoice invoice = new Invoice(id, state);
        DataRequest request = new DataRequest("invoice", "updateState", JsonConverter.toJson(invoice));

        InvoiceResponse response = JsonConverter.fromJson(connection.sendRequest(request), InvoiceResponse.class);

        return response;
    }

    @Override
    public InvoiceResponse getById(int id) {
        DataRequest request = new DataRequest("invoice", "getById", id + "");

        InvoiceResponse response = JsonConverter.fromJson(connection.sendRequest(request), InvoiceResponse.class);

        return response;
    }

    @Override
    public InvoiceListResponse getAllByPayerEmail(String payerEmail) {
        DataRequest request = new DataRequest("invoice", "getAllByPayerEmail", payerEmail);

        InvoiceListResponse response = JsonConverter.fromJson(connection.sendRequest(request), InvoiceListResponse.class);

        return response;
    }

    @Override
    public InvoiceListResponse getAllUnpaidByPayerEmail(String payerEmail) {
        DataRequest request = new DataRequest("invoice", "getAllUnpaidByPayerEmail", payerEmail);

        InvoiceListResponse response = JsonConverter.fromJson(connection.sendRequest(request), InvoiceListResponse.class);

        return response;
    }

    @Override
    public InvoiceResponse getByReservationId(int reservationId) {
        return JsonConverter.fromJson(connection.sendRequest(new DataRequest(
                "invoice", "getByReservationId", reservationId + "")
        ), InvoiceResponse.class);
    }
}

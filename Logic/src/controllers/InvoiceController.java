package controllers;

import dependencycollection.DependencyCollection;
import handlers.IInvoiceHandler;
import models.response.InvoiceResponse;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/invoices")
public class InvoiceController {

    private IInvoiceHandler invoiceHandler = DependencyCollection.getInvoiceHandler();

    @POST
    @Path("/pay/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pay(@PathParam("id") int invoiceId) {
        boolean res = invoiceHandler.pay(invoiceId);

        return Response
                .status(Response.Status.OK)
                .entity("{success: " + res + "}")
                .build();
    }

}

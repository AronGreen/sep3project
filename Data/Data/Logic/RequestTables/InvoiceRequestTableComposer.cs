using System.Collections.Generic;
using System.ComponentModel;
using System.Reflection.Metadata;
using System.Text.Json;
using Data.Models.Entities;
using Data.Network;
using Data.Repositories;

namespace Data.Logic.RequestTables
{
    public class InvoiceRequestTableComposer : IRequestTableComposer
    {

        private readonly IInvoiceRepository _invoiceRepository;

        public InvoiceRequestTableComposer(IInvoiceRepository invoiceRepository)
        {
            _invoiceRepository = invoiceRepository;
        }

        public void Compose(IDictionary<(string, string), Handler> map)
        {
            map.Add(("invoice", "create"), CreateInvoice());
            map.Add(("invoice", "updateState"), UpdateState());
            map.Add(("invoice", "getById"), GetById());
            map.Add(("invoice", "getAllByPayerEmail"), GetAllByPayerEmail());
            map.Add(("invoice", "getAllUnpaidByPayerEmail"), GetAllUnpaidByPayerEmail());
            map.Add(("invoice", "getByReservationId"), GetByReservationId());
        }

        private Handler CreateInvoice() => body =>
        {
            var result = _invoiceRepository.Create(JsonSerializer.Deserialize<Invoice>(body));
            var status = result == null ? "internalError" : "success";
            return new Response
            {
                Status = status,
                Body = result
            };
        };

        private Handler UpdateState() => body =>
        {
            var invoice = JsonSerializer.Deserialize<Invoice>(body);
            var result = _invoiceRepository.UpdateState(invoice.Id, invoice.State);
            var status = result == null ? "badRequest" : "success";
            return new Response
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetById() => body =>
        {
            var result = _invoiceRepository.GetById(int.Parse(body));
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetAllByPayerEmail() => body =>
        {
            var result = _invoiceRepository.GetAllByPayerEmail(body);
            var status = result == null ? "internalError" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetAllUnpaidByPayerEmail() => body =>
        {
            var result = _invoiceRepository.GetAllUnpaidByPayerEmail(body);
            var status = result == null ? "internalError" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetByReservationId() => body =>
        {
            var result = _invoiceRepository.GetByReservationId(int.Parse(body));
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

    }
}
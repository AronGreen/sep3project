using Data.Models.Entities;

namespace Data.Repositories
{
    public interface IInvoiceRepository
    {

        Invoice Create(Invoice invoice);
        Invoice UpdateState(int id, string state);
        Invoice GetById(int id);
        Invoice[] GetAllByPayerEmail(string payerEmail);
        Invoice[] GetAllUnpaidByPayerEmail(string email);
        Invoice GetByReservationId(int reservationId);

    }
}
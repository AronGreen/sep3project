using Data.Models.Entities;

namespace Data.Repositories
{
    public interface IInvoiceRepository
    {

        Invoice Create(Invoice invoice);
        Invoice UpdateState(int id, string state);
        Invoice GetById(int id);
        Invoice[] GetAllByEmail(string email);

    }
}
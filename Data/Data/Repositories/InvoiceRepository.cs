using System;
using System.Linq;
using Data.Models.Entities;

namespace Data.Repositories
{
    public class InvoiceRepository : IInvoiceRepository
    {

        public Invoice Create(Invoice invoice)
        {
            using var context = new ApplicationContext();
            try
            {
                var result = context.Invoices.Add(invoice).Entity;
                context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Invoice UpdateState(int id, string state)
        {
            using var context = new ApplicationContext();
            try
            {
                var result = context.Invoices.Single(x => x.Id == id);
                result.State = state;

                result = context.Invoices.Update(result).Entity;
                context.SaveChanges();

                return result;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Invoice GetById(int id)
        {
            using var context = new ApplicationContext();
            try
            {
                return context.Invoices.Single(x => x.Id == id);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Invoice[] GetAllByPayerEmail(string payerEmail)
        {
            using var context = new ApplicationContext();
            try
            {
                return context.Invoices
                    .Select(x => x)
                    .Where(x => x.PayerEmail == payerEmail)
                    .ToArray();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }

        public Invoice[] GetAllUnpaidByPayerEmail(string payerEmail)
        {
            using var context = new ApplicationContext();
            try
            {
                return context.Invoices
                    .Select(x => x)
                    .Where(x => x.PayerEmail == payerEmail && (x.State == "Pending" || x.State == "Rejected"))
                    .ToArray();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public Invoice GetByReservationId(int reservationId)
        {
            using var context = new ApplicationContext();
            try
            {
                return context.Invoices
                    .Single(x => x.ReservationId == reservationId);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return null;
            }
        }
    }
}
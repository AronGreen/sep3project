using Data.Data.Contexts;
using Data.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace Data.Data.Repositories
{
    public class ReservationContext : BaseContext
    {

        public DbSet<Reservation> Reservations { get; set; }

    }
}
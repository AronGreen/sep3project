using Data.Models.Entities;
using FrontEnd.Constants;
using FrontEnd.Helpers;

namespace FrontEnd.ServiceProviders
{
    public class ReservationServiceProvider : IReservationServiceProvider
    {
        public bool Create(Reservation model, string token)
        {
            var response = ApiHelpers.DoPost(Api.Reservations.Create, model, token);
            return response.IsSuccessStatusCode;
        }
    }
}
using System.Collections;
using System.Collections.Generic;
using Data.Data.Repositories;
using Microsoft.EntityFrameworkCore.Query;

namespace Data.Logic
{
    public class RequestTable : IRequestTable
    {

        private readonly IDictionary<(string, string), Handler> _map;

        public RequestTable(
            AccountRequestTableComposer accountComposer,
            TripRequestTableComposer tripComposer,
            ReservationRequestTableComposer reservationComposer)
        {
            _map = new Dictionary<(string, string), Handler>();

            Compose(accountComposer);
            Compose(tripComposer);
            Compose(reservationComposer);
        }

        private void Compose(IRequestTableComposer composer)
        {
            composer.Compose(_map);
        }

        private void AddEntry(string type, string operation, Handler handler)
        {
            _map.Add((type, operation), handler);
        }

        public Handler GetEntry(string type, string operation)
        {
            throw new System.NotImplementedException();
        }
    }
}
}
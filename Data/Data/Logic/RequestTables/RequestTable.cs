using System.Collections.Generic;

namespace Data.Logic.RequestTables
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

        public Handler GetEntry(string type, string operation)
        {
            return _map[(type, operation)];
        }
    }
}
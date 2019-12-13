using System.Collections.Generic;

namespace Data.Logic.RequestTables
{
    interface IRequestTableComposer
    {

        void Compose(IDictionary<(string, string), Handler> map);

    }
}

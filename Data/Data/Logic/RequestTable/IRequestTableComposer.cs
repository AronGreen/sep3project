using System;
using System.Collections.Generic;
using System.Text;

namespace Data.Logic
{
    interface IRequestTableComposer
    {

        void Compose(IDictionary<(string, string), Handler> map);

    }
}

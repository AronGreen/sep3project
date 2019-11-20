using System;
using Data.Data.Contexts;
using Data.Data.Repositories;
using Data.Logic;
using Data.Network;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.DependencyInjection.Extensions;

namespace Data.Composer
{
    public class Composition : IComposition
    {

        private readonly IServiceProvider _serviceProvider;
        
        public Composition()
        {
            _serviceProvider = Compose();
        }

        private IServiceProvider Compose()
        {
            return new ServiceCollection()

                // Add db context
                .AddTransient<ApplicationContext>()

                // Add repositories
                .AddTransient<IAccountRepository, AccountRepository>()
                .AddTransient<ITripRepository, TripRepository>()
                .AddTransient<IReservationRepository, ReservationRepository>()

                // Add request table composers
                .AddTransient<AccountRequestTableComposer>()
                .AddTransient<TripRequestTableComposer>()
                .AddTransient<ReservationRequestTableComposer>()

                // Add request handler table
                .AddTransient<IRequestTable, RequestTable>()

                // Add request handler
                .AddTransient<IRequestHandler, RequestHandler>()

                // Add network handler
                .AddTransient<INetworkHandler, SocketHandler>()

                .BuildServiceProvider();
        }

        public T Get<T>()
        {
            return _serviceProvider.GetService<T>();
        }
    }
}
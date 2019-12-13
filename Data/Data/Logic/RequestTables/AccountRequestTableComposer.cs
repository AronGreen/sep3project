using System.Collections.Generic;
using System.Text.Json;
using Data.Models.Entities;
using Data.Network;
using Data.Repositories;

namespace Data.Logic.RequestTables
{
    public class AccountRequestTableComposer : IRequestTableComposer
    {

        private readonly IAccountRepository _accountRepository;

        public AccountRequestTableComposer(
            IAccountRepository accountRepository)
        {
            _accountRepository = accountRepository;
        }

        public void Compose(IDictionary<(string, string), Handler> map)
        {
            map.Add(("account", "create"), CreateAccount());
            map.Add(("account", "update"), UpdateAccount());
            map.Add(("account", "delete"), DeleteAccount());
            map.Add(("account", "getAll"), GetAll());
            map.Add(("account", "getByEmail"), GetByEmail());
            map.Add(("account", "getPasswordByEmail"), GetPasswordByEmail());
        }

        private Handler CreateAccount() => body =>
        {
            var result = _accountRepository.Create(JsonSerializer.Deserialize<Account>(body));
            var status = result == null ? "alreadyExists" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler UpdateAccount() => body =>
        {
            var result = _accountRepository.Update(JsonSerializer.Deserialize<Account>(body));
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler DeleteAccount() => body =>
        {
            var result = _accountRepository.Delete(body);
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetAll() => body =>
        {
            var result = _accountRepository.GetAll();
            var status = "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetByEmail() => body =>
        {
            var result = _accountRepository.GetByEmail(body);
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

        private Handler GetPasswordByEmail() => body =>
        {
            var result = _accountRepository.GetPasswordByEmail(body);
            var status = result == null ? "notFound" : "success";
            return new Response()
            {
                Status = status,
                Body = result
            };
        };

    }
}
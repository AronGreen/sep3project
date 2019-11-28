using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Data.Models.Entities;


namespace FrontEnd.Pages.Entities
{
    public sealed class GlobalAccess
    {

        private static GlobalAccess instance = null;
        private static readonly object padlock = new object();
        private Account Account;
        private string Token;



        GlobalAccess()
        {
        }

        public static GlobalAccess Instance
        {
            get
            {
                lock (padlock)
                {
                    if (instance == null)
                    {
                        instance = new GlobalAccess();
                    }
                    return instance;
                }
            }
        }
        public void setAccount(Account account) {
            Account = account;
        }
        public Account GetAccount() {
            return Account;
        }

        public void setToken(string token) {
            Token = token;
                    
        }
        public string getToken() {
            return Token;
        }
    }
}


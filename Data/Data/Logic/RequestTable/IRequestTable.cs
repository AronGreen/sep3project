using System;
using Data.Network;

namespace Data.Logic
{

    public delegate Response Handler(string body);

    public interface IRequestTable
    {
        Handler GetEntry(string type, string operation);
    }
}
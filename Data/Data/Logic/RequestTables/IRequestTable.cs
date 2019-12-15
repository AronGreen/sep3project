using Data.Network;

namespace Data.Logic.RequestTables
{

    public delegate Response Handler(string body);

    public interface IRequestTable
    {
        Handler GetEntry(string type, string operation);
    }
}
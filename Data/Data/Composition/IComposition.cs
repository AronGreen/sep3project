using System.Runtime.InteropServices;

namespace Data.Composer
{
    public interface IComposition
    {

        T Get<T>();

    }
}
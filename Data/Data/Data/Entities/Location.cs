using System.Xml;

namespace Data.Data.Entities
{
    public struct Location
    {

        public Location(double x, double y)
        {
            X = x;
            Y = y;
        }

    public double X { get; }
    public double Y { get; }

    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FrontEnd.Pages.Entities
{
    public class Trip
    {
        

        public int Id { get; set; }
        public string Title { get; set; }
        public string Date { get; set; }
        public string StartingPoint { get; set; }
        public string EndingPoint { get; set; }
        public int AvailableSeats { get; set; }
        public double CancellationFee { get; set; }

        public Trip(int id, string title, string date)
        {
            this.Id = id;
            this.Title = title;
            this.Date = date;

        }

        public Trip()
        {

        }

        public Trip(string title, string date, string startingPoint, string endingPoint, int availableSeats)
        {
            this.Title = title;
            this.Date = date;
            this.StartingPoint = startingPoint;
            this.EndingPoint = endingPoint;
            this.AvailableSeats = availableSeats;

        }


    }
}
    
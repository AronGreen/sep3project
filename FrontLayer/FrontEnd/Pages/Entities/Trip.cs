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

        public Trip(int id, string title, string date)
        {
            this.Id = id;
            this.Title = title;
            this.Date = date;

          

        }



        
    }
}

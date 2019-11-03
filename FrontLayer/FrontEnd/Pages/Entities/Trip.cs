using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FrontEnd.Pages.Entities
{
    public class Trip
    {
        

        public int id { get; set; }
        public string title { get; set; }
        public string date { get; set; }

        public Trip(int id, string title, string date)
        {
            this.id = id;
            this.title = title;
            this.date = date;

        }

        
    }
}

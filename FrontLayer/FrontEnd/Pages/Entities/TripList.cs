using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FrontEnd.Pages.Entities
{
    public class TripList
    {

        private List<Trip> trips;

        //So if you wonder, this class constructor puts array of trips to list of trips

        public TripList(params Trip[] trips)
        {
            foreach(Trip trip in trips)
            {
                this.trips.Add(trip);
            }
        }

        public void sortByDate()
        {

        }


    }
}

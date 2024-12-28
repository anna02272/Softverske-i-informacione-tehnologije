using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CinemaApp.Models
{
    public class Projection
    {
        public long Id { get; set; }
        public DateTime DateAndTime { get; set; }
        public Movie Movie { get; set; }
        public int Hall { get; set; }
        public string ProjectionType { get; set; }
        public double TicketPrice { get; set; }
    }
}
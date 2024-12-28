using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Web;

namespace CinemaApp.Models
{
    public class Movie : IEquatable<Movie>
    {
        public long Id { get; set; }
        public string NameSr { get; set; }
        public string DescriptionSr { get; set; }
        public string NameEn { get; set; }
        public string DescriptionEn { get; set; }
        public int ReleasedYear { get; set; }
        public List<Genre> Genres { get; set; }
        public List<Projection> Projections { get; set; }
        public int Duration { get; set; }
        public string ImageUrl { get; set; }

        public Movie() { }

        public Movie(string name, int releasedYear, List<Genre> genres)
        {
            Id = Stopwatch.GetTimestamp();
            NameEn = name;
            NameSr = name;
            ReleasedYear = releasedYear;
            Genres = genres;
        }

        public bool Equals(Movie other)
        {
            return this.Id == other.Id &&
                this.NameEn == other.NameEn &&
                this.NameSr == other.NameSr &&
                this.ReleasedYear == other.ReleasedYear &&
                this.Duration == other.Duration &&
                this.ImageUrl == other.ImageUrl;
        }
    }
}
﻿using CinemaApp.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CinemaApp.Service
{
    public interface IMovieService
    {
        Movie FindOne(long id);
        List<Movie> FindAll();
        Movie Save(Movie movie);
        List<Movie> Save(List<Movie> movies);
        Movie Update(Movie movie);
        Movie Delete(long id);
        List<Movie> Find(string title, long genreId, int minDuration, int maxDuration);
        List<Movie> Find(string title);
        List<Movie> FindByGenreId(long id);
    }
}
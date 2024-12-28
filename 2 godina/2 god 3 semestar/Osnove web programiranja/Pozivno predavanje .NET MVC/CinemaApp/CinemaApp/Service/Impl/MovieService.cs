using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using CinemaApp.Models;
using CinemaApp.Dao;

namespace CinemaApp.Service.Impl
{
    public class MovieService : IMovieService
    {
        private readonly IMovieDAO _movieDAO;

        public MovieService(IMovieDAO movieDAO)
        {
            _movieDAO = movieDAO;
        }

        public Movie Delete(long id)
        {
            return _movieDAO.Delete(id);
        }

        public List<Movie> Find(string title)
        {
            return _movieDAO.Find(title);
        }

        public List<Movie> Find(string title, long genreId, int minDuration, int maxDuration)
        {
            return _movieDAO.Find(title, genreId, minDuration, maxDuration);
        }

        public List<Movie> FindAll()
        {
            return _movieDAO.FindAll();
        }

        public List<Movie> FindByGenreId(long id)
        {
            //return _movieDAO.FindByGenreId(id);
            throw new NotImplementedException();
        }

        public Movie FindOne(long id)
        {
            return _movieDAO.FindOne(id);
        }

        public Movie Save(Movie movie)
        {
            throw new NotImplementedException();
        }

        public List<Movie> Save(List<Movie> movies)
        {
            throw new NotImplementedException();
        }

        public Movie Update(Movie movie)
        {
            throw new NotImplementedException();
        }
    }
}
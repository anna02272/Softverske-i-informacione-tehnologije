using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using CinemaApp.Models;
using CinemaApp.Dao;

namespace CinemaApp.Service.Impl
{
    public class GenreService : IGenreService
    {
        private readonly IGenreDAO _genreDao;

        public GenreService(IGenreDAO genreDao)
        {
            _genreDao = genreDao;
        }

        public Genre Delete(long id)
        {
            throw new NotImplementedException();
        }

        public List<Genre> Find(string name)
        {
            return _genreDao.Find(name);
        }

        public List<Genre> FindAll()
        {
            return _genreDao.FindAll();
        }

        public Genre FindOne(long id)
        {
            return _genreDao.FindOne(id);
        }

        public Genre Save(Genre genre)
        {
            return _genreDao.Save(genre);
        }

        public List<Genre> Save(List<Genre> genres)
        {
            return _genreDao.Save(genres);
        }

        public Genre Update(Genre genre)
        {
            return _genreDao.Update(genre);
        }
    }
}
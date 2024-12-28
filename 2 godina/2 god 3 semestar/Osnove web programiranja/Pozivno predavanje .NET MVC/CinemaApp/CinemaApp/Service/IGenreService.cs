using CinemaApp.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CinemaApp.Service
{
    public interface IGenreService
    {
        Genre FindOne(long id);
        List<Genre> FindAll();
        List<Genre> Find(string name);
        Genre Save(Genre genre);
        List<Genre> Save(List<Genre> genres);
        Genre Update(Genre genre);
        Genre Delete(long id);
    }
}

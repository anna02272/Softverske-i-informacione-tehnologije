using CinemaApp.Models;
using CinemaApp.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace CinemaApp.Controllers
{
    public class MoviesController : Controller
    {
        private IMovieService _movieService;

        public MoviesController(IMovieService movieService)
        {
            _movieService = movieService;
        }
        
        // GET: Movies
        public ActionResult Index()
        {
            List <Movie> movies = _movieService.FindAll();
            return View(movies[0]);
        }
        // GET: Details
        public ActionResult Details(int id)
        {
            Movie movie = _movieService.FindOne(id);

            List<Movie> visitedMovies;
            visitedMovies = Session["Visited"] != null 
                ? (List<Movie>)Session["Visited"] 
                : new List<Movie>();
            if(!visitedMovies.Contains(movie))
            {
                visitedMovies.Add(movie);
            }
            Session["Visited"] = visitedMovies;
            
            return View(movie);
        }
    }
}
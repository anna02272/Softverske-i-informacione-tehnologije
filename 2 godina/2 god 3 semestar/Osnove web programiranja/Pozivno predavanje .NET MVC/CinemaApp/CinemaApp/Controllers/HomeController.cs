using CinemaApp.Service;
using CinemaApp.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace CinemaApp.Controllers
{
    public class HomeController : Controller
    {
        private IMovieService _movieService;

        public HomeController(IMovieService movieService)
        {
            _movieService = movieService;
        }

        public ActionResult Index(string title)
        {
            List<Movie> movies = title == null 
                ? _movieService.FindAll()
                : _movieService.Find(title);
            return View(movies);
        }

        [Route("greetings/{message:maxlength(3)}/{genreId:int}/{releaseYear:int:regex(\\d{4}):range(1888,2021)}/{minDuration:int:range(0,390)}/{maxDuration:int:range(0,390)}")]
        public ActionResult Greet(string message, int genreId, int releaseYear, int minDuration, int maxDuration)
        {
            return Json(new { message, genreId, releaseYear, minDuration, maxDuration },JsonRequestBehavior.AllowGet);
        }

        [Route("filtering/{title}/{genreId:int}/{releaseYear:int:regex(\\d{4}):range(1888,2021)}/{minDuration:int:range(0,390)}/{maxDuration:int:range(0,390)}/{lang?}")]
        public ActionResult FilterMovies(string title, int genreId, int releaseYear, int minDuration, int maxDuration)
        {
            ViewBag.Title = "Filtering";
            List<Movie> filteredMovies = _movieService.Find(title, genreId, minDuration, maxDuration);
            return View("Index",filteredMovies);
            //return Json(filteredMovies, JsonRequestBehavior.AllowGet);
            //return Json(new { title, genreId, releaseYear, minDuration, maxDuration }, JsonRequestBehavior.AllowGet);
        }
        
        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
    }
}
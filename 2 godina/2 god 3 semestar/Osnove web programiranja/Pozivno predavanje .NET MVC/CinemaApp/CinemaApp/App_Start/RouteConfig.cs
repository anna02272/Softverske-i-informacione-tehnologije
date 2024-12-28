using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace CinemaApp
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

            // custom route
            routes.MapRoute(
                "HomeFilter",
                "movies/{title}/{genreId}/{releaseYear}/{minDuration}/{maxDuration}",
                new { controller = "Home", action = "Filter" },
                // validation
                new { releaseYear = @"\d{4}" }
                //new { year = @"2015|2016", releaseYear = @"\d{4}" }
            );
            // attribute route
            routes.MapMvcAttributeRoutes();

            routes.MapRoute(
                name: "Default",
                url: "{controller}/{action}/{id}",
                defaults: new { controller = "Home", action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}

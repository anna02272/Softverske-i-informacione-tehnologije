using CinemaApp.Dao;
using CinemaApp.Dao.Impl;
using CinemaApp.Service;
using CinemaApp.Service.Impl;
using System.Web.Mvc;
using Unity;
using Unity.Mvc5;

namespace CinemaApp
{
    public static class UnityConfig
    {
        public static void RegisterComponents()
        {
			var container = new UnityContainer();

            // register all your components with the container here
            // it is NOT necessary to register your controllers

            // e.g. container.RegisterType<ITestService, TestService>();
            container.RegisterType<IMovieDAO, MovieDAO>();
            container.RegisterType<IMovieService, MovieService>();

            DependencyResolver.SetResolver(new UnityDependencyResolver(container));
        }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using CinemaApp.Dao;
using CinemaApp.Models;

namespace CinemaApp.Service.Impl
{
    public class UserService : IUserService
    {
        private IUserDAO _userDAO;

        public UserService(IUserDAO userDAO)
        {
            _userDAO = userDAO;
        }

        public User Delete(long id)
        {
            throw new NotImplementedException();
        }

        public List<User> Find(string username, string email, string gender, bool administrator)
        {
            throw new NotImplementedException();
        }

        public List<User> FindAll()
        {
            throw new NotImplementedException();
        }

        public User FindOne(string username)
        {
            throw new NotImplementedException();
        }

        public User FindOne(string username, string password)
        {
            throw new NotImplementedException();
        }

        public User Save(User user)
        {
            throw new NotImplementedException();
        }

        public List<Movie> Save(List<User> users)
        {
            throw new NotImplementedException();
        }

        public User Update(User user)
        {
            throw new NotImplementedException();
        }
    }
}
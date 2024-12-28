using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using CinemaApp.Models;

namespace CinemaApp.Dao.Impl
{
    public class UserDAO : IUserDAO
    {
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

        public User Update(User user)
        {
            throw new NotImplementedException();
        }
    }
}
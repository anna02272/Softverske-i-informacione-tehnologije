using CinemaApp.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CinemaApp.Service
{
    public interface IUserService
    {
        User FindOne(string username);
        User FindOne(string username, string password);
        List<User> FindAll();
        User Save(User user);
        List<Movie> Save(List<User> users);
        User Update(User user);
        User Delete(long id);
        List<User> Find(string username, string email, string gender, bool administrator);
    }
}

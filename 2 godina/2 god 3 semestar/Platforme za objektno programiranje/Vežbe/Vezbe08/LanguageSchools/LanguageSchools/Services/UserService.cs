using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LanguageSchools.Models;
using LanguageSchools.CustomExceptions;
using LanguageSchools.Repositories;

namespace LanguageSchools.Services
{
    class UserService : IUserService
    {
        private IUserRepository repostory;

        public UserService()
        {   
            repostory = new UserRepository();
        }

        public List<User> GetActiveUsers()
        {
            return repostory.GetAll().Where(p => p.IsActive).ToList();
        }

        public List<User> GetAll()
        {
            return repostory.GetAll();
        }

        public void Add(User user)
        {
            repostory.Add(user);
        }

        public void Update(int id, User user)
        {
            repostory.Update(id, user);
        }

        public void Delete(int id)
        {
            repostory.Delete(id);
        }
    }
}

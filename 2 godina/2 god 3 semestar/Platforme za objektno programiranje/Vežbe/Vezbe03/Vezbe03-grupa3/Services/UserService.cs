using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Vezbe02.Models;
using Vezbe02.MyExceptions;
using Vezbe02.Repositories;

namespace Vezbe02.Services
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

        public void Set(List<User> users)
        {
            repostory.Set(users);
        }

        public void Update(string email, User user)
        {
            repostory.Update(email, user);
        }

        public void Delete(string email)
        {
            repostory.Delete(email);
        }
    }
}

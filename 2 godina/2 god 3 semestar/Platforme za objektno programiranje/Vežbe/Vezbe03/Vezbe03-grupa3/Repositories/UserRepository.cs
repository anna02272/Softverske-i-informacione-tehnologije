﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using Vezbe02.Models;
using Vezbe02.MyExceptions;

namespace Vezbe02.Repositories
{
    class UserRepository : IUserRepository, IFilePersistence
    {
        private List<User> users;

        public UserRepository()
        {
            users = new List<User>();
        }

        public void Add(User user)
        {
            users.Add(user);
            Save();
        }

        public void Add(List<User> newUsers)
        {
            users.AddRange(newUsers);
            Save();
        }

        public void Set(List<User> newUsers)
        {
            users = newUsers;
        }

        public void Delete(string email)
        {
            User user = GetById(email);

            if(user != null)
            {
                user.IsActive = false;
            }
            else
            {
                throw new UserNotFoundException();
            }

            Save();
        }

        public List<User> GetAll()
        {
            return users;
        }

        public User GetById(string email)
        {
            return users.Find(u => u.Email == email);
        }

        public void Update(string email, User updatedUser)
        {
            User user = GetById(email);

            if(user != null)
            {
                user.Address = updatedUser.Address;
                user.FirstName = updatedUser.FirstName;
                user.LastName = updatedUser.LastName;
                user.UserType = updatedUser.UserType;
            }
            Save();
        }

        public void Save()
        {
            IFormatter formatter = new BinaryFormatter();
            using (Stream stream = new FileStream(Config.usersFilePath, FileMode.Create, FileAccess.Write))
            {
                formatter.Serialize(stream, users);
            }
        }
    }
}

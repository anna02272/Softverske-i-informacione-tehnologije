﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Vezbe02.Models;

namespace Vezbe02.Services
{
    interface IUserService
    {
        List<User> GetAll();
        List<User> GetActiveUsers();
        void Add(User user);
        void Set(List<User> users);
        void Update(string email, User user);
        void Delete(string email);
    }
}

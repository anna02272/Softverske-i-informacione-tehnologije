using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfApp2.Services
{
    interface IUserService
    {
        int SaveUser(Object obj);

        void ReadUsers();

        void DeleteUser(string email);
    }
}

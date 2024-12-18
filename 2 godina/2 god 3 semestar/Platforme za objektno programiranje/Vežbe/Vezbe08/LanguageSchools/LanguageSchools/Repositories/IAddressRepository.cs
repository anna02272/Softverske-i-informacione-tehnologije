using LanguageSchools.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LanguageSchools.Repositories
{
    interface IAddressRepository
    {
        List<Address> GetAll();
        Address GetById(int id);
        int Add(Address address);
        void Update(int id, Address address);
        void Delete(int id);
    }
}

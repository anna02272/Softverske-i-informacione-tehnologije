using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LanguageSchools.Models;

namespace LanguageSchools.Repositories
{
    interface IProfessorRepository
    {
        List<Professor> GetAll();
        Professor GetById(int id);
        int Add(Professor professor);
        void Update(int id, Professor professor);
        void Delete(int id);
    }
}

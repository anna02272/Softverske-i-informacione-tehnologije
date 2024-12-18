using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LanguageSchools.Models;

namespace LanguageSchools.Services
{
    interface IProfessorService
    {
        List<Professor> GetAll();
        Professor GetById(int id);
        List<Professor> GetActiveProfessors();
        List<Professor> GetActiveProfessorsByEmail(string email);
        List<Professor> GetActiveProfessorsOrderedByEmail();
        void Add(Professor professor);
        void Update(int id, Professor professor);
        void Delete(int id);
        List<User> ListAllStudents();
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Vezbe02.Models;

namespace Vezbe02.Services
{
    interface IProfessorService
    {
        List<RegistrovaniKorisnik> ListAllStudents();
    }
}

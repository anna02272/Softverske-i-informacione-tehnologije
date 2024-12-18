using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WpfApp2.Models;

namespace WpfApp2.Services
{
    interface IProfessorService
    {
        List<RegistrovaniKorisnik> ListAllStudents();
    }
}

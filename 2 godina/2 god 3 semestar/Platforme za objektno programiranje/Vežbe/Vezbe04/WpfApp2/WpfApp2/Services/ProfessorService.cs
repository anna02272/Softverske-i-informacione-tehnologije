using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WpfApp2.Models;

namespace WpfApp2.Services
{
    class ProfessorService : IUserService, IProfessorService
    {
        public void DeleteUser(string email)
        {
            throw new NotImplementedException();
        }

        public List<RegistrovaniKorisnik> ListAllStudents()
        {
            throw new NotImplementedException();
        }

        public void ReadUsers(string filename)
        {
            Data.Instance.Profesori = new List<RegistrovaniKorisnik>();
            using (StreamReader file = new StreamReader(@"../../Resources/" + filename))
            {
                string line;

                while ((line = file.ReadLine()) != null)
                {
                    string[] profesorIzFajla = line.Split(';');

                    RegistrovaniKorisnik registrovaniKorisnik = Data.Instance.Korisnici.Find(korisnik => korisnik.Email.Equals(profesorIzFajla[0]));
                    //(Liskov substitution principle)
                    Profesor profesor = new Profesor
                    {
                        Ime = registrovaniKorisnik.Ime,
                        Prezime = registrovaniKorisnik.Prezime,
                        Email = registrovaniKorisnik.Email,
                        Lozinka = registrovaniKorisnik.Lozinka,
                        JMBG = registrovaniKorisnik.JMBG,
                        Pol = registrovaniKorisnik.Pol,
                        TipKorisnika = registrovaniKorisnik.TipKorisnika,
                        Aktivan = registrovaniKorisnik.Aktivan,
                        Korisnik = registrovaniKorisnik
                    };

                    Data.Instance.Profesori.Add(profesor);
                }
            }
        }

        public void SaveUsers(string filename)
        {
            using (StreamWriter file = new StreamWriter(@"../../Resources/" + filename))
            {
                foreach (Profesor profesor in Data.Instance.Profesori)
                {
                    file.WriteLine(profesor.ProfesorZaUpisUFajl());
                }
            }
        }
    }
}

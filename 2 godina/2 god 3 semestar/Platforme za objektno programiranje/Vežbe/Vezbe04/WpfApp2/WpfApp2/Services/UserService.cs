using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WpfApp2.Models;
using WpfApp2.MyExceptions;

namespace WpfApp2.Services
{
    class UserService : IUserService
    {
        public void SaveUsers(string filename)
        {
            using (StreamWriter file = new StreamWriter(@"../../Resources/" + filename))
            {
                foreach (RegistrovaniKorisnik registrovaniKorisnik in Data.Instance.Korisnici)
                {
                    file.WriteLine(registrovaniKorisnik.KorisnikZaUpisUFajl());
                }
            }
        }

        public void ReadUsers(string filename)
        {
            Data.Instance.Korisnici = new List<RegistrovaniKorisnik>();

            using (StreamReader file = new StreamReader(@"../../Resources/" + filename))
            {
                string line;

                while ((line = file.ReadLine()) != null)
                {
                    string[] korisnikIzFajla = line.Split(';');

                    Enum.TryParse(korisnikIzFajla[5], out EPol pol);
                    Enum.TryParse(korisnikIzFajla[6], out ETipKorisnika tip);
                    Boolean.TryParse(korisnikIzFajla[7], out Boolean aktivan);

                    RegistrovaniKorisnik registrovaniKorisnik = new RegistrovaniKorisnik
                    {

                        Ime = korisnikIzFajla[0],
                        Prezime = korisnikIzFajla[1],
                        Email = korisnikIzFajla[2],
                        Lozinka = korisnikIzFajla[3],
                        JMBG = korisnikIzFajla[4],
                        Pol = pol,
                        TipKorisnika = tip,
                        Aktivan = aktivan
                    };

                    Data.Instance.Korisnici.Add(registrovaniKorisnik);
                }
            }

        }

        public void DeleteUser(string email)
        {
            RegistrovaniKorisnik registrovaniKorisnik = Data.Instance.Korisnici.ToList().Find(k => k.Email.Contains(email));
            if (registrovaniKorisnik == null)
            {
                //Console.WriteLine($"Ne postoji taj korisnik sa email adresom {email}" );
                //Ako ne pronadjem korisnika bacam izuzetak
                throw new UserNotFoundException($"Ne postoji taj korisnik sa email adresom {email}");
            }
            registrovaniKorisnik.Aktivan = false;
        }
    }
}

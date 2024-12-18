using System;
using System.Collections.Generic;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using Vezbe02.Services;

namespace Vezbe02.Models
{
    sealed class Data
    {
        private static readonly Data instance = new Data();
        private IUserService userService;
        private IUserService professorService;

        static Data() { }

        private Data()
        {
            userService = new UserService();
            professorService = new ProfessorService();
        }

        public static Data Instance
        {
            get
            {
                return instance;
            }
        }

        public List<RegistrovaniKorisnik> Korisnici { get; set; }
        public List<RegistrovaniKorisnik> Profesori { get; set; }

        public void Initialize()
        {

            Korisnici = new List<RegistrovaniKorisnik>();
            Profesori = new List<RegistrovaniKorisnik>();

            Adresa adresa = new Adresa
            {
                Grad = "Novi Sad",
                Drzava = "Srbija",
                Ulica = "ulica1",
                Broj = "22",
                ID = "1"
            };

            RegistrovaniKorisnik korisnik1 = new RegistrovaniKorisnik();
            korisnik1.Ime = "Pera";
            korisnik1.Prezime = "Peric";
            korisnik1.Email = "pera@gmail.com";
            korisnik1.JMBG = "121346";
            korisnik1.Lozinka = "peki";
            // korisnik1.Adresa = adresa;
            korisnik1.Pol = EPol.MUSKI;
            korisnik1.TipKorisnika = ETipKorisnika.ADMINISTRATOR;
            korisnik1.Aktivan = true;

            RegistrovaniKorisnik korisnik2 = new RegistrovaniKorisnik
            {
                Email = "mika@gmail.com",
                Ime = "mika",
                Prezime = "Mikic",
                JMBG = "121346",
                Lozinka = "zika",
                Pol = EPol.ZENSKI,
                TipKorisnika = ETipKorisnika.PROFESOR,
                Aktivan = true
                //Adresa = adresa
            };

            Profesor korisnik4 = new Profesor
            {
                Korisnik = korisnik2,
            };

            Korisnici.Add(korisnik1);
            Korisnici.Add(korisnik2);
            Profesori.Add(korisnik4);
        }

        public void SacuvajEntitet(string filename)
        {
            if (filename.Contains("korisnici"))
            {
                userService.SaveUsers(filename);
            }
            else if (filename.Contains("profesori"))
            {
                professorService.SaveUsers(filename);
            }
        }
     
        public void CitanjeEntiteta(string filename)
        {
            if (filename.Contains("korisnici"))
            {
                userService.ReadUsers(filename);
            }
            else if (filename.Contains("profesori"))
            {
                professorService.ReadUsers(filename);
            }
        }

        public void SacuvajUBin(string filename)
        {
            IFormatter formatter = new BinaryFormatter();
            using (Stream stream = new FileStream(@"../../Resources/" + filename, FileMode.Create, FileAccess.Write))
            {
                if (filename.Contains("korisnici"))
                {
                    formatter.Serialize(stream, Data.Instance.Korisnici);
                }
                else if (filename.Contains("profesori"))
                {
                    formatter.Serialize(stream, Data.Instance.Profesori);
                }
            }
        }

        public void CitajIzBin(string filename)
        {
            IFormatter formatter = new BinaryFormatter();
            using (Stream stream = new FileStream(@"../../Resources/" + filename, FileMode.Open, FileAccess.Read))
            {
                if (filename.Contains("korisnici"))
                {
                    Korisnici = (List<RegistrovaniKorisnik>)formatter.Deserialize(stream);
                }
                else if (filename.Contains("profesori"))
                {
                    Profesori = (List<RegistrovaniKorisnik>)formatter.Deserialize(stream);
                }
            }
        }

        public void ObrisiKorisnika(string email)
        {
            userService.DeleteUser(email);
        }
    }
}

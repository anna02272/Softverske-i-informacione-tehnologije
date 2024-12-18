using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using WpfApp2.Services;

namespace WpfApp2.Models
{
    sealed class Data
    {
        private static readonly Data instance = new Data();
        public static readonly string CONNECTION_STRING = @"Data Source=(localdb)\MSSQLLocalDB;Initial Catalog=master;Integrated Security=True;Connect Timeout=30;Encrypt=False;TrustServerCertificate=False;ApplicationIntent=ReadWrite;MultiSubnetFailover=False";
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

        public ObservableCollection<RegistrovaniKorisnik> Korisnici { get; set; }
        public ObservableCollection<Profesor> Profesori { get; set; }

        public void Initialize()
        {

            Korisnici = new ObservableCollection<RegistrovaniKorisnik>();
            Profesori = new ObservableCollection<Profesor>();

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
                Korisnik = korisnik2
            };

            Korisnici.Add(korisnik1);
            Korisnici.Add(korisnik2);
            Profesori.Add(korisnik4);
        }

        public int SacuvajEntitet(Object obj)
        {
            if (obj is RegistrovaniKorisnik)
            {
               return userService.SaveUser(obj);
            }
            else if (obj is Profesor)
            {
                return professorService.SaveUser(obj);
            }
            return -1;
        }
     
        public void CitanjeEntiteta(string filename)
        {
            if (filename.Contains("korisnici"))
            {
                userService.ReadUsers();
            }
            else if (filename.Contains("profesori"))
            {
                professorService.ReadUsers();
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
                    Korisnici = (ObservableCollection<RegistrovaniKorisnik>)formatter.Deserialize(stream);
                }
                else if (filename.Contains("profesori"))
                {
                    Profesori = (ObservableCollection<Profesor>)formatter.Deserialize(stream);
                }
            }
        }

        public void ObrisiKorisnika(string email)
        {
            userService.DeleteUser(email);
            //userService.SaveUsers("korisnici.txt");
        }
    }
}

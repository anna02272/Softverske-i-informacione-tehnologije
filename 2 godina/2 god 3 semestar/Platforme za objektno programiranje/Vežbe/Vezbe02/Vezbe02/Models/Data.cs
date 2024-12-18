using System;
using System.Collections.Generic;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;

namespace Vezbe02.Models
{
    sealed class Data
    {
        private static readonly Data instance = new Data();

        static Data() { }

        public static Data Instance
        {
            get
            {
                return instance;
            }
        }

        public List<RegistrovaniKorisnik> Korisnici { get; set; }
        public List<Profesor> Profesori { get; set; }

        public void Initialize()
        {

            Korisnici = new List<RegistrovaniKorisnik>();
            Profesori = new List<Profesor>();

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

            RegistrovaniKorisnik korisnik2 = new RegistrovaniKorisnik
            {
                Email = "mika@gmail.com",
                Ime = "mika",
                Prezime = "Mikic",
                JMBG = "121346",
                Lozinka = "zika",
                Pol = EPol.ZENSKI,
                TipKorisnika = ETipKorisnika.PROFESOR,
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
                SacuvajKorisnike(filename);
            }
            else if (filename.Contains("profesori"))
            {
                SacuvajProfesore(filename);
            }
        }

        private void SacuvajKorisnike(string filename)
        {
            using (StreamWriter file = new StreamWriter(@"../../Resources/" + filename))
            {
                foreach (RegistrovaniKorisnik registrovaniKorisnik in Korisnici)
                {
                    file.WriteLine(registrovaniKorisnik.KorisnikZaUpisUFajl());
                }
            }

        }
        private void SacuvajProfesore(string filename)
        {
            using (StreamWriter file = new StreamWriter(@"../../Resources/" + filename))
            {
                foreach (Profesor profesor in Profesori)
                {
                    file.WriteLine(profesor.ProfesorZaUpisUFajl());
                }
            }
        }

        public void CitanjeEntiteta(string filename)
        {
            if (filename.Contains("korisnici"))
            {
                CitajKorisnike(filename);
            }
            else if (filename.Contains("profesori"))
            {
                CitajProfesore(filename);
            }
        }

        private void CitajKorisnike(string filename)
        {
            Korisnici = new List<RegistrovaniKorisnik>();

            using (StreamReader file = new StreamReader(@"../../Resources/" + filename))
            {
                string line;

                while ((line = file.ReadLine()) != null)
                {
                    string[] korisnikIzFajla = line.Split(';');

                    Enum.TryParse(korisnikIzFajla[5], out EPol pol);
                    Enum.TryParse(korisnikIzFajla[6], out ETipKorisnika tip);

                    RegistrovaniKorisnik registrovaniKorisnik = new RegistrovaniKorisnik
                    {

                        Ime = korisnikIzFajla[0],
                        Prezime = korisnikIzFajla[1],
                        Email = korisnikIzFajla[2],
                        Lozinka = korisnikIzFajla[3],
                        JMBG = korisnikIzFajla[4],
                        Pol = pol,
                        TipKorisnika = tip
                    };

                    Korisnici.Add(registrovaniKorisnik);
                }
            }

        }

        private void CitajProfesore(string filename)
        {
            Profesori = new List<Profesor>();
            using (StreamReader file = new StreamReader(@"../../Resources/" + filename))
            {
                string line;

                while ((line = file.ReadLine()) != null)
                {
                    string[] profesorIzFajla = line.Split(';');

                    RegistrovaniKorisnik registrovaniKorisnik = Korisnici.Find(korisnik => korisnik.Email.Equals(profesorIzFajla[0]));

                    Profesor profesor = new Profesor
                    {
                        Korisnik = registrovaniKorisnik
                    };

                    Profesori.Add(profesor);
                }
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
                    Profesori = (List<Profesor>)formatter.Deserialize(stream);
                }
            }
        }
    }
}

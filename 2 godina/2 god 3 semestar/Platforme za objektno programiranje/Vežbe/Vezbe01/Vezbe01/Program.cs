using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Vezbe01.Models;

namespace Vezbe01
{
    class Program
    {
        public static List<RegistrovaniKorisnik> registrovaniKorisnici = new List<RegistrovaniKorisnik>();
        public static List<Profesor> profesori = new List<Profesor>();
        public static void Initialize()
        {
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
            korisnik1.Adresa = adresa;
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
                Adresa = adresa
            };

            Profesor profesor1 = new Profesor
            {
                Korisnik = korisnik2,
            };

            registrovaniKorisnici.Add(korisnik1);
            registrovaniKorisnici.Add(korisnik2);
            profesori.Add(profesor1);
        }

        public static void PrikazProfesora()
        {
            /*
            for(int i=0; i < profesori.Count; i++)
            {
                Console.WriteLine(profesori[i].ToString());
            }
            */
            foreach (Profesor profesor in profesori)
            {
                Console.WriteLine(profesor.ToString());
            }
        }

        public static void UnosProfesora()
        {
            Adresa adresa = new Adresa
            {
                Broj = "2",
                Drzava = "Drzava 2",
                Grad = "Grad 2",
                Ulica = "Ulica 2",
                ID = "2"
            };

            Console.WriteLine("Unesi ime:");

            string ime = Console.ReadLine();

            Console.WriteLine("Unesi prezime:");
            string prezime = Console.ReadLine();

            Console.WriteLine("Unesi lozinku:");
            string lozinka = Console.ReadLine();

            Console.WriteLine("Unesi JMBG:");
            string jmbg = Console.ReadLine();

            Console.WriteLine("Unesi Email:");
            string email = Console.ReadLine();

            Console.WriteLine("Unesite pol:(MUSKI/ZENSKI/DRUGO)");
            Enum.TryParse(Console.ReadLine(), out EPol pol);

            RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik
            {
                Adresa = adresa,
                Email = email,
                Ime = ime,
                Prezime = prezime,
                JMBG = jmbg,
                Lozinka = lozinka,
                Pol = pol,
                TipKorisnika = ETipKorisnika.PROFESOR
            };

            Profesor profesor = new Profesor
            {
                Korisnik = korisnik

            };

            profesori.Add(profesor);
            registrovaniKorisnici.Add(korisnik);

            Console.WriteLine("Uspesno dodat profesor.");

        }

        public static void IzmenaProfesora()
        {
            Console.WriteLine("Unesite email adresu korisnika:");
            string email = Console.ReadLine();

            int indeks = VratiPozicijuKorisnika(email);
            if (indeks != -1)
            {
                Console.WriteLine("Izmeni ime:"); //moze se menjati koji god atribut
                string novoIme = Console.ReadLine();

                registrovaniKorisnici[indeks].Ime = novoIme;
                Console.WriteLine("Promenjeno ime korisnika.");
            }
            else
            {
                Console.WriteLine("Ne postoji korisnik sa unetom email adresom.");
            }
        }

        public static int VratiPozicijuKorisnika(string email)
        {
            for (int i = 0; i < registrovaniKorisnici.Count; i++)
            {
                if (registrovaniKorisnici[i].Email.Equals(email))
                {
                    return i;
                }
            }
            return -1;
        }
        static void Main(string[] args)
        {
            Initialize();
            string opcija;
            do
            {
                Console.WriteLine("Meni");
                Console.WriteLine("Opcija 1: Prikaz svih profesora");
                Console.WriteLine("Opcija 2: Unos novog profesora");
                Console.WriteLine("Opcija 3: Modifikacija profesora");
                Console.WriteLine("Opcija X: Kraj");
                Console.WriteLine("Opcija>>>");
                opcija = Console.ReadLine();

                switch (opcija)
                {
                    case "1":
                        PrikazProfesora();
                        break;
                    case "2":
                        UnosProfesora();
                        break;
                    case "3":
                        IzmenaProfesora();
                        break;
                    case "X":
                        break;
                    default:
                        Console.WriteLine("Molimo ponovite unos opcije!");
                        break;
                }

            } while (!opcija.Equals("X"));
            Console.ReadLine();

        }
    }
}

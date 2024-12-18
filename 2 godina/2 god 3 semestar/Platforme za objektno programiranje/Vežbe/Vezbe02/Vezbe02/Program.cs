using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Vezbe02.Models;

namespace Vezbe02
{
    class Program
    {
        public static void PrikazProfesora()
        {
            /*
            for(int i=0; i < profesori.Count; i++)
            {
                Console.WriteLine(profesori[i].ToString());
            }
            */
            foreach (Profesor profesor in Data.Instance.Profesori)
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

            Data.Instance.Profesori.Add(profesor);
            Data.Instance.Korisnici.Add(korisnik);

            Data.Instance.SacuvajEntitet("profesori.txt");
            Data.Instance.SacuvajEntitet("korisnici.txt");

            //Data.Instance.SacuvajUBin("korisnici.bin");
            //Data.Instance.SacuvajUBin("profesori.bin");
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

                Data.Instance.Korisnici[indeks].Ime = novoIme;
                Console.WriteLine("Promenjeno ime korisnika.");

                Data.Instance.SacuvajEntitet("profesorii.txt");
                Data.Instance.SacuvajEntitet("korisnici.txt");

                // Data.Instance.SacuvajUBin("korisnici.bin");
                // Data.Instance.SacuvajUBin("profesori.bin");
            }
            else
            {
                Console.WriteLine("Ne postoji korisnik sa unetom email adresom.");
            }
        }

        public static int VratiPozicijuKorisnika(string email)
        {
            for (int i = 0; i < Data.Instance.Korisnici.Count; i++)
            {
                if (Data.Instance.Korisnici[i].Email.Equals(email))
                {
                    return i;
                }
            }
            return -1;
        }
        static void Main(string[] args)
        {
            //inicijalno kreiranje podataka i punjenje fajlova
            Data.Instance.Initialize();

            //citanje iz tekstualnih fajlova
            //Data.Instance.CitanjeEntiteta("korisnici.txt");
            //Data.Instance.CitanjeEntiteta("profesori.txt");

            //citanje iz binarnih fajlova
            //Data.Instance.CitajIzBin("korisnici.bin");
            //Data.Instance.CitajIzBin("instruktori.bin");

            string opcija;
            do
            {
                Console.WriteLine("Meni");
                Console.WriteLine("Opcija 1: Prikaz svih profesora");
                Console.WriteLine("Opcija 2: Unos novog profesora");
                Console.WriteLine("Opcija 3: Modifikacija profesora");
                Console.WriteLine("Opcija 4: Pretraga po email adresi");
                Console.WriteLine("Opcija 5: Sortiranje po email adresi");
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
                    case "4":
                        PretragaEmail();
                        break;
                    case "5":
                        SortiranjeEmail();
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

        private static void PretragaEmail()
        {
            Console.WriteLine("Unesite email adresu za pretragu:");
            string email = Console.ReadLine();

            List<RegistrovaniKorisnik> trazeniKorisnici = Data.Instance.Korisnici.FindAll(k => k.Email.Contains(email));
            PrikazKorisnika(trazeniKorisnici);
        }
        private static void PrikazKorisnika(List<RegistrovaniKorisnik> korisnici)
        {
            foreach (RegistrovaniKorisnik registrovaniKorisnik in korisnici)
            {
                Console.WriteLine(registrovaniKorisnik.ToString());
            }
        }

        private static void SortiranjeEmail()
        {

            PrikazKorisnika(Data.Instance.Korisnici.OrderBy(k => k.Email).ToList());
        }
 }
}

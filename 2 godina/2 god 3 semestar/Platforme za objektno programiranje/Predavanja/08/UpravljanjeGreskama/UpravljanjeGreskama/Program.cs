using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace UpravljanjeGreskama
{
    class Program
    {
        static void Main(string[] args)
        {
            //try
            //{
                Console.Write("Unesite ime korisnika: ");
                string ime = Console.ReadLine();
                Console.Write("Unesite prezime korisnika: ");
                string prezime = Console.ReadLine();
                Korisnik korisnik = new Korisnik(ime, prezime);
                //string inicijali = korisnik.GetInicijaliKorisnikaI();
                //string inicijali = korisnik.GetInicijaliKorisnikaII();
                string inicijali = korisnik.GetInicijaliKorisnikaIII();
                Console.WriteLine(inicijali);
            //}
            //catch (Exception e)
            //{
            //    Console.WriteLine("[main]: Exception");
            //    Console.WriteLine(e);                
            //}
            Console.WriteLine("Program je zavrsen.");
        }
    }
}

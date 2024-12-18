using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OsnoveObjektnogProgramiranja
{
    class Program
    {
        static void Main(string[] args)
        {          
            //kreiranje opstine
            Opstina o = new Opstina();
            o.Naziv = "Novi Sad";
            Console.WriteLine(o.Naziv);

            //kreiranje korisnika
            Korisnik korisnik = new Korisnik("Marko", "Petrovic", 1);

            korisnik.OpstinaStanovanja = o;

            //kreiranje priznanice za januar
            Priznanica priznanicaJan = new Priznanica();
            priznanicaJan.BrojRacuna = "2256";
            priznanicaJan.Datum = new DateTime(2008, 1, 8);
            priznanicaJan.ZaMesec = "Januar";

            //kreiranje stavki
            StavkaPriznanice vodaJan = new StavkaPriznanice("voda", 1000);
            StavkaPriznanice grejanjeJan = new StavkaPriznanice("grejanje", 3500);

            //dodavanje stavki u priznanicu
            priznanicaJan.Stavke.Add(vodaJan);
            priznanicaJan.Stavke.Add(grejanjeJan);

            //kreiranje priznanice za februar
            Priznanica priznanicaFeb = new Priznanica();
            priznanicaFeb.BrojRacuna = "3422";
            priznanicaFeb.Datum = new DateTime(2008, 2, 4);
            priznanicaFeb.ZaMesec = "Februar";

            //kreiranje stavki
            priznanicaFeb.Stavke.Add(new StavkaPriznanice("voda", 2000));
            priznanicaFeb.Stavke.Add(new StavkaPriznanice("grejanje", 4000));
            
            //dodavanje priznanica korisniku
            korisnik.Priznanice.Add(priznanicaJan);
            korisnik.Priznanice.Add(priznanicaFeb);

            //ispis
            foreach (Priznanica priznanica in korisnik.Priznanice)
            {
                Console.WriteLine("Priznanica za mesec: " + 
                    priznanica.ZaMesec);
                foreach (StavkaPriznanice stavka in priznanica.Stavke)
                {
                    Console.WriteLine("\t" + stavka.Naziv + ":" + stavka.Iznos.ToString());
                }
            }
        }
    }
}

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
            //kreiranje korisnika
            Korisnik korisnik = new Korisnik("Marko", "Petrovic", 1);

            //kreiranje priznanice za januar
            Priznanica priznanicaJan = new Priznanica();
            priznanicaJan.BrojRacuna = "2256";
            priznanicaJan.Datum = new DateTime(2008, 1, 8);
            priznanicaJan.ZaMesec = "Januar";

            //kreiranje stavki
            UtrosakGrejanja grejanjeJan = new UtrosakGrejanja(68, 32);
            UtrosakVode vodaJan = new UtrosakVode(14, 4, 54, 1000);

            //dodavanje stavki u priznanicu
            priznanicaJan.Stavke.Add(grejanjeJan);
            priznanicaJan.Stavke.Add(vodaJan);

            //kreiranje priznanice za februar
            Priznanica priznanicaFeb = new Priznanica();
            priznanicaFeb.BrojRacuna = "3422";
            priznanicaFeb.Datum = new DateTime(2008, 2, 4);
            priznanicaFeb.ZaMesec = "Februar";

            //kreiranje stavki
            UtrosakVode vodaFeb = new UtrosakVode(14, 4, 54, 800);
            UtrosakGrejanja grejanjeFeb = new UtrosakGrejanja(68, 32);
            //dodavanje stavki u priznanicu
            priznanicaFeb.Stavke.Add(grejanjeFeb);
            priznanicaFeb.Stavke.Add(vodaFeb);
            priznanicaFeb.Stavke.Add(new TroskoviOdrzavanja(200, 20));

            //dodavanje priznanica korisniku
            korisnik.Priznanice.Add(priznanicaJan);
            korisnik.Priznanice.Add(priznanicaFeb);

            //ispis
            foreach (Priznanica priznanica in korisnik.Priznanice)
            {
                Console.WriteLine("Priznanica za mesec: " + priznanica.ZaMesec);
                foreach (StavkaPriznanice stavka in priznanica.Stavke)
                {
                    Console.WriteLine("\t" + stavka.Naziv + 
                        ":" + stavka.IzracunajIznos().ToString());
                }
            }
        }
    }
}

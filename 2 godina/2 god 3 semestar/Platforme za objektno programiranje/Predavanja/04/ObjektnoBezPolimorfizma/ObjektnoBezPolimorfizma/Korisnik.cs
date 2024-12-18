using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;

namespace OsnoveObjektnogProgramiranja
{
    public class Korisnik
    {
        private string ime;
        private string prezime;
        private int sifra;
        private static int brojKorisnika = 0;
        private List<Priznanica> priznanice;

        public Korisnik(string ime, string prezime, int sifra)
        {
            this.ime = ime;
            this.prezime = prezime;
            this.sifra = sifra;
            priznanice = new List<Priznanica>();

            brojKorisnika++;
        }

        public Korisnik()
        {
            ime = "";
            prezime = "";
            sifra = 0;
            priznanice = new List<Priznanica>();

            brojKorisnika++;
        }

        public string GetInicijaliKorisnika()
        {
            if (ime.Length > 0 && prezime.Length > 0)
                return ime[0] + "." + prezime[0] + ".";
            else
                return null;
        }

        public static int GetBrojKorisnika()
        {
            return brojKorisnika;
        }

        public string GetIme()
        {
            return ime;
        }

        public void SetIme(string ime)
        {
            this.ime = ime;
        }

        public string Prezime
        {
            get
            {
                return prezime;
            }
            set
            {
                prezime = value;
            }
        }

        public List<Priznanica> Priznanice
        {
            get
            {
                return priznanice;
            }
            set
            {
                priznanice = value;
            }
        }
    }
}

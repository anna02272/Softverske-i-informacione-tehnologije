using System;
using System.Collections.Generic;
using System.Text;

namespace UpravljanjeGreskama
{
    public class Korisnik
    {
        private string ime;
        private string prezime;

        public Korisnik(string ime, string prezime)
        {
            this.ime = ime;
            this.prezime = prezime;
        }

        public Korisnik()
        {
            ime = "";
            prezime = "";
        }

        public string GetInicijaliKorisnikaI()
        {
            return ime[0] + "." + prezime[0] + ".";
        }

        public string GetInicijaliKorisnikaII()
        {
            string retValue = "";
            try
            {
                retValue =  ime[0] + "." + prezime[0] + ".";
            }
            catch (Exception e)
            {
                Console.WriteLine("[Korisnik:GetInicijaliKorisnikaII] " + e.Message);
                retValue =  "";
            }

            return retValue;            
        }

        public string GetInicijaliKorisnikaIII()
        {           
            string retVal = "";
            try
            {
                retVal = ime[0] + "." + prezime[0] + ".";
            }
            catch (Exception e)
            {
                Console.WriteLine("[Korisnik:GetInicijaliKorisnikaIII]" + e.Message);
                throw e;
            }
            finally
            {
                Console.WriteLine("[Korisnik:GetInicijaliKorisnikaIII] Finally");
            }
            return retVal;
        }

        public string Ime
        {
            get
            {
                return ime;
            }
            set
            {
                ime = value;
            }
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
    }
}

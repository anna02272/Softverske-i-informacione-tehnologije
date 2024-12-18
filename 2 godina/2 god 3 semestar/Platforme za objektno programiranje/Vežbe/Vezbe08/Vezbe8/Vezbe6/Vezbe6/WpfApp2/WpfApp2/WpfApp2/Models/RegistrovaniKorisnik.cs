using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfApp2.Models
{
    [Serializable]
    public class RegistrovaniKorisnik : IDataErrorInfo
    {
        private string _ime;

        public string Ime
        {
            get { return _ime; }
            set { _ime = value; }
        }

        private string _prezime;

        public string Prezime
        {
            get { return _prezime; }
            set { _prezime = value; }
        }

        private string _JMBG;

        public string JMBG
        {
            get { return _JMBG; }
            set 
            {
                if (Data.Instance.Korisnici.ToList().Exists(k=> k.JMBG.Equals(value)))
                {
                    throw new ArgumentException("Vec postoji taj korisnik!");
                }
                _JMBG = value;
            }
        }

        private EPol _pol;

        public EPol Pol
        {
            get { return _pol; }
            set { _pol = value; }
        }

        private string _email;

        public string Email
        {
            get { return _email; }
            set { _email = value; }
        }

        private string _lozinka;

        public string Lozinka
        {
            get { return _lozinka; }
            set { _lozinka = value; }
        }

        private Adresa _adresa;

        public Adresa Adresa
        {
            get { return _adresa; }
            set { _adresa = value; }
        }


        private ETipKorisnika _tipKorisnika;

        public ETipKorisnika TipKorisnika
        {
            get { return _tipKorisnika; }
            set { _tipKorisnika = value; }
        }

        private bool _aktivan;

        public bool Aktivan
        {
            get { return _aktivan; }
            set { _aktivan = value; }
        }

        public string Error
        {
            get
            {
                return "g";
            }
        }

        public string this[string columnName]
        {
            get
            {
                if (String.IsNullOrEmpty(Ime))
                {
                    return "Ne sme biti prazno polje";
                }
                return "";
            }
        }

        public RegistrovaniKorisnik() { }

        public override string ToString()
        {
            return "Ja sam " + Ime + " " + Prezime +  " moja email adresa je:" + _email;
        }

        public string KorisnikZaUpisUFajl()
        {
            return Ime + ";" + Prezime + ";" + Email + ";" + Lozinka + ";" + JMBG + ";" + Pol + ";" + TipKorisnika + ";" + Aktivan;
        }
    }
}

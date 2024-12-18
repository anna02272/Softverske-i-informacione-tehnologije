using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfApp2.Models
{
    [Serializable]
    public class Profesor
    {

        private int _id;

        public int ID
        {
            get { return _id; }
            set { _id = value; }
        }


        private RegistrovaniKorisnik _korisnik;

        public RegistrovaniKorisnik Korisnik
        {
            get { return _korisnik; }
            set { _korisnik = value; }
        }

        public override string ToString()
        {
            return "Ja sam profesor i moje ime je:" + _korisnik.Ime + ", a moj email je :" + _korisnik.Email;
        }

        public string ProfesorZaUpisUFajl()
        {
            return Korisnik.Email; //cyvamo jedinstveni identifikator profesora (email/JMBG/sifru)
        }
    }
}

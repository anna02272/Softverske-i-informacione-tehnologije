using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Vezbe01.Models
{
    class Profesor
    {
        private RegistrovaniKorisnik _korisnik;

        public RegistrovaniKorisnik Korisnik
        {
            get { return _korisnik; }
            set { _korisnik = value; }
        }

        public override string ToString()
        {
            return "Ja sam instruktor i moje ime je:" + _korisnik.Ime + ", a moj email je :" + _korisnik.Email;
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Dogadjaji
{
    class Primer
    {
        /*DELEGAT*/
        public delegate void ObradjivacIspisa(string tekst);

        public ObradjivacIspisa OI { get; set; }

        public void Ispis(String s)
        {
            if (OI != null)
                OI(s);
        }

        /* DOGADJAJ */
        private string s = "Pocetak";

        public string S
        {
            get
            {
                return s;
            }
            set
            {
                string staraVrednost = s;
                s = value;
                if (PromenjenTekst != null)
                    PromenjenTekst(staraVrednost, value); //bice pozvana metoda postavljena u svojstvo PromenjenTekst
            }
        }

        public delegate void ObradjivacPromeneTeksta(string stariTekst, 
            string noviTekst);
        public event ObradjivacPromeneTeksta PromenjenTekst; //spolja ce biti postavljena metoda u ovo svojstvo. 
                                                             //Metoda mora da ima istu deklaraciju kao ObradjivacPromeneTeksta
    }
}

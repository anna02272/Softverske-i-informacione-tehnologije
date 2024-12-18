using System;
using System.Collections.Generic;
using System.Text;

namespace OsnoveObjektnogProgramiranja
{
    public class UtrosakVode: StavkaPriznanice
    {
        private double jedinicnaCena;
        private int brojClanovaDomacinstva;
        private int brojStanaraUZgradi;
        private double potrosnjaUZgradi;

        public UtrosakVode(double cena, int brojClanova, int brojStanara, double potrosnja)
        {
            naziv = "Voda";
            
            jedinicnaCena = cena;
            brojClanovaDomacinstva = brojClanova;
            brojStanaraUZgradi = brojStanara;
            potrosnjaUZgradi = potrosnja;
        }

        public double IzracunajIznos()
        {
            iznos = (potrosnjaUZgradi / brojStanaraUZgradi) * brojClanovaDomacinstva * jedinicnaCena;

            return Math.Round(iznos, 2);
        }

        public double JedinicnaCena
        {
            get
            {
                return jedinicnaCena;
            }
            set
            {
                jedinicnaCena = value;
            }
        }

        public int BrojClanovaDomacinstva
        {
            get
            {
                return brojClanovaDomacinstva;
            }
            set
            {
                brojClanovaDomacinstva = value;
            }
        }

        public int BrojStanaraUZgradi
        {
            get
            {
                return brojStanaraUZgradi;
            }
            set
            {
                brojStanaraUZgradi = value;
            }
        }

        public double PotrosnjaUZgradi
        {
            get
            {
                return potrosnjaUZgradi;
            }
            set
            {
                potrosnjaUZgradi = value;
            }
        }

    }
}

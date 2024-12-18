using System;
using System.Collections.Generic;
using System.Text;

namespace OsnoveObjektnogProgramiranja
{
    public class TroskoviOdrzavanja: StavkaPriznanice
    {
        private double cena;
        private double taksa;

        public TroskoviOdrzavanja(double cena, double taksa)
        {
            naziv = "Odrzavanje";

            this.cena = cena;
            this.taksa = taksa;
        }        

        public override double IzracunajIznos()
        {
            iznos = cena + taksa;

            return iznos;
        }

        public double Cena
        {
            get
            {
                return cena;
            }
            set
            {
                cena = value;
            }
        }

        public double Taksa
        {
            get
            {
                return taksa;
            }
            set
            {
                taksa = value;
            }
        }
    }
}

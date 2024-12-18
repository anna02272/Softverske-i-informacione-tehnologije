using System;
using System.Collections.Generic;
using System.Text;

namespace OsnoveObjektnogProgramiranja
{
    public class UtrosakGrejanja:StavkaPriznanice
    {
        double kvadraturaStana;
        double jedinicnaCena;

        public UtrosakGrejanja(double kvadratura, double cena)
        {
            naziv = "Grejanje";

            kvadraturaStana = kvadratura;
            jedinicnaCena = cena;
        }

        public override double IzracunajIznos()
        {
            iznos =  kvadraturaStana * jedinicnaCena;

            return Math.Round(iznos, 2);
        }

        public void ispisi()
        {

        }

        public double KvadraturaStana
        {
            get
            {
                return kvadraturaStana;
            }
            set
            {
                kvadraturaStana = value;
            }
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
    }
}

using System;
using System.Collections.Generic;
using System.Text;

namespace OsnoveObjektnogProgramiranja
{
    public class StavkaPriznanice
    {
        protected string naziv;
        protected double iznos;

        public string Naziv
        {
            get
            {
                return naziv;
            }
        }
        
        public double Iznos
        {
            get
            {
                return iznos;
            }
            set
            {
                iznos = value;
            }
        }

        public double IzracunajIznos()
        {
            return iznos;
        }
    }
}

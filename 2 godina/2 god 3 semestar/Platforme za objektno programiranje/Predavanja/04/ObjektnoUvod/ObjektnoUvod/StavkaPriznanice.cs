using System;
using System.Collections.Generic;
using System.Text;

namespace OsnoveObjektnogProgramiranja
{
    public class StavkaPriznanice
    {
        protected string naziv;
        protected double iznos;

        public StavkaPriznanice(string naziv, double iznos)
        {
            this.naziv = naziv;
            this.iznos = iznos;
        }

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
    }
}

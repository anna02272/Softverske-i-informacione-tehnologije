using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;

namespace OsnoveObjektnogProgramiranja
{
    public class Priznanica
    {
        private string brojRacuna;
        private string zaMesec;
        private DateTime datum;
        private List<StavkaPriznanice> stavke = new List<StavkaPriznanice>(); 

        public string BrojRacuna
        {
            get
            {
                return brojRacuna;
            }
            set
            {
                brojRacuna = value;
            }
        }

        public string ZaMesec
        {
            get
            {
                return zaMesec;
            }
            set
            {
                zaMesec = value;
            }
        }

        public DateTime Datum
        {
            get
            {
                return datum;
            }
            set
            {
                datum = value;
            }
        }

        public List<StavkaPriznanice> Stavke
        {
            get
            {
                return stavke;
            }
            set
            {
                stavke = value;
            }
        }
    }
}

using System;
using System.Collections.Generic;
using System.Text;

namespace OsnoveObjektnogProgramiranja
{
    //zadatak
    public class Opstina
    {
        string naziv;
        int brojStanovnika;

        public Opstina(string naziv, int brojStanovnika)
        {
            this.naziv = naziv;
            this.brojStanovnika = brojStanovnika;
        }

        public string Naziv
        {
            get
            {
                return naziv;
            }
            set
            {
                naziv = value;
            }
        }

        public int BrojStanovnika
        {
            get
            {
                return brojStanovnika;
            }
            set
            {
                brojStanovnika = value;
            }
        }
    }
}

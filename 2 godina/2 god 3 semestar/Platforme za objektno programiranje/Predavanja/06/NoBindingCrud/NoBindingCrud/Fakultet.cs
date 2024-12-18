using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.ComponentModel;

namespace Crud
{
    public class Fakultet
    {
        private string naziv;
        private string adresa;

        public Fakultet() { }

        public Fakultet(string naziv, string adresa)
        {
            this.naziv = naziv;
            this.adresa = adresa;
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
        public string Adresa
        {
            get
            {
                return adresa;
            }
            set
            {
                adresa = value;
            }
        }

        public override string ToString()
        {
            return naziv + " " + adresa;
        }        
    }
}

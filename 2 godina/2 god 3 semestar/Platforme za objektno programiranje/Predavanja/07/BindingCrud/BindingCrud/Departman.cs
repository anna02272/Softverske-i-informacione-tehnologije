using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Crud
{
    public class Departman {

        public string Naziv { get; set; }
        public string Opis { get; set; }

        public Departman() { }

        public Departman(string naziv)
        {
            Naziv = naziv;
        }
    }
}

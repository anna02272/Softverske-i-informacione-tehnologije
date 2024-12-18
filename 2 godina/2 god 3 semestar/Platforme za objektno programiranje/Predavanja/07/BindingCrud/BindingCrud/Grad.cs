using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Crud
{
    public class Grad
    {
        public int Ptt { get; set; }
        public string Naziv { get; set; }

        public Grad(int ptt, string naziv)
        {
            Ptt = ptt;
            Naziv = naziv;
        }

        public override string ToString()
        {
            return Ptt + " " + Naziv;
        }
    }
}

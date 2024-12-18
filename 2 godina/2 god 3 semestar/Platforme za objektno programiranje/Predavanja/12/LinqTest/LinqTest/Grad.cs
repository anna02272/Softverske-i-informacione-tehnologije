using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LinqTest
{
    class Grad
    {
        public string Naziv { get; set; }
        public int PttBroj { get; set; }

        public Grad(int pttBroj, string naziv)
        {
            this.PttBroj = pttBroj;
            this.Naziv = naziv;
        }
    }
}

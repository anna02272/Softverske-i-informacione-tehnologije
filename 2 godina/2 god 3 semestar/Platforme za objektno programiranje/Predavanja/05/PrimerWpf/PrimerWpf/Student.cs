using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace PrimerWpf
{
    class Student
    {
        public string Ime { get; set; }
        public string Prezime { get; set; }

        public Student(string ime, string prezime)
        {
            this.Ime = ime;
            this.Prezime = prezime;
        }

        public override string ToString()
        {
            return Ime + " " + Prezime;
        }
    }
}

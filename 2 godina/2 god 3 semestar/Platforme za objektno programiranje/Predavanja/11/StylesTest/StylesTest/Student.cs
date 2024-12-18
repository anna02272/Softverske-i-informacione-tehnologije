using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace StylesTest
{
    class Student
    {
        public Student(string ime, string prezime)
        {
            Ime = ime;
            Prezime = prezime;
        }
        
        public string Ime { get; set; }
        public string Prezime { get; set; }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Metode
{
    class Program
    {
        static void promeni1(int x) {
            x = 5;
        }

        static void promeniGrad(Grad x)
        {
            x.Naziv = "Novi Sad";
        }

        static void promeni2(ref int x)
        {
            x = 5;
        }

        static void promeni3(out int x)
        {
            x = 5;   
        }

        static void Main(string[] args)
        {
            int a = 10;
            promeni1(a);
            Console.WriteLine(a);

            Grad g = new Grad("Beograd");
            promeniGrad(g);
            Console.WriteLine(g.Naziv);

            int b = 10;
            promeni2(ref b);
            Console.WriteLine(b);

            int c;
            promeni3(out c);
            Console.WriteLine(c);
        }
    }
}

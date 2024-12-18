using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Sintaksa
{
    class Program
    {
        enum Dan { Ponedeljak, Utorak };

        public struct TestStruktura
        {
            public int a, b;
        }        
      
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World");
            int p1;
            p1 = 10;
            double p2 = 12.45;

            double decimalni = 10.5;
            int broj = (int) decimalni;
            Console.WriteLine(broj);

            Console.WriteLine("p1 je: " + p1 + ", a p2 je: " + p2);

            //ENUMERACIJA            
            Dan d;
            d = Dan.Ponedeljak;
            Console.WriteLine(d);

            //STRUKTURA
            TestStruktura ts;            
            ts.a = 5;
            Console.WriteLine(ts.a);

            //STRING
            //Deklaracije imaju isto znacenje:
            String s;
            //string s;

            //Operator == poredi po vrednosti (razliciti stringovi su jednaki, ako im je jednak sadrzaj)
            Console.WriteLine("Poredjenje stringova");
            string s1 = "abc";
            string s2 = "abc";
            Console.WriteLine(s1 == s2); 
            //Neizmenjivost: 
            s1 = "Hello ";   
            s2 = s1;  

            s1 += "World";  
            System.Console.WriteLine(s2);  //s2 ima vrednost Hello, jer sada s1 pokazuje na novu lokaciju
            //Operator[]
            Console.WriteLine(s1[3]);
            //Metode klase String
            Console.WriteLine(s1.ToLower());
            Console.WriteLine(s1.ToUpper());
            Console.WriteLine(s1.Insert(2, "Test"));
            Console.WriteLine(s1.Replace(" ", ""));
            Console.WriteLine(s1.Remove(5, 1));
            Console.WriteLine(s1.Substring(2, 5));

            //Celobrojno deljenje
            double rez = 5 / 2; //koliko je rez?
            Console.WriteLine("Rezultat je: " + rez);

            //Ostatak pri deljenju
            int ostatak = 7 % 2;
            double decimalniOstatak = 7.0 % 2.4; 
            Console.WriteLine("Decimalni ostatak: " + decimalniOstatak);

            //Infinity
            double nula = 0.0;
            double beskonacno = 5 / nula;
            Console.WriteLine(beskonacno);

            //NaN
            double nan = nula / nula;
            Console.WriteLine(nan);

            //Increment/decrement
            int x = 10;
            int y = x++ + ++x;
            Console.WriteLine(y); //koja je vrednost y? 
            
            //skracena konjukcija
            bool logicka = (x < 10) && (y++ < 20); //koja je vrednost y nakon izraza?
            Console.WriteLine("Y je: " + y);
            //bit operatori
            int x1 = 3;//11
            int x2 = 2;//10
            int x3 = x1 & x2;//10
            Console.WriteLine("Rezultat bit operacija je: " + x3); //x3 je 2 (10)

            Console.WriteLine("x2 pomereno u desno za jedan bit je: " + (x2 >> 1)); //01
            Console.WriteLine("x2 pomereno u levo za jedan bit je: " + (x2 << 1)); //100 

            //TERNARNI OPERATOR
            int m = 3;
            int n = 3;
            int proba = (m == n) ? 5 : 10;

            //DEFAULT operator
            int br = default(int);
            Console.WriteLine(br);

            //IF
            int i = 5;
            int a = 9;

            if (a == 5 & i++ == 6) 
            {
                Console.WriteLine("Usao u if");
            }

            Console.WriteLine(i);

            switch (i)
            {
                case 1: Console.WriteLine("vrednost je 1");
                    break;
                case 2: Console.WriteLine("vrednost je 2");
                    break;
                default: Console.WriteLine("vrednost je: " + i);
                    break;
            }

            int brojSlovaA = 0;
            string tekst = "danas ";
            for (int k = 0; k < tekst.Length; ++k)
            {
                if (tekst[k] == ' ')
                    break;
                else if (tekst[k] != 'a')
                    continue;

                brojSlovaA++;
            }
            Console.WriteLine(brojSlovaA);
        }        
    }
}
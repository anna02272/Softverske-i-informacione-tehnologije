using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LinqTest
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] nums = new int[] { 2, 0, 1, 6 };            
            var res = from a in nums where a < 3 orderby a select a;

            Console.WriteLine("Prikaz elemenata niza manjih od 3 ");
            foreach (int i in res)
                Console.Write(i + " ");

            Console.WriteLine("\n***********************************");

            //deffered execution - svaki put se iznova izvrsava pri koriscenju
            Console.WriteLine("Primer odlozenog izvrsavanja");
            nums[1] = 2; 
            foreach (int i in res)
                Console.WriteLine(i);

            Console.WriteLine("\n***********************************");

            //pri prebacivanju u listu izvrsi se upit. Nakon toga lista radi sa kopiranim podacima i nema vise vezu sa originalom
            //zato se pri ispisu ne vidi izmenjen element u originalu
            Console.WriteLine("Primer prebacivanja rezultata u listu");
            List<int> lista = res.ToList<int>();
            nums[1] = 0;
            foreach (int i in lista)
                Console.WriteLine(i);

            Console.WriteLine("\n***********************************");
            
            Student s1 = new Student("Petar", "Petrovic");
            Student s2 = new Student("Marko", "Markovic");
            Student s3 = new Student("Zoran", "Petrovic");
            List<Student> studenti = new List<Student>();

            studenti.Add(s1);
            studenti.Add(s2);
            studenti.Add(s3);

            /* select clause */
            Console.WriteLine("Primer koriscenja select klauzule");
            var upitSelect = from s in studenti select s.Ime;

            foreach (string ime in upitSelect)
                Console.WriteLine(ime);

            Console.WriteLine("\n***********************************");

            /* group*/            
            Console.WriteLine("Primer grupisanja elemenata iz rezultata");

            var rez = from s in studenti group s by s.Prezime 
                          into g select g;

            foreach (IGrouping<string, Student> g in rez) {
                Console.WriteLine(g.Key);
                foreach(Student s in g)
                    Console.WriteLine("\t" + s.Ime);
            }
            Console.WriteLine("\n***********************************");

            /* Join */
            s1.Grad = "Novi Sad";
            s2.Grad = "Novi Sad";
            s3.Grad = "Beograd";

            Grad g1 = new Grad(21000, "Novi Sad");
            Grad g2 = new Grad(21000, "Beograd");
            List<Grad> gradovi = new List<Grad>();
            gradovi.Add(g1);
            gradovi.Add(g2);

            var upit = from s in studenti join g in gradovi 
                       on s.Grad equals g.Naziv 
                       select new { ImeStudenta = s.Ime, 
                           PrezimeStudenta=s.Prezime, 
                           PttGrada = g.PttBroj, 
                           NazivGrada = g.Naziv };

            foreach (var u in upit)
                Console.WriteLine(u.ImeStudenta + " " + u.PrezimeStudenta + " " + " " 
                    + u.PttGrada + " " + u.NazivGrada);          
        }
    }
}

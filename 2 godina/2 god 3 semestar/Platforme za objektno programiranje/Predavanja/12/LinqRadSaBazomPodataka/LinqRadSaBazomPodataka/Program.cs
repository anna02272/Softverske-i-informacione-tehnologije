using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Linq;
using System.Data;

namespace LinqRadSaBazomPodataka
{
    class Program
    {
        static void Main(string[] args)
        {
            CRUDOperacije();

            //PovezivanjeEntiteta();            
        }

        private static void CRUDOperacije()
        {
            DataContext dc = new DataContext(@"Integrated Security=true;
                                                  Initial Catalog=ComputerShop;
                                                  Data Source=DESKTOP-LSUKHSK");
          
            Table<Proizvodjac> proizvodjaci = dc.GetTable<Proizvodjac>();                

            #region Unos
            Console.WriteLine("\n\n --------------------- Unos novog proizvodjaca ----------------------");
            Console.Write("Unesite naziv proizvodjaca: ");
            string naziv = Console.ReadLine();

            Proizvodjac noviProizvodjac = new Proizvodjac();
            noviProizvodjac.Naziv = naziv;
            proizvodjaci.InsertOnSubmit(noviProizvodjac);

            dc.SubmitChanges();

            Console.WriteLine("\n\n --------------------- Spisak svih proizvodjaca ----------------------");
            foreach (Proizvodjac p in proizvodjaci)
                Console.WriteLine("ProizvodjacId {0}\nProizvodjacNaziv {1}\n", p.Id, p.Naziv);
            #endregion

            #region Izmena
            Console.WriteLine("\n\n --------------------- Izmena podataka o proizvodjacu ----------------------");
            Console.Write("Unesite ID proizvodjaca ciji naziv zelite da promenite: ");
            int id = Int32.Parse(Console.ReadLine());
            Console.Write("Unesite novi naziv proizvodjaca: ");
            string noviNaziv = Console.ReadLine();

            Proizvodjac proizvodjac = proizvodjaci.Single(p => p.Id == id); //tehnika funkcionalnog programiranja
            /*IEnumerable<Proizvodjac> res = from p in proizvodjaci 
                                           where p.Id == id select p;*/
            //Proizvodjac proizvodjac = res.ElementAt(0);
            proizvodjac.Naziv = noviNaziv;
            dc.SubmitChanges();

            Console.WriteLine("\n\n --------------------- Spisak svih proizvodjaca ----------------------");
            foreach (Proizvodjac p in proizvodjaci)
                Console.WriteLine("ProizvodjacId {0}\nProizvodjacNaziv {1}\n", p.Id, p.Naziv);
            #endregion

            #region Brisanje
            Console.WriteLine("\n\n --------------------- Brisanje proizvodjaca ----------------------");
            Console.Write("Unesite ID proizvodjaca kojeg zelite da obrisete: ");
            id = Int32.Parse(Console.ReadLine());

            //proizvodjac = proizvodjaci.Single(p => p.Id == id);
            IEnumerable<Proizvodjac> delRes = from p in proizvodjaci where p.Id == id select p;
            proizvodjac = delRes.ElementAt(0);
            proizvodjaci.DeleteOnSubmit(proizvodjac);
            dc.SubmitChanges();

            Console.WriteLine("\n\n --------------------- Spisak svih proizvodjaca ----------------------");
            foreach (Proizvodjac p in proizvodjaci)
                Console.WriteLine("ProizvodjacId {0}\nProizvodjacNaziv {1}\n", p.Id, p.Naziv);
            #endregion

            /*
             * select koriscenjem linq
             * 
             */
/*          var res = from p in dc.GetTable<Proizvodjac>() select p;

            foreach (Proizvodjac p in res)
                Console.WriteLine(p.Naziv);*/

            /*
             * select koriscenjem direktnog upita
             * 
             */
            /*
            var res = dc.ExecuteQuery<Proizvodjac>("SELECT * FROM PROIZVODJAC");
            foreach (Proizvodjac p in res)
                Console.WriteLine(p.Naziv);*/



        }

        private static void PovezivanjeEntiteta()
        {
            DataContext dc = new DataContext(@"Integrated Security=true;
                                                  Initial Catalog=ComputerShop;
                                                  Data Source=DESKTOP-LSUKHSK");

            Table<Proizvodjac> proizvodjaci = dc.GetTable<Proizvodjac>();

            Console.WriteLine("\n\n --------------------- Spisak svih komponenata ----------------------");
            Table<Komponenta> komponente = dc.GetTable<Komponenta>();

            foreach (Komponenta k in komponente)
                Console.WriteLine(k.Naziv + " " + k.Proizvodjac.Naziv);

            Console.WriteLine("\n\n --------------------- Spisak svih proizvodjaca i njihovih komponenata ----------------------");
            foreach (Proizvodjac p in proizvodjaci)
            {
                Console.WriteLine("ProizvodjacId {0} ProizvodjacNaziv {1}\n", p.Id, p.Naziv);
                foreach (Komponenta k in p.Komponente)
                    Console.WriteLine("\tKomponentaId {0} KomponentaSifra {1} KomponentaNaziv {2} KomponentaCena {3}\n",
                        k.Id, k.Sifra, k.Naziv, k.Cena);
            }
        }       
    }
}
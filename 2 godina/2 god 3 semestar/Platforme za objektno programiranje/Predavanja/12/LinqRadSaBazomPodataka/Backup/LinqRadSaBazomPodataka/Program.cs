using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Linq;

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
                                                  Data Source=.\SQLExpress");

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

            Proizvodjac proizvodjac = proizvodjaci.Single(p => p.Id == id);
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

            proizvodjac = proizvodjaci.Single(p => p.Id == id);
            proizvodjaci.DeleteOnSubmit(proizvodjac);
            dc.SubmitChanges();

            Console.WriteLine("\n\n --------------------- Spisak svih proizvodjaca ----------------------");
            foreach (Proizvodjac p in proizvodjaci)
                Console.WriteLine("ProizvodjacId {0}\nProizvodjacNaziv {1}\n", p.Id, p.Naziv);
            #endregion

        }

        private static void PovezivanjeEntiteta()
        {
            DataContext dc = new DataContext(@"Integrated Security=true;
                                                  Initial Catalog=ComputerShop;
                                                  Data Source=.\SQLExpress");

            Table<Proizvodjac> proizvodjaci = dc.GetTable<Proizvodjac>();

            Console.WriteLine("\n\n --------------------- Spisak svih komponenti ----------------------");
            Table<Komponenta> komponente = dc.GetTable<Komponenta>();

            foreach (Komponenta k in komponente)
                Console.WriteLine(k.Naziv + " " + k.Proizvodjac.Naziv);

            Console.WriteLine("\n\n --------------------- Spisak svih proizvodjaca i njihovih komponenti ----------------------");
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
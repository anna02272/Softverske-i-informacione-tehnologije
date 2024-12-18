using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.Collections.ObjectModel;
using System.Collections.Specialized;

namespace Crud
{
    class Aplikacija
    {
        public ObservableCollection<Fakultet> Fakulteti { get; set; }
        public List<string> Oblasti { get; set; }
        public ObservableCollection<Grad> Gradovi { get; set; }
        
        //singleton pattern; Jedan objekat klase Aplikacija postoji u celom programu. Svi delovi programa koriste ovaj objekat
        private static Aplikacija instance = new Aplikacija();

        public static Aplikacija Instance
        {
            get
            {
                return instance;
            }
        }

        private Aplikacija()
        {
            Fakulteti = new ObservableCollection<Fakultet>();
            Oblasti = new List<string>();
            Gradovi = new ObservableCollection<Grad>();
            PopuniPodatke();
        }

        private void PopuniPodatke()
        {
            Oblasti.Add("Tehnicko-tehnoloske nauke");
            Oblasti.Add("Drustveno-humanisticke nauke");
            Oblasti.Add("Prirodno-matematicke nauke");
            Oblasti.Add("Medicinske nauke");

            Grad g = new Grad(21000, "Novi Sad");
            Gradovi.Add(g);
            g = new Grad(11000, "Beograd");
            Gradovi.Add(g);
            g = new Grad(18000, "Nis");
            Gradovi.Add(g);
            g = new Grad(24000, "Subotica");
            Gradovi.Add(g);

            Fakultet f = new Fakultet("Fakultet tehnickih nauka", 
                "Trg Dositeja Obradovica 6, Novi Sad", Oblasti[0], Gradovi[0]);
            Departman d = new Departman("Departman za Racunarstvo i automatiku");
            f.Departmani.Add(d);
            d = new Departman("Departman za Matematiku");
            f.Departmani.Add(d);
            Fakulteti.Add(f);

            f = new Fakultet("Pravni fakultet", "Bulevar Kralja Aleksandra 67, Beograd", Oblasti[1], Gradovi[1]);
            d = new Departman("Departman za Međunarodno pravo");
            f.Departmani.Add(d);
            Fakulteti.Add(f);

            f = new Fakultet("Elektronski fakultet", "Aleksandra Medvedeva 14, Nis", Oblasti[0], Gradovi[2]);
            d = new Departman("Departman za Elektroniku");
            f.Departmani.Add(d);
            Fakulteti.Add(f);
        }
    }
}
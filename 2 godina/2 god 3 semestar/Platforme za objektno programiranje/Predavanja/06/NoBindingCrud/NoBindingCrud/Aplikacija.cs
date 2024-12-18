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
        public List<Fakultet> Fakulteti { get; set; }
        
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
            Fakulteti = new List<Fakultet>();
            PopuniPodatke();
        }

        private void PopuniPodatke()
        {
            Fakultet f = new Fakultet(
                "Fakultet tehnickih nauka", "Trg Dositeja Obradovica 6, Novi Sad");
            Fakulteti.Add(f);

            f = new Fakultet("Pravni fakultet", "Bulevar Kralja Aleksandra 67, Beograd");
            Fakulteti.Add(f);

            f = new Fakultet("Elektronski fakultet", "Aleksandra Medvedeva 14, Nis");
            Fakulteti.Add(f);
        }
    }
}
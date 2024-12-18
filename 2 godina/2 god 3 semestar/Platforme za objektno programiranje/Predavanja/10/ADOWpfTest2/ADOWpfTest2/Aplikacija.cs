using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.Collections.ObjectModel;
using System.Collections.Specialized;

namespace ADOWPfTest2
{
    class Aplikacija
    {
        private const string CONNECTION_STRING = @"Integrated Security=true;
                                          Initial Catalog=Fakultet;
                                          Data Source=DESKTOP-LSUKHSK";

        public ObservableCollection<Fakultet> Fakulteti { get; set; }
        public List<Fakultet> ObrisaniFakulteti { get; set; }
        
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
            ObrisaniFakulteti = new List<Fakultet>();
        }        

        public void UcitajFakultete()
        {
            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = CONNECTION_STRING;

                conn.Open();

                Fakultet.Ucitaj(conn);
            }
            //nakon ucitavanja iz baze, reaguje na nove dogadjaje u listi. Kada se desi izmena u listi poziva se metoda Fakulteti_CollectionChanged
            Fakulteti.CollectionChanged += new NotifyCollectionChangedEventHandler(
                Fakulteti_CollectionChanged);
            //reakcija na izmenu svakog pojedinacnog elementa u listi
            foreach (Fakultet f in Fakulteti)
            {
                //kada izmeni jedan element kolekcije Fakulteti, poziva se metoda f_PropertyChanged
                f.PropertyChanged += new 
                    System.ComponentModel.PropertyChangedEventHandler(
                    f_PropertyChanged);
            }
        }

        /// <summary>
        /// Reakcija na dodavanje novog elementa u listu ili brisanje postojeceg elementa iz liste
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void Fakulteti_CollectionChanged(object sender, NotifyCollectionChangedEventArgs e)
        {
            Fakultet f;
            switch (e.Action)
            {
                case NotifyCollectionChangedAction.Add:
                    f = e.NewItems[0] as Fakultet; //f je dodani Fakultet
                    f.Stanje = Fakultet.DbStanje.DODAN; //postavimo stanje ovom objektu, da bi taj objekat kasnije bio ubacen u bazu podataka
                    break;
                case NotifyCollectionChangedAction.Remove:
                    f = e.OldItems[0] as Fakultet; //f je obrisani fakultet
                    f.Stanje = Fakultet.DbStanje.OBRISAN;
                    ObrisaniFakulteti.Add(f); //ubacimo ga u listu obrisanih fakulteta da bi taj objekat kasnije bio izbrisan i iz baze podataka
                    break;
            }
        }

        /// <summary>
        /// Reakcija na izmenu svakog pojedinacnog elementa u listi
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void f_PropertyChanged(object sender, System.ComponentModel.PropertyChangedEventArgs e)
        {
            //element pri izmeni izaziva ovaj dogadjaj
            Fakultet f = sender as Fakultet;//pozivaoc dogadjaja je fakultet koji je izmenjen
            f.Stanje = Fakultet.DbStanje.IZMENJEN;//promenimo stanje elementa koji je menjan da bi kasnije bio izmenjen i u bazi podataka
        }

        public void SnimiFakultete()
        {
            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = CONNECTION_STRING;

                conn.Open();

                Fakultet.Snimi(conn);
            }
        }
    }
}
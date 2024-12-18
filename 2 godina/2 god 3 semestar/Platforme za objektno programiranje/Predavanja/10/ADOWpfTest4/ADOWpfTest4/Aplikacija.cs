using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.Collections.ObjectModel;
using System.Collections.Specialized;

namespace ADOWpfTest4
{
    class Aplikacija
    {
        public const string CONNECTION_STRING = @"Integrated Security=true;
                                          Initial Catalog=Fakultet;
                                          Data Source=DESKTOP-LSUKHSK";

        public ObservableCollection<Fakultet> Fakulteti { get; set; }
        
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
        }

        public void UcitajFakultete() {
            Fakultet.UcitajFakultete();
            //nakon ucitavanja fakulteta, reagujemo na nove dogadjaje u listi
            Fakulteti.CollectionChanged += new NotifyCollectionChangedEventHandler(Fakulteti_CollectionChanged);            
            foreach (Fakultet f in Fakulteti)
                f.PropertyChanged += new System.ComponentModel.PropertyChangedEventHandler(f_PropertyChanged);
        }

        void f_PropertyChanged(object sender, System.ComponentModel.PropertyChangedEventArgs e)
        {
            Fakultet.IzmeniFakultet((Fakultet)sender); //pri izmeni vrednosti nekog svojstva, odmah se vrsi izmena i u bazi
        }

        void Fakulteti_CollectionChanged(object sender, NotifyCollectionChangedEventArgs e)
        {
            switch (e.Action)
            {                
                case NotifyCollectionChangedAction.Add:
                    foreach (Fakultet f in e.NewItems)
                    {
                        f.PropertyChanged+=new System.ComponentModel.PropertyChangedEventHandler(f_PropertyChanged);//i novi fakultet reaguje na izmene
                        Fakultet.DodajFakultet(f);
                    }
                    break;
                case NotifyCollectionChangedAction.Remove:
                    foreach (Fakultet f in e.OldItems)
                        Fakultet.ObrisiFakultet(f);//brisemo iz baze svaki obrisani fakultet
                    break;
            }
        }
    }
}
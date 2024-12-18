using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.Collections.ObjectModel;
using System.Collections.Specialized;

namespace ADOWpfTest3
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
    }
}
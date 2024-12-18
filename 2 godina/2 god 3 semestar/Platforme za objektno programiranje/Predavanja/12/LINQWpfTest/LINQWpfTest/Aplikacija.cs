using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.Collections.ObjectModel;
using System.Collections.Specialized;
using System.Data.Linq;

namespace LINQWpfTest
{
    public class Aplikacija
    {
        public const string CONNECTION_STRING = @"Integrated Security=true;
                                          Initial Catalog=Fakultet;
                                          Data Source=DESKTOP-LSUKHSK";

        DataContext dc;
        public Table<Fakultet> Fakulteti { get; set; }
        
        //singleton pattern; Jedan objekat klase Aplikacija postoji u celom programu. Svi delovi programa koriste ovaj objekat
        private static Aplikacija instance = new Aplikacija();

        public static Aplikacija Instance
        {
            get
            {
                return instance;
            }
        }

        public void UcitajFakultete()
        {
            dc = new DataContext(CONNECTION_STRING);
            Fakulteti = dc.GetTable<Fakultet>();
        }

        public void SnimiFakultete()
        {
            dc.SubmitChanges();
        }

        public void PonistiIzmene(Fakultet fakultet)
        {
            dc.Refresh(RefreshMode.OverwriteCurrentValues, fakultet);
        }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.Collections.ObjectModel;

namespace ADOWPfTest
{
    class Aplikacija
    {
        private const string CONNECTION_STRING = @"Integrated Security=true;
                                          Initial Catalog=Fakultet;
                                          Data Source=DESKTOP-LSUKHSK";
        public DataSet Ds { get; set; }

        private static Aplikacija instance = new Aplikacija();

        private Aplikacija()
        {
            Ds = new DataSet();
        }
        
        public static Aplikacija Instance
        {
            get
            {
                return instance;
            }
        }

        public void UcitajFakultete()
        {
            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = CONNECTION_STRING;

                conn.Open();

                SqlCommand fakultetiCommand = conn.CreateCommand();
                fakultetiCommand.CommandText = @"SELECT * FROM FAKULTET";
                SqlDataAdapter daFakulteti = new SqlDataAdapter();
                daFakulteti.SelectCommand = fakultetiCommand;
                daFakulteti.Fill(Ds, "Fakultet");
            }
        }

        public void SnimiFakultete()
        {
            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = CONNECTION_STRING;

                conn.Open();

                SqlDataAdapter daFakulteti = new SqlDataAdapter(@"SELECT * FROM FAKULTET", conn);
                SqlCommandBuilder builder = new SqlCommandBuilder(daFakulteti);

                daFakulteti.Update(Ds.Tables["Fakultet"]);
            }
        }

        


    }
}
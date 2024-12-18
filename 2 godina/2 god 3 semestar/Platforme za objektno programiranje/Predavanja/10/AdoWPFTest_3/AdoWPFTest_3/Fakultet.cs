using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.ComponentModel;

namespace ADOWpfTest3
{
    public class Fakultet : ICloneable //implementira ovaj interfejs da bi se mogao klonirati fakultet. 
    {
        private decimal id;
        private string naziv;
        private string adresa;

        public decimal Id
        {
            get
            {
                return id;
            }
            set
            {
                id = value;
            }
        }
        public string Naziv 
        { 
            get
            {
                return naziv;
            }
            set
            {
                naziv = value;
            }
        }
        public string Adresa
        {
            get
            {
                return adresa;
            }
            set
            {
                adresa = value;
            }
        }

        /// <summary>
        /// Metoda vrsi kloniranje objekta. Kloniranje pravi kopiju objekta - prebacuje vrednosti svih atributa u novi objekat
        /// </summary>
        /// <returns></returns>
        public object Clone()
        {
            Fakultet kopija = new Fakultet();
            kopija.Id = Id;
            kopija.Naziv = Naziv;
            kopija.Adresa = Adresa;

            return kopija;
        }

        /// <summary>
        /// Preuzimanje svih fakulteta iz baze podataka
        /// </summary>
        /// <param name="connection"></param>
        public static void UcitajFakultete()
        {
            using (SqlConnection connection = new SqlConnection(Aplikacija.CONNECTION_STRING))
            {
                connection.Open();

                DataSet ds = new DataSet();

                SqlCommand fakultetiCommand = connection.CreateCommand();
                fakultetiCommand.CommandText = @"SELECT * FROM FAKULTET";
                SqlDataAdapter daFakulteti = new SqlDataAdapter();
                daFakulteti.SelectCommand = fakultetiCommand;
                daFakulteti.Fill(ds, "Fakultet");

                foreach (DataRow row in ds.Tables["Fakultet"].Rows)
                {
                    Fakultet f = new Fakultet();
                    f.Id = (decimal) row["Id"];
                    f.Naziv = (string) row["Naziv"];
                    f.Adresa = (string) row["Adresa"];

                    Aplikacija.Instance.Fakulteti.Add(f);
                }
            }
        }

        /// <summary>
        /// Dodavanje jednog fakulteta u bazu podataka
        /// </summary>
        /// <param name="conn"></param>
        /// <param name="f"></param>
        public static void DodajFakultet(Fakultet f)
        {
            using (SqlConnection conn = new SqlConnection(Aplikacija.CONNECTION_STRING))
            {
                conn.Open();

                SqlCommand command = conn.CreateCommand();
                command.CommandText = @"INSERT INTO FAKULTET (NAZIV, ADRESA) VALUES (@Naziv, @Adresa)";

                command.Parameters.Add(new SqlParameter("@Naziv", f.Naziv));
                command.Parameters.Add(new SqlParameter("@Adresa", f.Adresa));

                command.ExecuteNonQuery();
            }
        }

        /// <summary>
        /// Brisanje jednog fakulteta iz baze podataka
        /// </summary>
        /// <param name="conn"></param>
        /// <param name="f"></param>
        public static void ObrisiFakultet(Fakultet f)
        {
            using (SqlConnection conn = new SqlConnection(Aplikacija.CONNECTION_STRING))
            {                
                if (f.Id != 0)//ako postoji u bazi
                {
                    conn.Open();

                    SqlCommand command = conn.CreateCommand();
                    command.CommandText = @"DELETE FROM FAKULTET WHERE ID=@Id";

                    command.Parameters.Add(new SqlParameter("@Id", f.Id));

                    command.ExecuteNonQuery();
                }
            }
        }

        /// <summary>
        /// Izmena jednog fakulteta u bazi podataka
        /// </summary>
        /// <param name="conn"></param>
        /// <param name="f"></param>
        public static void IzmeniFakultet(Fakultet f)
        {
            using (SqlConnection conn = new SqlConnection(Aplikacija.CONNECTION_STRING))
            {
                if (f.Id != 0)//ako postoji u bazi
                {
                    conn.Open();

                    SqlCommand command = conn.CreateCommand();
                    command.CommandText = @"UPDATE FAKULTET SET NAZIV=@Naziv, Adresa=@Adresa WHERE ID=@Id";

                    command.Parameters.Add(new SqlParameter("@Id", f.Id));
                    command.Parameters.Add(new SqlParameter("@Naziv", f.Naziv));
                    command.Parameters.Add(new SqlParameter("@Adresa", f.Adresa));

                    command.ExecuteNonQuery();
                }
            }
        }
    }
}

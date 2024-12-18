using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.ComponentModel;

namespace ADOWpfTest4
{
    public class Fakultet:INotifyPropertyChanged 
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
                OnPropertyChanged("Naziv"); //posalji obavestenje da je podatak izmenjen
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
                OnPropertyChanged("Adresa"); //posalji obavestenje da je podatak izmenjen
            }
        }

        public event PropertyChangedEventHandler PropertyChanged; //dogadjaj koji se izaziva pri promeni objekta klase Fakultet

        protected void OnPropertyChanged(string name)
        {
            PropertyChangedEventHandler handler = PropertyChanged;
            if (handler != null)
            {
                handler(this, new PropertyChangedEventArgs(name));
            }
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
                    f.Id = (decimal)row["Id"];
                    f.Naziv = (string)row["Naziv"];
                    f.Adresa = (string)row["Adresa"];

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
                command.CommandText = @"INSERT INTO FAKULTET (NAZIV, ADRESA) VALUES (@Naziv, @Adresa); SET @ID=SCOPE_IDENTITY()";

                command.Parameters.Add(new SqlParameter("@Naziv", f.Naziv != null ? f.Naziv : ""));
                command.Parameters.Add(new SqlParameter("@Adresa", f.Adresa != null ? f.Adresa : ""));
                //izlazni parametar za preuzimanje dobijenog id-a                
                SqlParameter p = new SqlParameter();
                p.ParameterName = "@ID";
                p.SqlDbType = SqlDbType.Int;
                p.Size = 4;
                p.Direction = ParameterDirection.Output;
                command.Parameters.Add(p);

                command.ExecuteNonQuery();

                f.Id = (int)p.Value;
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
                    command.Parameters.Add(new SqlParameter("@Naziv", f.Naziv != null ? f.Naziv : ""));
                    command.Parameters.Add(new SqlParameter("@Adresa", f.Adresa != null ? f.Adresa : ""));

                    command.ExecuteNonQuery();
                }
            }
        }
    }
}

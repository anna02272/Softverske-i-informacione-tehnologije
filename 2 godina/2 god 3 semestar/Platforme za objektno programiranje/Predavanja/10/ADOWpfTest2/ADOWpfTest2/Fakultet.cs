using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.ComponentModel;

namespace ADOWPfTest2
{
    class Fakultet:INotifyPropertyChanged //implementira ovaj interfejs da bi objekat mogao poslati obavestenje kada bude izmenjen
    {
        public enum DbStanje { INICIJALNO, DODAN, OBRISAN, IZMENJEN }; //moguce vrednosti objekta u odnosu na podatke u bazi podataka

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
                OnPropertyChanged("Id"); //posalji obavestenje da je podatak izmenjen
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
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
        }
        
        public DbStanje Stanje { get; set; } //stanje entiteta u odnosu na vrednosti u bazi podataka

        public Fakultet()
        {
            Stanje = DbStanje.INICIJALNO;
        }

        /// <summary>
        /// Preuzimanje svih fakulteta iz baze podataka
        /// </summary>
        /// <param name="connection"></param>
        public static void Ucitaj(SqlConnection connection)
        {
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
                f.Naziv = (string)row["Naziv"];
                f.Adresa = (string)row["Adresa"];

                Aplikacija.Instance.Fakulteti.Add(f);
            }
        }

        /// <summary>
        /// Snimanje u bazu podataka izmena nad kolekcijom fakulteta
        /// </summary>
        /// <param name="connection"></param>
        public static void Snimi(SqlConnection connection)
        {
            foreach (Fakultet f in Aplikacija.Instance.Fakulteti)
            {
                switch (f.Stanje)
                {
                    case DbStanje.DODAN: //novi fakulteti se dodaju u bazi
                        DodajFakultet(connection, f);
                        break;
                    case DbStanje.IZMENJEN: //fakulteti nad kojima je u aplikaciji izvrsena izmena se menjaju i u bazi podataka
                        IzmeniFakultet(connection, f);
                        break;
                }
                f.Stanje = DbStanje.INICIJALNO; //reset stanja nakon operacije nad bazom podataka
            }

            foreach (Fakultet f in Aplikacija.Instance.ObrisaniFakulteti)//obrisani fakulteti se brisu i iz baze podataka
                ObrisiFakultet(connection, f);

            Aplikacija.Instance.ObrisaniFakulteti.Clear();//reset obrisanih fakulteta nakon operacije nad bazom podataka
        }

        /// <summary>
        /// Dodavanje jednog fakulteta u bazu podataka
        /// </summary>
        /// <param name="conn"></param>
        /// <param name="f"></param>
        public static void DodajFakultet(SqlConnection conn, Fakultet f)
        {
            SqlCommand command = conn.CreateCommand();
            command.CommandText = @"INSERT INTO FAKULTET (NAZIV, ADRESA) VALUES (@Naziv, @Adresa)";
            
            command.Parameters.Add(new SqlParameter("@Naziv", f.Naziv));
            command.Parameters.Add(new SqlParameter("@Adresa", f.Adresa));       
            
            command.ExecuteNonQuery();            
        }

        /// <summary>
        /// Brisanje jednog fakulteta iz baze podataka
        /// </summary>
        /// <param name="conn"></param>
        /// <param name="f"></param>
        public static void ObrisiFakultet(SqlConnection conn, Fakultet f)
        {
            if (f.Id != 0)//ako postoji u bazi
            {
                SqlCommand command = conn.CreateCommand();
                command.CommandText = @"DELETE FROM FAKULTET WHERE ID=@Id";

                command.Parameters.Add(new SqlParameter("@Id", f.Id));

                command.ExecuteNonQuery();
            }
        }

        /// <summary>
        /// Izmena jednog fakulteta u bazi podataka
        /// </summary>
        /// <param name="conn"></param>
        /// <param name="f"></param>
        public static void IzmeniFakultet(SqlConnection conn, Fakultet f)
        {
            if (f.Id != 0)//ako postoji u bazi
            {
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

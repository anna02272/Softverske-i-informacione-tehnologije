using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WpfApp2.Models;
using WpfApp2.MyExceptions;

namespace WpfApp2.Services
{
    class UserService : IUserService
    {
        public int SaveUser(Object obj)
        {
            RegistrovaniKorisnik korisnik = obj as RegistrovaniKorisnik;

            using (SqlConnection conn = new SqlConnection(Data.CONNECTION_STRING))
            {
                conn.Open();

                string users = "select * from Korisnici";

                DataSet ds = new DataSet();
                SqlDataAdapter dataAdapter = new SqlDataAdapter(users, conn);
                dataAdapter.Fill(ds, "Korisnici");

                //od objekta(korisnik) kreiram red u tabeli
                DataRow newRow = ds.Tables["Korisnici"].NewRow();
                newRow["Id"] = Data.Instance.Korisnici.Count + 1;
                newRow["Ime"] = korisnik.Ime;
                newRow["Prezime"] = korisnik.Ime;
                newRow["Jmbg"] = korisnik.JMBG;
                newRow["Email"] = korisnik.Email;
                newRow["Lozinka"] = korisnik.Ime;
                newRow["Pol"] = EPol.MUSKI;
                newRow["TipKorisnika"] = ETipKorisnika.PROFESOR;
                newRow["Aktivan"] = true;

                ds.Tables["Korisnici"].Rows.Add(newRow);

                SqlCommandBuilder commandBuilder = new SqlCommandBuilder(dataAdapter);
                dataAdapter.Update(ds.Tables["Korisnici"]);

                return Data.Instance.Korisnici.Count + 1; //vracam id kao povratnu vrednost
            }
        }

        public void ReadUsers()
        {
            Data.Instance.Korisnici = new ObservableCollection<RegistrovaniKorisnik>();

        }

        public void DeleteUser(string email)
        {
            RegistrovaniKorisnik registrovaniKorisnik = Data.Instance.Korisnici.ToList().Find(k => k.Email.Contains(email));
            if (registrovaniKorisnik == null)
            {
                //Console.WriteLine($"Ne postoji taj korisnik sa email adresom {email}" );
                //Ako ne pronadjem korisnika bacam izuzetak
                throw new UserNotFoundException($"Ne postoji taj korisnik sa email adresom {email}");
            }
            registrovaniKorisnik.Aktivan = false;
            this.DeactivateUser(registrovaniKorisnik);
        }

        private void DeactivateUser(RegistrovaniKorisnik korisnik)
        {
            using (SqlConnection conn = new SqlConnection(Data.CONNECTION_STRING))
            {
                conn.Open();
                SqlCommand command = conn.CreateCommand();
                command.CommandText = @"update Korisnici
                                        set Aktivan = @Aktivan
                                        where Email = @Email";
                command.Parameters.Add(new SqlParameter("Aktivan", korisnik.Aktivan));
                command.Parameters.Add(new SqlParameter("Email", korisnik.Email));

                command.ExecuteNonQuery();

                //string users = "select * from Korisnici";
                //DataSet ds = new DataSet();
                //SqlDataAdapter dataAdapter = new SqlDataAdapter(users, conn);
                //dataAdapter.Fill(ds, "Korisnici");

                //foreach (DataRow row in ds.Tables["Korisnici"].Rows)
                //{
                //    if (row["Email"].ToString().Equals(korisnik.Email))
                //    {
                //        row["Aktivan"] = korisnik.Aktivan;
                //    }
                //}

                //SqlCommandBuilder commandBuilder = new SqlCommandBuilder(dataAdapter);
                //dataAdapter.Update(ds.Tables["Korisnici"]);
            }
        }
    }
}

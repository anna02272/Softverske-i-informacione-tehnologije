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

namespace WpfApp2.Services
{
    class ProfessorService : IUserService, IProfessorService
    {
        public void DeleteUser(string email)
        {
            throw new NotImplementedException();
        }

        public List<RegistrovaniKorisnik> ListAllStudents()
        {
            throw new NotImplementedException();
        }

        public void ReadUsers()
        {
            Data.Instance.Profesori = new ObservableCollection<Profesor>();
            Data.Instance.Korisnici = new ObservableCollection<RegistrovaniKorisnik>();

            using (SqlConnection conn = new SqlConnection(Data.CONNECTION_STRING))
            {
                conn.Open();
                DataSet ds = new DataSet();

                string selectedUser = @"select * from Korisnici";
                SqlDataAdapter dataAdapter = new SqlDataAdapter(selectedUser, conn);
                dataAdapter.Fill(ds, "Korisnici");

                foreach (DataRow dataRow in ds.Tables["Korisnici"].Rows)
                {
                    //od reda u tabeli kreiramo objekat(korisnik)
                    RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik
                    {
                        Ime = dataRow["Ime"].ToString(),
                        Prezime = dataRow["Prezime"].ToString(),
                        JMBG = dataRow["Jmbg"].ToString(),
                        Email = dataRow["Email"].ToString(),
                        Lozinka = dataRow["Lozinka"].ToString(),
                        Aktivan = (bool)dataRow["Aktivan"],
                        TipKorisnika = ETipKorisnika.PROFESOR,
                        Pol = EPol.MUSKI
                    };
                    Data.Instance.Korisnici.Add(korisnik);
                }
            }
            
        }

        public int SaveUser(Object obj)
        {

            Profesor profesor = obj as Profesor;

            using (SqlConnection conn = new SqlConnection(Data.CONNECTION_STRING))
            {
                conn.Open();
                SqlCommand command = conn.CreateCommand();

                command.CommandText = @"insert into dbo.Profesori(Id)
                output inserted.id values(@Id)";

                command.Parameters.Add(new SqlParameter("Id", profesor.ID));
                return (int)command.ExecuteScalar();
            }
        }
    }
}

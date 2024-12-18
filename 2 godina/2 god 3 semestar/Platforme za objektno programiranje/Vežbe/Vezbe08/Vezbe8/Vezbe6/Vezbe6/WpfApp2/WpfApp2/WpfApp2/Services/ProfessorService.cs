using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
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
                SqlCommand command = conn.CreateCommand();
                command.CommandText = @"select * from Korisnici where TipKorisnika like 'Profesor'";
                SqlDataReader reader = command.ExecuteReader();

                while (reader.Read())
                {
                    RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik
                    {
                        Ime = reader.GetString(1),
                        Prezime = reader.GetString(2),
                        JMBG = reader.GetString(3),
                        Email = reader.GetString(4),
                        Lozinka = reader.GetString(5),
                        Pol = EPol.MUSKI,
                        TipKorisnika = ETipKorisnika.PROFESOR,
                        Aktivan = reader.GetBoolean(8)
                    };

                    Data.Instance.Korisnici.Add(korisnik);
                }

                reader.Close();
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

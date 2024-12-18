using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
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
                SqlCommand command = conn.CreateCommand();

                command.CommandText = @"insert into dbo.Korisnici(Ime, Prezime,
                 Jmbg, Email, Lozinka, Pol, TipKorisnika, Aktivan)
                output inserted.id values(@Ime, @Prezime, @Jmbg, @Email, @Lozinka,
                @Pol, @TipKorisnika, @Aktivan)";

                command.Parameters.Add(new SqlParameter("Ime", korisnik.Ime));
                command.Parameters.Add(new SqlParameter("Prezime", korisnik.Ime));
                command.Parameters.Add(new SqlParameter("Jmbg", korisnik.JMBG));
                command.Parameters.Add(new SqlParameter("Email", korisnik.Email));
                command.Parameters.Add(new SqlParameter("Lozinka", korisnik.Ime)) ;
                command.Parameters.Add(new SqlParameter("Pol", EPol.MUSKI.ToString()));
                command.Parameters.Add(new SqlParameter("TipKorisnika",korisnik.TipKorisnika.ToString()));

                command.Parameters.Add(new SqlParameter("Aktivan", korisnik.Aktivan));
                return (int)command.ExecuteScalar();
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
        }
    }
}

using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using LanguageSchools.Models;

namespace LanguageSchools.Repositories
{
    class ProfessorRepository : IProfessorRepository
    {
        public int Add(Professor professor)
        {
            using (SqlConnection conn = new SqlConnection(Config.CONNECTION_STRING))
            {
                conn.Open();

                SqlCommand command = conn.CreateCommand();
                command.CommandText = @"
                    insert into dbo.Professors (UserId)
                    output inserted.Id
                    values (@UserId)";

                command.Parameters.Add(new SqlParameter("UserId", professor.UserId));

                return (int)command.ExecuteScalar();
            }
        }

        public void Delete(int id)
        {
        }

        public List<Professor> GetAll()
        {
            List<Professor> professors = new List<Professor>();

            using (SqlConnection conn = new SqlConnection(Config.CONNECTION_STRING))
            {
                string commandText = "select * from dbo.Professors p, dbo.Users u where p.UserId=u.id";
                SqlDataAdapter dataAdapter = new SqlDataAdapter(commandText, conn);

                DataSet ds = new DataSet();

                dataAdapter.Fill(ds, "Professors");

                foreach (DataRow row in ds.Tables["Professors"].Rows)
                {
                    var user = new User
                    {
                        Id = (int)row["UserId"],
                        FirstName = row["FirstName"] as string,
                        LastName = row["LastName"] as string,
                        Email = row["Email"] as string,
                        Password = row["Password"] as string,
                        JMBG = row["Jmbg"] as string,
                        Gender = (EGender)Enum.Parse(typeof(EGender), row["Gender"] as string),
                        UserType = (EUserType)Enum.Parse(typeof(EUserType), row["UserType"] as string),
                        IsActive = (bool)row["IsActive"]
                    };

                    var professor = new Professor
                    {
                        Id = (int)row["id"],
                        User = user
                    };

                    professors.Add(professor);
                }
            }

            return professors;
        }

        public Professor GetById(int id)
        {
            using (SqlConnection conn = new SqlConnection(Config.CONNECTION_STRING))
            {
                string commandText = "select * from dbo.Professors p, dbo.Users u where p.UserId=u.id";
                SqlDataAdapter dataAdapter = new SqlDataAdapter(commandText, conn);

                DataSet ds = new DataSet();

                dataAdapter.Fill(ds, "Professors");

                if (ds.Tables["Professors"].Rows.Count > 0)
                {
                    var row = ds.Tables["Professors"].Rows[0];
                    
                    var user = new User
                    {
                        Id = (int)row["Id"],
                        FirstName = row["FirstName"] as string,
                        LastName = row["LastName"] as string,
                        Email = row["Email"] as string,
                        Password = row["Password"] as string,
                        JMBG = row["Jmbg"] as string,
                        Gender = (EGender)Enum.Parse(typeof(EGender), row["Gender"] as string),
                        UserType = (EUserType)Enum.Parse(typeof(EUserType), row["UserType"] as string),
                        IsActive = (bool)row["IsActive"]
                    };

                    var professor = new Professor
                    {
                        User = user
                    };

                    return professor;
                }
            }

            return null;
        }

        public void Update(int id, Professor professor)
        {
        }
    }
}

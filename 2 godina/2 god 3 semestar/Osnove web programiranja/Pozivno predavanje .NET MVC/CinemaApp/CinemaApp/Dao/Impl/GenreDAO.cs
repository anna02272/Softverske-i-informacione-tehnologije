using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using CinemaApp.Models;

namespace CinemaApp.Dao.Impl
{
    public class GenreDAO : IGenreDAO
    {
        public Genre Delete(long id)
        {
            throw new NotImplementedException();
        }

        public List<Genre> Find(string name)
        {
            throw new NotImplementedException();
        }

        public List<Genre> FindAll()
        {
            List<Genre> genres = new List<Genre>();
            using (SqlConnection connection = new SqlConnection(SqlDataAccess.GetConnectionString()))
            {
                SqlCommand dataCommandGenres = new SqlCommand();
                dataCommandGenres.Connection = connection;

                dataCommandGenres.CommandText = @"SELECT Id, NameSr, NameEN FROM Genres";

                DataSet ds2 = new DataSet();
                SqlDataAdapter custDA2 = new SqlDataAdapter
                {
                    SelectCommand = dataCommandGenres
                };
                custDA2.Fill(ds2, "Genres");
                foreach (DataRow row2 in ds2.Tables["Genres"].Rows)
                {
                    Genre genre = new Genre()
                    {
                        Id = int.Parse(row2["Id"].ToString()),
                        NameEn = row2["NameEn"].ToString(),
                        NameSr = row2["NameSr"].ToString()
                    };
                    genres.Add(genre);
                }
            }
            return genres;
        }

        public Genre FindOne(long id)
        {
            using (SqlConnection connection = new SqlConnection(SqlDataAccess.GetConnectionString()))
            {
                try
                {
                    connection.Open();

                    SqlCommand dataCommand = new SqlCommand();
                    dataCommand.Connection = connection;
                    dataCommand.CommandText = @"SELECT * FROM Genres WHERE Id=@Id";
                    SqlParameter paramId = new SqlParameter("@Id", id);
                    dataCommand.Parameters.Add(paramId);

                    DataSet ds = new DataSet();
                    SqlDataAdapter custDA = new SqlDataAdapter
                    {
                        SelectCommand = dataCommand
                    };

                    custDA.Fill(ds, "Genres");
                    if (ds.Tables["Genres"].Rows.Count == 1)
                    {
                        var row = ds.Tables["Genres"].Rows[0];

                        Genre genre = new Genre()
                        {
                            Id = int.Parse(row["Id"].ToString()),
                            NameEn = row["NameEn"].ToString(),
                            NameSr = row["NameSr"].ToString()
                        };

                        return genre;
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Error accessing the database " + e.Message);
                }
            }
            return null;
        }

        public Genre Save(Genre genre)
        {
            throw new NotImplementedException();
        }

        public List<Genre> Save(List<Genre> genres)
        {
            throw new NotImplementedException();
        }

        public Genre Update(Genre genre)
        {
            throw new NotImplementedException();
        }
    }
}
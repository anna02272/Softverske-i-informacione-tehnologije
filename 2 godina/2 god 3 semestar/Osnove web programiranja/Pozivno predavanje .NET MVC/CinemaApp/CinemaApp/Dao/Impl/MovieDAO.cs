using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using CinemaApp.Models;

namespace CinemaApp.Dao.Impl
{
    public class MovieDAO : IMovieDAO
    {
        public Movie Delete(long id)
        {
            using (SqlConnection connection = new SqlConnection(SqlDataAccess.GetConnectionString()))
            {
                try
                {
                    connection.Open();

                    SqlCommand dataCommand = new SqlCommand();
                    dataCommand.Connection = connection;
                    dataCommand.CommandText = @"DELETE * FROM Movies WHERE Id=@Id";
                    SqlParameter paramId = new SqlParameter("@Id", id);
                    dataCommand.Parameters.Add(paramId);

                    DataSet ds = new DataSet();
                    SqlDataAdapter custDA = new SqlDataAdapter
                    {
                        SelectCommand = dataCommand
                    };

                    custDA.Fill(ds, "Movies");
                    if (ds.Tables["Movies"].Rows.Count == 1)
                    {
                        var row = ds.Tables["Movies"].Rows[0];

                        Movie movie = new Movie()
                        {
                            Id = int.Parse(row["Id"].ToString()),
                            NameEn = row["NameEn"].ToString(),
                            NameSr = row["NameSr"].ToString(),
                            DescriptionEn = row["DescriptionEn"].ToString(),
                            DescriptionSr = row["DescriptionSr"].ToString(),
                            ImageUrl = row["ImageUrl"].ToString(),
                            Duration = int.Parse(row["Duration"].ToString()),
                            ReleasedYear = int.Parse(row["ReleasedYear"].ToString()),
                            Genres = new List<Genre>(),
                            Projections = new List<Projection>()
                        };

                        movie = GetGenres(movie);

                        movie = GetProjections(movie);

                        return movie;
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Error accessing the database " + e.Message);
                }
            }
            return null;
        }

        public List<Movie> Find(string title)
        {
            List<Movie> movies = new List<Movie>();

            using (SqlConnection connection = new SqlConnection(SqlDataAccess.GetConnectionString()))
            {
                try
                {
                    connection.Open();

                    SqlCommand dataCommand = new SqlCommand();
                    dataCommand.Connection = connection;

                    dataCommand.CommandText = @"SELECT * FROM Movies WHERE NameEn LIKE @Title OR NameSr LIKE @Title";

                    SqlParameter paramTitle = new SqlParameter("@Title", '%'+title+'%');
                    dataCommand.Parameters.Add(paramTitle);

                    DataSet ds = new DataSet();
                    SqlDataAdapter custDA = new SqlDataAdapter
                    {
                        SelectCommand = dataCommand
                    };

                    custDA.Fill(ds, "Movies");
                    foreach (DataRow row in ds.Tables["Movies"].Rows)
                    {
                        Movie movie = new Movie()
                        {
                            Id = int.Parse(row["Id"].ToString()),
                            NameEn = row["NameEn"].ToString(),
                            NameSr = row["NameSr"].ToString(),
                            DescriptionEn = row["DescriptionEn"].ToString(),
                            DescriptionSr = row["DescriptionSr"].ToString(),
                            ImageUrl = row["ImageUrl"].ToString(),
                            Duration = int.Parse(row["Duration"].ToString()),
                            ReleasedYear = int.Parse(row["ReleasedYear"].ToString()),
                            Genres = new List<Genre>()
                        };

                        movie = GetGenres(movie);

                        movies.Add(movie);
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Error accessing the database " + e.Message);
                }
            }
            return movies;
        }

        public List<Movie> Find(string title, long genreId, int minDuration, int maxDuration)
        {
            List<Movie> movies = new List<Movie>();

            using (SqlConnection connection = new SqlConnection(SqlDataAccess.GetConnectionString()))
            {
                try
                {
                    connection.Open();

                    SqlCommand dataCommand = new SqlCommand();
                    dataCommand.Connection = connection;

                    dataCommand.CommandText = @"SELECT * FROM Movies, MovieGenre WHERE Id = MovieId AND GenreId = @GenreID AND (NameEn LIKE @Title OR NameSr LIKE @Title) AND (Duration BETWEEN @MinDuration AND @MaxDuration)";

                    dataCommand.Parameters.Add(new SqlParameter("@GenreID", genreId));
                    dataCommand.Parameters.Add(new SqlParameter("@Title", '%' + title + '%'));
                    dataCommand.Parameters.Add(new SqlParameter("@MinDuration", minDuration));
                    dataCommand.Parameters.Add(new SqlParameter("@MaxDuration", maxDuration));

                    DataSet ds = new DataSet();
                    SqlDataAdapter custDA = new SqlDataAdapter
                    {
                        SelectCommand = dataCommand
                    };

                    custDA.Fill(ds, "Movies");
                    foreach (DataRow row in ds.Tables["Movies"].Rows)
                    {
                        Movie movie = new Movie()
                        {
                            Id = int.Parse(row["Id"].ToString()),
                            NameEn = row["NameEn"].ToString(),
                            NameSr = row["NameSr"].ToString(),
                            DescriptionEn = row["DescriptionEn"].ToString(),
                            DescriptionSr = row["DescriptionSr"].ToString(),
                            ImageUrl = row["ImageUrl"].ToString(),
                            Duration = int.Parse(row["Duration"].ToString()),
                            ReleasedYear = int.Parse(row["ReleasedYear"].ToString()),
                            Genres = new List<Genre>()
                        };

                        movie = GetGenres(movie);

                        movies.Add(movie);
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Error accessing the database " + e.Message);
                }
            }
            return movies;
        }

        public List<Movie> FindAll()
        {
            List<Movie> movies = new List<Movie>();

            using (SqlConnection connection = new SqlConnection(SqlDataAccess.GetConnectionString()))
            {
                try
                {
                    connection.Open();

                    SqlCommand dataCommand = new SqlCommand();
                    dataCommand.Connection = connection;
                    
                    dataCommand.CommandText = @"SELECT * FROM Movies";

                    DataSet ds = new DataSet();
                    SqlDataAdapter custDA = new SqlDataAdapter
                    {
                        SelectCommand = dataCommand
                    };

                    custDA.Fill(ds, "Movies");
                    foreach (DataRow row in ds.Tables["Movies"].Rows)
                    {
                        Movie movie = new Movie()
                        {
                            Id = int.Parse(row["Id"].ToString()),
                            NameEn = row["NameEn"].ToString(),
                            NameSr = row["NameSr"].ToString(),
                            DescriptionEn = row["DescriptionEn"].ToString(),
                            DescriptionSr = row["DescriptionSr"].ToString(),
                            ImageUrl = row["ImageUrl"].ToString(),
                            Duration = int.Parse(row["Duration"].ToString()),
                            ReleasedYear = int.Parse(row["ReleasedYear"].ToString()),
                            Genres = new List<Genre>()
                        };

                        movie = GetGenres(movie);

                        movies.Add(movie);
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Error accessing the database " + e.Message);
                }
            }
            return movies;
        }

        public Movie FindOne(long id)
        {
            using (SqlConnection connection = new SqlConnection(SqlDataAccess.GetConnectionString()))
            {
                try
                {
                    connection.Open();

                    SqlCommand dataCommand = new SqlCommand();
                    dataCommand.Connection = connection;
                    dataCommand.CommandText = @"SELECT * FROM Movies WHERE Id=@Id";
                    SqlParameter paramId = new SqlParameter("@Id", id);
                    dataCommand.Parameters.Add(paramId);

                    DataSet ds = new DataSet();
                    SqlDataAdapter custDA = new SqlDataAdapter
                    {
                        SelectCommand = dataCommand
                    };

                    custDA.Fill(ds, "Movies");
                    if (ds.Tables["Movies"].Rows.Count == 1)
                    {
                        var row = ds.Tables["Movies"].Rows[0];

                        Movie movie = new Movie()
                        {
                            Id = int.Parse(row["Id"].ToString()),
                            NameEn = row["NameEn"].ToString(),
                            NameSr = row["NameSr"].ToString(),
                            DescriptionEn = row["DescriptionEn"].ToString(),
                            DescriptionSr = row["DescriptionSr"].ToString(),
                            ImageUrl = row["ImageUrl"].ToString(),
                            Duration = int.Parse(row["Duration"].ToString()),
                            ReleasedYear = int.Parse(row["ReleasedYear"].ToString()),
                            Genres = new List<Genre>(),
                            Projections = new List<Projection>()
                        };

                        movie = GetGenres(movie);
                        
                        movie = GetProjections(movie);

                        return movie;
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Error accessing the database " + e.Message);
                }
            }
            return null;
        }

        public Movie Save(Movie movie)
        {
            throw new NotImplementedException();
        }

        public List<Movie> Save(List<Movie> movies)
        {
            throw new NotImplementedException();
        }

        public Movie Update(Movie movie)
        {
            throw new NotImplementedException();
        }

        private Movie GetGenres(Movie movie)
        {
            using (SqlConnection connection = new SqlConnection(SqlDataAccess.GetConnectionString()))
            {
                SqlCommand dataCommandGenres = new SqlCommand();
                dataCommandGenres.Connection = connection;

                dataCommandGenres.CommandText = @"SELECT Id, NameSr, NameEN, MovieId FROM Genres g
	                        LEFT JOIN MovieGenre mg ON mg.MovieId = @MovieId AND mg.GenreId = g.Id
		                        WHERE MovieId = @MovieId";
                SqlParameter paramMovieId = new SqlParameter("@MovieId", movie.Id);
                dataCommandGenres.Parameters.Add(paramMovieId);

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
                    movie.Genres.Add(genre);
                }
                return movie;
            }
        }

        private Movie GetProjections(Movie movie)
        {
            using (SqlConnection connection = new SqlConnection(SqlDataAccess.GetConnectionString()))
            {
                SqlCommand dataCommandProjections = new SqlCommand();
                dataCommandProjections.Connection = connection;

                dataCommandProjections.CommandText = @"SELECT p.Id, p.DateAndTime, p.MovieId, p.Hall, pt.Name as ProjectionType, p.TicketPrice
                            FROM Projections p
                              LEFT JOIN ProjectionTypes pt ON p.ProjectionType = pt.Id
	                            WHERE p.MovieId = 1
                                  ORDER BY p.DateAndTime";
                SqlParameter paramMovieId2 = new SqlParameter("@MovieId", movie.Id);
                dataCommandProjections.Parameters.Add(paramMovieId2);

                DataSet ds3 = new DataSet();
                SqlDataAdapter custDA3 = new SqlDataAdapter
                {
                    SelectCommand = dataCommandProjections
                };
                custDA3.Fill(ds3, "Projections");
                foreach (DataRow row3 in ds3.Tables["Projections"].Rows)
                {
                    Projection projection = new Projection()
                    {
                        Id = int.Parse(row3["Id"].ToString()),
                        DateAndTime = DateTime.Parse(row3["DateAndTime"].ToString()),
                        Movie = movie,
                        Hall = int.Parse(row3["Hall"].ToString()),
                        TicketPrice = double.Parse(row3["TicketPrice"].ToString()),
                        ProjectionType = row3["ProjectionType"].ToString(),
                    };
                    movie.Projections.Add(projection);
                }
                return movie;
            }
        }
    }
}
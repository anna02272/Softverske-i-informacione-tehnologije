using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;

namespace ADORadSaBazomPodataka
{
    class Program
    {
        static void Main(string[] args)
        {
            using (SqlConnection dataConnection = new SqlConnection()) //automatski se konekcija zatvara pri unistavanju objekta 
            {
                try
                {
                    dataConnection.ConnectionString = @"Integrated Security=true;
                                                      Initial Catalog=ComputerShop;
                                                      Data Source=DESKTOP-LSUKHSK";
                    dataConnection.Open();

                    SqlCommand dataCommand = new SqlCommand();
                    dataCommand.Connection = dataConnection;                    

                    //TestTransactions(dataConnection);

                    #region Unos
                    Console.WriteLine("\n\n --------------------- Unos novog proizvodjaca ----------------------");
                    Console.Write("Unesite naziv proizvodjaca: ");
                    string naziv = Console.ReadLine();

                    SqlParameter param;

                    // prvi nacin
                    /*dataCommand.CommandText = @"INSERT INTO PROIZVODJAC " 
                        + "(NAZIV) VALUES ('" + naziv + "')";*/

                    // drugi nacin
                    dataCommand.CommandText = @"INSERT INTO PROIZVODJAC "
                            + "(NAZIV) VALUES (@Naziv)";//sprecavanje sql-injection
                    param = new SqlParameter("@Naziv", naziv);
                    dataCommand.Parameters.Add(param);
                    dataCommand.ExecuteNonQuery();

                    IspisProizvodjaca(dataCommand);
                    #endregion

                    #region Izmena
                    Console.WriteLine("\n\n --------------------- Izmena podataka o proizvodjacu ----------------------");
                    Console.Write("Unesite ID proizvodjaca ciji naziv zelite da promenite: ");
                    string noviId = Console.ReadLine();
                    Console.Write("Unesite novi naziv proizvodjaca: ");
                    string noviNaziv = Console.ReadLine();

                    dataCommand = new SqlCommand();
                    dataCommand.Connection = dataConnection;
                    dataCommand.CommandText = @"UPDATE PROIZVODJAC SET naziv=@Naziv WHERE ID=@Id";
                    param = new SqlParameter("@Naziv", noviNaziv);
                    dataCommand.Parameters.Add(param);
                    SqlParameter paramId = new SqlParameter("@Id", noviId);
                    dataCommand.Parameters.Add(paramId);
                    dataCommand.ExecuteNonQuery();

                    IspisProizvodjaca(dataCommand);
                    #endregion

                    #region Brisanje
                    Console.WriteLine("\n\n --------------------- Brisanje proizvodjaca ----------------------");
                    Console.Write("Unesite ID proizvodjaca koji zelite da obrisete: ");
                    string id = Console.ReadLine();

                    dataCommand = new SqlCommand();
                    dataCommand.Connection = dataConnection;
                    dataCommand.CommandText = @"DELETE FROM PROIZVODJAC WHERE id=@Id";
                    paramId = new SqlParameter("@Id", id);
                    dataCommand.Parameters.Add(paramId);
                    dataCommand.ExecuteNonQuery();
                    #endregion

                    IspisProizvodjaca(dataCommand);

                    /*DeleteRow(dataConnection);
                    IspisProizvodjaca(dataCommand);*/
                }
                catch (Exception e)
                {
                    Console.WriteLine("Error accessing the database " + e.Message);

                }
            }
        }

        private static void IspisProizvodjaca(SqlCommand dataCommand)
        {
            Console.WriteLine("\n\n --------------------- Spisak svih proizvodjaca ----------------------");
            dataCommand.CommandText = @"SELECT * FROM PROIZVODJAC";

            DataSet ds = new DataSet();
            SqlDataAdapter custDA = new SqlDataAdapter();
            custDA.SelectCommand = dataCommand;

            custDA.Fill(ds,"Proizvodjac");            

            foreach (DataRow row in ds.Tables["Proizvodjac"].Rows)            
            {
                Console.WriteLine("ProizvodjacId " + 
                    row["ID"] + "\nProizvodjacNaziv " + row["Naziv"]);
            }
            //drugi nacin
            /*SqlDataReader dataReader = dataCommand.ExecuteReader();

            while (dataReader.Read())
            {
                int proizvodjacId = dataReader.GetInt32(0);
                string proizvodjacNaziv = dataReader.GetString(1);

                Console.WriteLine(
                    "ProizvodjacId {0}\nProizvodjacNaziv {1}\n", 
                    proizvodjacId, proizvodjacNaziv);
            }

            dataReader.Close();*/
        }

        private static void TestTransactions(SqlConnection connection)
        {
            SqlTransaction transaction = null;
            try
            {
                transaction = connection.BeginTransaction();
                SqlCommand command = new SqlCommand();
                command.Connection = connection;
                command.Transaction = transaction;
                
                command.CommandText = @"INSERT INTO PROIZVODJAC (NAZIV) VALUES ('Acer')";
                command.ExecuteNonQuery();
                command.CommandText = @"INSERT INTO PROIZVODJAC (NAZIV) VALUES ('Toshiba')";
                command.ExecuteNonQuery();                
                
                transaction.Commit();
            }
            catch (Exception e)
            {
                transaction.Rollback();
                Console.WriteLine(e.StackTrace);
            }
        }

        private static void DeleteRow(SqlConnection connection)
        {
            SqlCommand selectCommand = connection.CreateCommand();
            selectCommand.CommandText = @"SELECT * FROM PROIZVODJAC";

            SqlCommand deleteCommand = connection.CreateCommand();
            deleteCommand.CommandText = @"DELETE FROM PROIZVODJAC WHERE ID=@ID";
            SqlParameter idParam = deleteCommand.Parameters.Add("@ID", SqlDbType.Int);
            idParam.SourceColumn = "ID"; //iz koje kolone iz reda u data setu ce vrednost parametra biti preuzeta
            
            DataSet ds = new DataSet();
            SqlDataAdapter custDA = new SqlDataAdapter();
            custDA.SelectCommand = selectCommand;
            custDA.Fill(ds, "Proizvodjac");

            custDA.DeleteCommand = deleteCommand;
            ds.Tables[0].Rows[0].Delete();

            custDA.Update(ds,"Proizvodjac");
        }
    }
}
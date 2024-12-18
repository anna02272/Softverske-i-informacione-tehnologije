using LanguageSchools.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LanguageSchools.Repositories
{
    class AddressRepository : IAddressRepository
    {
        public int Add(Address address)
        {
            using (SqlConnection conn = new SqlConnection(Config.CONNECTION_STRING))
            {
                conn.Open();

                SqlCommand command = conn.CreateCommand();
                command.CommandText = @"
                    insert into dbo.Addresses (Street, StreetNumber, City, Country)
                    output inserted.Id
                    values (@Street, @StreetNumber, @City, @Country";

                command.Parameters.Add(new SqlParameter("Street", address.Street));
                command.Parameters.Add(new SqlParameter("StreetNumber", address.StreetNumber));
                command.Parameters.Add(new SqlParameter("City", address.City));
                command.Parameters.Add(new SqlParameter("Country", address.Country));

                return (int)command.ExecuteScalar();
            }
        }

        public void Delete(int id)
        {
            using (SqlConnection conn = new SqlConnection(Config.CONNECTION_STRING))
            {
                conn.Open();

                SqlCommand command = conn.CreateCommand();
                command.CommandText = "update dbo.Addresses set IsDeleted=1 where Id=@id";

                command.Parameters.Add(new SqlParameter("id", id));
                command.ExecuteNonQuery();
            }
        }

        public List<Address> GetAll()
        {
            List<Address> addresses = new List<Address>();

            using (SqlConnection conn = new SqlConnection(Config.CONNECTION_STRING))
            {
                string commandText = "select * from dbo.Addresses";
                SqlDataAdapter dataAdapter = new SqlDataAdapter(commandText, conn);

                DataSet ds = new DataSet();

                dataAdapter.Fill(ds, "Addresses");

                foreach (DataRow row in ds.Tables["Addresses"].Rows)
                {
                    var address = new Address
                    {
                        Id = (int)row["Id"],
                        Street = row["Street"] as string,
                        StreetNumber = row["StreetNumber"] as string,
                        City = row["City"] as string,
                        Country = row["Country"] as string,
                    };

                    addresses.Add(address);
                }
            }

            return addresses;
        }

        public Address GetById(int id)
        {
            using (SqlConnection conn = new SqlConnection(Config.CONNECTION_STRING))
            {
                string commandText = $"select * from dbo.Address where Id={id}";
                SqlDataAdapter dataAdapter = new SqlDataAdapter(commandText, conn);

                DataSet ds = new DataSet();

                dataAdapter.Fill(ds, "Address");
                if (ds.Tables["Address"].Rows.Count > 0)
                {
                    var row = ds.Tables["Address"].Rows[0];

                    var address = new Address
                    {
                        Id = (int)row["Id"],
                        Street = row["Street"] as string,
                        StreetNumber = row["StreetNumber"] as string,
                        City = row["City"] as string,
                        Country = row["Country"] as string,
                    };

                    return address;
                }
            }

            return null;
        }

        public void Update(int id, Address address)
        {
            using (SqlConnection conn = new SqlConnection(Config.CONNECTION_STRING))
            {
                conn.Open();

                SqlCommand command = conn.CreateCommand();
                command.CommandText = @"update dbo.Addresses set 
                        Street = @Street,
                        StreetNumber = @StreetNumber,
                        City = @City,
                        Country = @Country
                        where Id=@id";

                command.Parameters.Add(new SqlParameter("id", id));
                command.Parameters.Add(new SqlParameter("Street", address.Street));
                command.Parameters.Add(new SqlParameter("StreetNumber", address.StreetNumber));
                command.Parameters.Add(new SqlParameter("City", address.City));
                command.Parameters.Add(new SqlParameter("Country", address.Country));

                command.ExecuteNonQuery();
            }
        }
    }
}

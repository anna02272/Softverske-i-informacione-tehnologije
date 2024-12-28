using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace CinemaApp.Dao
{
    public class SqlDataAccess
    {
        public static string GetConnectionString(string connectionName = "CinemaDB")
        {
            return ConfigurationManager.ConnectionStrings[connectionName].ConnectionString;
        }
        
    }
}
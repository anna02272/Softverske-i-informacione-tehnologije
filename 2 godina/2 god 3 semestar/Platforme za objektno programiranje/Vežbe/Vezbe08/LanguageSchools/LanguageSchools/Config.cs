using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Contexts;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Markup;

namespace LanguageSchools
{
    class Config
    {
        public static readonly string CONNECTION_STRING = @" Data Source = LAPTOP - SC9RDVNE; Initial Catalog=languageSchooldb; Integrated Security = True; Connect Timeout = 30; Encrypt=False;TrustServerCertificate=False;ApplicationIntent=ReadWrite;MultiSubnetFailover=False;";
    //    public static readonly string CONNECTION_STRING = @"Data Source=(localdb)\MSSQLLocalDB;Initial Catalog=LanguageSchoolsDB;Integrated Security=True;Connect Timeout=30;Encrypt=False;";
    }
}

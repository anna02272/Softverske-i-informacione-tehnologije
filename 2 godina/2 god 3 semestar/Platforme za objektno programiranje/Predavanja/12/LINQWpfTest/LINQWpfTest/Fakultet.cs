using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.ComponentModel;
using System.Data.Linq;
using System.Data.Linq.Mapping;

namespace LINQWpfTest
{
    [Table(Name = "FAKULTET")]
    public class Fakultet
    {
        [Column(IsPrimaryKey = true, Name = "ID", CanBeNull = false, IsDbGenerated = true)]
        public decimal Id {get; set;}

        [Column(Name = "NAZIV", CanBeNull = true)]
        public string Naziv {get; set;}

        [Column(Name = "ADRESA", CanBeNull = true)]
        public string Adresa {get; set;}
    }
}

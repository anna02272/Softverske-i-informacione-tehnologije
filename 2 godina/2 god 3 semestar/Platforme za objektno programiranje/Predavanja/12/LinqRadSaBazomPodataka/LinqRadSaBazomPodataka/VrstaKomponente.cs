using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data.Linq;
using System.Data.Linq.Mapping;

namespace LinqRadSaBazomPodataka
{
    [Table(Name = "VRSTA_KOMPONENTE")]
    public class VrstaKomponente
    {
        [Column(IsPrimaryKey = true, Name = "ID", CanBeNull = false, IsDbGenerated = true)]
        public int Id
        {
            get;
            set;
        }

        [Column(Name = "NAZIV")]
        public string Naziv
        {
            get;
            set;
        }
    }

}

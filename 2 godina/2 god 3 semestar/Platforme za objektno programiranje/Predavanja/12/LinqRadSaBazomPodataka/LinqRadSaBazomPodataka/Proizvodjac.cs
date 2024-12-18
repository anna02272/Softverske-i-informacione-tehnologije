using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data.Linq;
using System.Data.Linq.Mapping;

namespace LinqRadSaBazomPodataka
{
    [Table(Name="PROIZVODJAC")]
    public class Proizvodjac
    {
        [Column(IsPrimaryKey=true, Name="ID",CanBeNull=false,IsDbGenerated=true)]
        public int Id 
        { 
            get; 
            set; 
        }

        [Column(Name="NAZIV", CanBeNull=false)]
        public string Naziv
        {
            get;
            set;
        }

        private EntitySet<Komponenta> komponente = new EntitySet<Komponenta>();
        [Association(Storage="komponente", OtherKey="ProizvodjacId", ThisKey="Id")]
        public EntitySet<Komponenta> Komponente
        {
            get { return this.komponente; }
            set { this.komponente.Assign(value); }
        } 
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data.Linq;
using System.Data.Linq.Mapping;

namespace LinqRadSaBazomPodataka
{
    [Table(Name = "KOMPONENTA")]
    public class Komponenta
    {
        [Column(IsPrimaryKey = true, Name = "ID", CanBeNull = false, IsDbGenerated = true)]
        public int Id
        {
            get;
            set;
        }

        [Column (Name="PRO_ID",CanBeNull=false)]
        public int ProizvodjacId
        {
            get;
            set;
        }

        [Column(Name = "VRS_ID", CanBeNull=false)]
        public int VrstaKomponenteId
        {
            get;
            set;
        }

        [Column(Name = "SIFRA", CanBeNull = false)]
        public string Sifra
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

        [Column(Name = "CENA")]
        public decimal Cena
        {
            get;
            set;
        }

        private EntityRef<Proizvodjac> proizvodjac;
        [Association (Storage="proizvodjac", ThisKey="ProizvodjacId", OtherKey="Id", IsForeignKey=true)]
        public Proizvodjac Proizvodjac
        {
            get { return this.proizvodjac.Entity; }
            set { this.proizvodjac.Entity = value; }
        }

        private EntityRef<VrstaKomponente> vrstaKomponente;
        [Association(Storage = "vrstaKomponente", ThisKey = "VrstaKomponenteId", OtherKey = "Id", IsForeignKey=true)]
        public VrstaKomponente VrstaKomponente
        {
            get { return this.vrstaKomponente.Entity; }
            set { this.vrstaKomponente.Entity = value; }
        }        
    }
}

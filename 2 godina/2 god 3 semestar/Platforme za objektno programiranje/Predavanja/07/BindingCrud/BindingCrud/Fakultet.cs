using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.ComponentModel;
using System.Collections.ObjectModel;

namespace Crud
{
    public class Fakultet: ICloneable, INotifyPropertyChanged
    {
        private string naziv;
        private string adresa;
        string oblast;
        Grad grad;
        public ObservableCollection<Departman> Departmani { get; set; }

        public Fakultet() 
        {
            Departmani = new ObservableCollection<Departman>();
        }

        public Fakultet(string naziv, string adresa, string oblast, Grad grad)
        {
            this.naziv = naziv;
            this.adresa = adresa;
            this.oblast = oblast;
            this.grad = grad;
            Departmani = new ObservableCollection<Departman>();
        }

        public string Naziv 
        { 
            get
            {
                return naziv;
            }
            set
            {
                naziv = value;
                OnPropertyChanged("Naziv");
            }
        }
        public string Adresa
        {
            get
            {
                return adresa;
            }
            set
            {
                adresa = value;
                OnPropertyChanged("Adresa");
            }
        }

        public string Oblast
        {
            get
            {
                return oblast;
            }
            set
            {
                oblast = value;
                OnPropertyChanged("Oblast");
            }
        }

        public Grad Grad
        {
            get
            {
                return grad;
            }
            set
            {
                grad = value;
                OnPropertyChanged("Grad");
            }
        }

        public override string ToString()
        {
            return naziv + " " + adresa;
        }

        /// <summary>
        /// Metoda vrsi kloniranje objekta. Kloniranje pravi kopiju objekta - prebacuje vrednosti svih atributa u novi objekat
        /// </summary>
        /// <returns></returns>
        public object Clone()
        {
            Fakultet kopija = new Fakultet();
            kopija.Naziv = Naziv;
            kopija.Adresa = Adresa;
            kopija.Oblast = Oblast;
            kopija.Grad = Grad;
            kopija.Departmani = new ObservableCollection<Departman>(Departmani);
            return kopija;
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected void OnPropertyChanged(String propertyName)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
            }
        }
    }
}

﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using WpfApp2.Models;

namespace WpfApp2.Windows
{
    /// <summary>
    /// Interaction logic for AddEditProfessorWindow.xaml
    /// </summary>
    public partial class AddEditProfessorWindow : Window
    {
        private RegistrovaniKorisnik selektovaniKorisnik;
        private EStatus selektovaniStatus;

        public AddEditProfessorWindow(EStatus status, RegistrovaniKorisnik korisnik)
        {
            InitializeComponent();
            selektovaniKorisnik = korisnik;
            selektovaniStatus = status;

            this.DataContext = korisnik;  //setujem binding source

            //dinamcki kreiram sadrzaj combobox-a
            cmbTipKorisnika.ItemsSource = Enum.GetValues(typeof(ETipKorisnika));


            if (status.Equals(EStatus.DODAJ))
            {
                this.Title = "Dodaj profesora";
            }
            else
            {
                txtEmail.IsEnabled = false;
                this.Title = "Izmeni profesora";
            }
        }

        private void btnOdustani_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false;
            this.Close();
        }

        private bool isValid()
        {
            return !Validation.GetHasError(txtEmail) && !Validation.GetHasError(txtIme);
        }

        private void btnSacuvaj_Click(object sender, RoutedEventArgs e)
        {

            if (isValid())
            {
                //kreiram profesora
                Profesor profesor = new Profesor
                {
                    Korisnik = selektovaniKorisnik
                };

                //podatke cuvam u listu pa u fajlove/bazu
                if (selektovaniStatus.Equals(EStatus.DODAJ))
                {
                    selektovaniKorisnik.Aktivan = true;
                    Data.Instance.Korisnici.Add(selektovaniKorisnik);
                    Data.Instance.Profesori.Add(profesor);
                }

                int id = Data.Instance.SacuvajEntitet(selektovaniKorisnik);
                profesor.ID = id; //setujem strani kljuc
                Data.Instance.SacuvajEntitet(profesor);

                this.DialogResult = true;

            }
            //zatvaram formu za kreiranje profesora
            this.Close();
        }
    }
}

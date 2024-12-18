using System;
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
        public AddEditProfessorWindow()
        {
            InitializeComponent();
        }

        private void btnOdustani_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void btnSacuvaj_Click(object sender, RoutedEventArgs e)
        {
            //preuzimam podatke sa forme koje je korisnik uneo
            //kreiram profesora

            //izvlacim podatke iz padajuceg menija
            ComboBoxItem comboBoxItem = (ComboBoxItem)cmbTipKorisnika.SelectedItem;
            string value = comboBoxItem.Content.ToString();
            Enum.TryParse(value, out ETipKorisnika tip);

            //Kreirati konstruktor sa parametrima i tako praviti objekat!!!!!
            RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik
            {
                Ime = txtIme.Text,
                Email = txtEmail.Text,
                TipKorisnika = tip,
                Aktivan = true
            };

            Profesor profesor = new Profesor
            {
                Korisnik = korisnik
            };

            //podatke cuvam u listu pa u fajlove/bazu
            Data.Instance.Korisnici.Add(korisnik);
            Data.Instance.Profesori.Add(profesor);

            Data.Instance.SacuvajEntitet("korisnici.txt");
            Data.Instance.SacuvajEntitet("profesori.txt");

            //zatvaram formu za kreiranje profesora
            this.Close();
        }
    }
}

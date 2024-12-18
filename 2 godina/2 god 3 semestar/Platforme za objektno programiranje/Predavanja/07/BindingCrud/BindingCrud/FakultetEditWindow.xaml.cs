using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Crud
{
    /// <summary>
    /// Interaction logic for FakultetEditWindow.xaml
    /// </summary>
    public partial class FakultetEditWindow : Window
    {
        Fakultet fakultet;
        public enum Stanje { DODAVANJE, IZMENA };
        Stanje stanje;

        public FakultetEditWindow(Fakultet fakultet, Stanje stanje = Stanje.DODAVANJE) //default vrednost parametra
        {            
            InitializeComponent();

            this.fakultet = fakultet;
            this.stanje = stanje;

            cbOblast.ItemsSource = Aplikacija.Instance.Oblasti;

            tbNaziv.DataContext = fakultet;
            tbAdresa.DataContext = fakultet;
            cbOblast.DataContext = fakultet;
            tbGrad.DataContext = fakultet; //Binding mode je u XAML stavljeno OneWay jer je tbGrad ReadOnly

            dgDepartmani.ItemsSource = fakultet.Departmani;
            dgDepartmani.ColumnWidth = new DataGridLength(1, DataGridLengthUnitType.Star);
        }

        private void btnOK_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = true;
            if (stanje == Stanje.DODAVANJE)
            {
                Aplikacija.Instance.Fakulteti.Add(fakultet);
            }
            this.Close();            
        }

        private void btnPickGrad_Click(object sender, RoutedEventArgs e)
        {
            GradoviWindow gw = new GradoviWindow(GradoviWindow.Stanje.PREUZIMANJE);
            if (gw.ShowDialog() == true)
            {
                fakultet.Grad = gw.SelektovaniGrad;
                /*
                 * Da bi se grad promenio i u text boxu mora Fakultet da implementira INotifyPropertyChanged 
                 * i da "javi" putem dogadjaja kada se desila promena u svojstvu Fakultet
                 * 
                 * */
            }
        }

        private void btnDodajDepartman_Click(object sender, RoutedEventArgs e)
        {
            DepartmanAddWindow daw = new DepartmanAddWindow();
            if (daw.ShowDialog() == true)
            {
                fakultet.Departmani.Add(daw.Departman);
            }
        }

        private void btnObrisiDepartman_Click(object sender, RoutedEventArgs e)
        {
            fakultet.Departmani.Remove(dgDepartmani.SelectedItem as Departman);
        }
    }
}

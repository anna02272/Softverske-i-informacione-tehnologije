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
    /// Interaction logic for Gradovi.xaml
    /// </summary>
    public partial class GradoviWindow : Window
    {
        public enum Stanje { ADMINISTRACIJA, PREUZIMANJE };
        Stanje stanje;

        public Grad SelektovaniGrad = null;

        public GradoviWindow(Stanje stanje = Stanje.ADMINISTRACIJA)
        {
            InitializeComponent();
            this.stanje = stanje;

            if (stanje == Stanje.PREUZIMANJE)
            {
                btnAdd.Visibility = Visibility.Collapsed;
                btnDelete.Visibility = Visibility.Collapsed;
                btnUpdate.Visibility = Visibility.Collapsed;
            }
            else
            {
                btnPick.Visibility = Visibility.Hidden;
            }

            dgGradovi.ItemsSource = Aplikacija.Instance.Gradovi;

            dgGradovi.ColumnWidth = new DataGridLength(1, DataGridLengthUnitType.Star);
        }

        private void btnClose_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void btnPick_Click(object sender, RoutedEventArgs e)
        {
            SelektovaniGrad = dgGradovi.SelectedItem as Grad; 
            this.DialogResult = true;
            this.Close();
        }
    }
}

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

        public FakultetEditWindow(Fakultet fakultet, 
            Stanje stanje = Stanje.DODAVANJE) //default vrednost parametra
        {            
            InitializeComponent();

            this.fakultet = fakultet;
            this.stanje = stanje;
            
            tbNaziv.Text = fakultet.Naziv;
            tbAdresa.Text = fakultet.Adresa;
        }

        private void btnOK_Click(object sender, RoutedEventArgs e)
        {
            fakultet.Naziv = tbNaziv.Text;
            fakultet.Adresa = tbAdresa.Text;

            this.DialogResult = true;
            if (stanje == Stanje.DODAVANJE)
            {
                Aplikacija.Instance.Fakulteti.Add(fakultet);
            }
            this.Close();            
        }
    }
}

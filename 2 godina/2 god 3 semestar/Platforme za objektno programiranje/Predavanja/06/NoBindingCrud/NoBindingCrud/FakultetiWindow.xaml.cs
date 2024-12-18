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
using System.ComponentModel;
using System.Data;

namespace Crud
{
    /// <summary>
    /// Interaction logic for FakultetiWindow.xaml
    /// </summary>
    public partial class FakultetiWindow : Window
    {
        public FakultetiWindow()
        {
            InitializeComponent();

            OsveziPrikaz();            
        }

        private void OsveziPrikaz()
        {
            lvFakulteti.Items.Clear();
            foreach (Fakultet f in Aplikacija.Instance.Fakulteti)
            {
                lvFakulteti.Items.Add(f);
            }
        }

        private void btnDelete_Click(object sender, RoutedEventArgs e)
        {
            if (MessageBox.Show("Da li ste sigurni?", "Potvrda", 
                MessageBoxButton.YesNo) == MessageBoxResult.Yes)
            {
                Fakultet selektovaniFakultet = 
                    lvFakulteti.SelectedItem as Fakultet;
                Aplikacija.Instance.Fakulteti.Remove(selektovaniFakultet);

                OsveziPrikaz();
            }
        }

        private void btnClose_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void btnAdd_Click(object sender, RoutedEventArgs e)
        {
            Fakultet noviFakultet = new Fakultet();
            FakultetEditWindow few = new 
                FakultetEditWindow(noviFakultet);
            bool? rez = few.ShowDialog(); 
            if (rez == true)
                OsveziPrikaz();
        }

        private void btnUpdate_Click(object sender, RoutedEventArgs e)
        {
            Fakultet selektovaniFakultet = 
                lvFakulteti.SelectedItem as Fakultet;
            FakultetEditWindow few = new FakultetEditWindow(
                selektovaniFakultet, 
                FakultetEditWindow.Stanje.IZMENA);
            if (few.ShowDialog() == true)  //ako je kliknuo Potvrdi, samo osvezimo prikaz
            {
                OsveziPrikaz();              
            }
        }
    }
}

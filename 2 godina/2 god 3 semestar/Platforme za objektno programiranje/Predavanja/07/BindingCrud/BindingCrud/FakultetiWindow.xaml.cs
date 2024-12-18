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
        ICollectionView view;

        public FakultetiWindow()
        {
            InitializeComponent();

            view = CollectionViewSource.GetDefaultView(Aplikacija.Instance.Fakulteti);
            dgFakulteti.ItemsSource = view;
            dgFakulteti.IsSynchronizedWithCurrentItem = true;

            dgFakulteti.ColumnWidth = new DataGridLength(1, DataGridLengthUnitType.Star);
        }

        private void btnDelete_Click(object sender, RoutedEventArgs e)
        {
            if (MessageBox.Show("Da li ste sigurni?", "Potvrda", 
                MessageBoxButton.YesNo) == MessageBoxResult.Yes)
            {
                Fakultet selektovaniFakultet = view.CurrentItem as Fakultet;
                Aplikacija.Instance.Fakulteti.Remove(selektovaniFakultet);
            }
        }

        private void btnClose_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void btnAdd_Click(object sender, RoutedEventArgs e)
        {
            Fakultet noviFakultet = new Fakultet();
            FakultetEditWindow few = new FakultetEditWindow(noviFakultet);
            few.ShowDialog();
        }

        private void btnUpdate_Click(object sender, RoutedEventArgs e)
        {
            Fakultet selektovaniFakultet = view.CurrentItem as Fakultet; //preuzimanje selektovanog fakulteta
            
            if (selektovaniFakultet != null)//ako je neki fakultet selektovan
            {
                //napravimo kopiju trenutnih vrednosti u objektu,  da bi ih mogli preuzeti ako korisnik ponisti napravljenje izmene
                Fakultet old = (Fakultet) selektovaniFakultet.Clone(); 
                FakultetEditWindow few = new FakultetEditWindow(selektovaniFakultet,
                    FakultetEditWindow.Stanje.IZMENA);
                if (few.ShowDialog() != true) //ako je kliknuo cancel, ponistavaju se izmene nad objektom
                {
                    
                    //pronadjemo indeks selektovanog fakulteta
                    int index = Aplikacija.Instance.Fakulteti.IndexOf(
                        selektovaniFakultet);
                    //vratimo vrednosti njegovih atributa na stare vrednosti, jer je izmena ponistena
                    Aplikacija.Instance.Fakulteti[index] = old; 
                }
            }
        }

        //reagujemo na dogadjaj kada se automatski generisu kolone i sakrijemo kolonu koja bi prikazala departmane
        private void dgFakulteti_AutoGeneratingColumn(object sender, 
            DataGridAutoGeneratingColumnEventArgs e)
        {
            if ((string)e.Column.Header == "Departmani")
            {
                e.Cancel = true;
            }
        }
    }
}

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
    /// Interaction logic for AllProfessorsWindow.xaml
    /// </summary>
    public partial class AllProfessorsWindow : Window
    {
        public AllProfessorsWindow()
        {
            InitializeComponent();

            dgProfesori.ItemsSource = null;
            dgProfesori.ItemsSource = Data.Instance.Korisnici;
        }

        private void miDodajProfesora_Click(object sender, RoutedEventArgs e)
        {
            RegistrovaniKorisnik k = new RegistrovaniKorisnik();
            AddEditProfessorWindow addEditProfessorWindow = new AddEditProfessorWindow(EStatus.DODAJ, k);

            addEditProfessorWindow.Show();
        }

        private void miIzmeniProfesora_Click(object sender, RoutedEventArgs e)
        {
            // proveriti da li je nesto uopste selektovano u tabeli
            // ako nije, ispisi poruku korisniku
            RegistrovaniKorisnik k = (RegistrovaniKorisnik)dgProfesori.SelectedItem;
            //kopija originalnog korisnika
            RegistrovaniKorisnik kopijaKorisnika = new RegistrovaniKorisnik();
            kopijaKorisnika.Ime = k.Ime;
            kopijaKorisnika.Prezime = k.Prezime;
            kopijaKorisnika.Email = k.Email;
            kopijaKorisnika.TipKorisnika = k.TipKorisnika;
            kopijaKorisnika.Aktivan = k.Aktivan;

            AddEditProfessorWindow addEditProfessorWindow = new AddEditProfessorWindow(EStatus.IZMENI, k);

         
            if ((bool)!addEditProfessorWindow.ShowDialog())
            {
                //kopiju postavljam umesto izmenjenog objekta
                int index = Data.Instance.Korisnici.ToList().FindIndex(ko => ko.Email.Equals(k.Email));
                Data.Instance.Korisnici[index] = kopijaKorisnika;
            }

        }

        private void miObrisiProfesora_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}

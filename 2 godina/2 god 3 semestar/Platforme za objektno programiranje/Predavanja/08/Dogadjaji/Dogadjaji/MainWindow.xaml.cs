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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Dogadjaji
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

            /* PROBA DELEGATA */
            Primer p = new Primer();
            p.OI = IspisVelikimSlovima; //dinamicki se postavlja metoda koja ce se izvrsavati
            p.Ispis("tekst"); //ovde ce biti izvrsena metoda prethodno postavljena u svojstvo OI

            /* PROBA DOGADJAJA */
            //postavlja se metoda koja ce se pozvati u trenutku kada objekat "javi" da se dogadjaj desio
            p.PromenjenTekst += reakcijaNaDogadjaj;
            p.PromenjenTekst += drugaReakcija; //moze se registrovati vise reakcija na isti dogadjaj. Zato je sintaksa +=. 

            p.S = "Kraj"; //izmena teksta u objektu klase Primer izazvace dogadjaj na koji reagujemo u metodi reakcijaNaDogadjaj

            //delegat se moze i eksplicitno pozvati
            p.OI("Pozvan delegat");

            //event se ne moze eksplicitno pozvati, moze se samo reagovati kada se desi
            //p.PromenjenTekst("tekst1", "tekst2");

        }

        private void IspisMalimSlovima(string s)
        {
            MessageBox.Show(s.ToLower());
        }

        private void IspisVelikimSlovima(string s)
        {
            MessageBox.Show(s.ToUpper());
        }

        private void reakcijaNaDogadjaj(string oldText, string newText)
        {
            MessageBox.Show("Tekst u objektu klase Primer je promenjen sa " +
                             "vrednosti " + oldText + 
                             " na vrednost " + newText); 
        }

        private void drugaReakcija(string oldText, string newText)
        {
            Console.WriteLine("Tekst promenjen na " + newText);
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}

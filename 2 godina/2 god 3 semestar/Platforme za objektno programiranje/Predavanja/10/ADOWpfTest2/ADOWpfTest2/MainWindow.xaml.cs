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
using System.Collections.ObjectModel;

namespace ADOWPfTest2
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            Aplikacija.Instance.UcitajFakultete();
        }

        private void btnFakulteti_Click(object sender, RoutedEventArgs e)
        {
            //sacuvamo trenutni spisak fakulteta
            List<Fakultet> old = Aplikacija.Instance.Fakulteti.ToList<Fakultet>();
            FakultetiWindow fw = new FakultetiWindow();
            //ako korisnik nije potvrdio izmene, izmene se ponistavaju i stari spisak ponovo postaje vazeci
            if (fw.ShowDialog() != true)
            {
                Aplikacija.Instance.Fakulteti = new ObservableCollection<Fakultet>(old);
            }
        }
    }
}

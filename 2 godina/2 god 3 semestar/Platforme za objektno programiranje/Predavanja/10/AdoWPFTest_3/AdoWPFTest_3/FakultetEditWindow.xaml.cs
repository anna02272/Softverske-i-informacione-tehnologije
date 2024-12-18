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

namespace ADOWpfTest3
{
    /// <summary>
    /// Interaction logic for FakultetEditWindow.xaml
    /// </summary>
    public partial class FakultetEditWindow : Window
    {
        Fakultet fakultet;
        public FakultetEditWindow(Fakultet f)
        {            
            InitializeComponent();

            fakultet = f;
            //data binding za tekstualna polja. Povezuje se objekat fakultet sa textbox komponentama
            tbNaziv.DataContext = fakultet;
            tbAdresa.DataContext = fakultet;
        }

        private void btnOK_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = true;
            if (fakultet.Id != 0) //ako postoji id, fakultet je vec u bazi, sto znaci da se radi izmena fakulteta
            {
                Fakultet.IzmeniFakultet(fakultet);
            }
            else
            {
                Aplikacija.Instance.Fakulteti.Add(fakultet);
                Fakultet.DodajFakultet(fakultet);
            }                

            this.Close();            
        }
    }
}

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

namespace PrimerWpf
{
    /// <summary>
    /// Interaction logic for WMenu.xaml
    /// </summary>
    public partial class WMenu : Window
    {
        public WMenu()
        {
            InitializeComponent();
        }

        private void MenuItem_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("Izabrano kreiranje novog fajla");
        }

        private void miDefault_Click(object sender, RoutedEventArgs e)
        {
            tbGrad.Text = "Novi Sad";
        }

        private void miDelete_Click(object sender, RoutedEventArgs e)
        {
            tbGrad.Text = "";
        }
    }
}

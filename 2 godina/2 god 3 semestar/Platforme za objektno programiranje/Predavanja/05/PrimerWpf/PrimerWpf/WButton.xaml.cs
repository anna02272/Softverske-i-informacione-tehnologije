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
    /// Interaction logic for WButton.xaml
    /// </summary>
    public partial class WButton : Window
    {
        public WButton()
        {
            InitializeComponent();
        }

        private void btnPrimer_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("Klik na dugme.");
        }

        private void btnDrugi_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("FTN lokacija");
        }
    }
}

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
    /// Interaction logic for WMainWindow.xaml
    /// </summary>
    public partial class WMainWindow : Window
    {
        public WMainWindow()
        {
            InitializeComponent();
        }

        private void btnFakulteti_Click(object sender, RoutedEventArgs e)
        {
            FakultetiWindow fw = new FakultetiWindow();
            fw.ShowDialog();
        }

        private void btnGradovi_Click(object sender, RoutedEventArgs e)
        {
            GradoviWindow gw = new GradoviWindow();
            gw.ShowDialog();
        }
    }
}

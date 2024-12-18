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
    /// Interaction logic for ListView.xaml
    /// </summary>
    public partial class WListView : Window
    {
        public WListView()
        {
            InitializeComponent();
        }

        private void btnSelected_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(lvFajlovi.SelectedIndex.ToString());
            MessageBox.Show(((ListViewItem)lvFajlovi.SelectedItem).Content.ToString());
        }
    }
}

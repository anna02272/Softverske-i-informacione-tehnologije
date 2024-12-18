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
    /// Interaction logic for DepartmanAddWindow.xaml
    /// </summary>
    public partial class DepartmanAddWindow : Window
    {
        public Departman Departman { get; set; } 
        public DepartmanAddWindow()
        {
            InitializeComponent();
            Departman = new Departman("");

            this.DataContext = Departman; //Departman je izvor podataka za sve komponente na formi
        }

        private void btnDodaj_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = true;
            this.Close();
        }
    }
}

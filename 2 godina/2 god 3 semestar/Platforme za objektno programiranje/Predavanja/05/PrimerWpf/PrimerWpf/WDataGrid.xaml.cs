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
    /// Interaction logic for WDataGrid.xaml
    /// </summary>
    public partial class WDataGrid : Window
    {
        public WDataGrid()
        {
            InitializeComponent();                  
            
            List<Student> studenti = new List<Student>();
            studenti.Add(new Student("Marko", "Markovic"));
            studenti.Add(new Student("Petar", "Petrovic"));
            studenti.Add(new Student("Milan", "Milanovic"));
            studenti.Add(new Student("Nenad", "Nenadovic"));

            dgStudenti.SelectedValuePath = "Prezime";
            
            dgStudenti.ItemsSource = studenti;
        }

        private void btnSelected_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(dgStudenti.SelectedIndex.ToString());
            MessageBox.Show(dgStudenti.SelectedItem.ToString());
            MessageBox.Show(dgStudenti.SelectedValue.ToString());
        }
    }
}

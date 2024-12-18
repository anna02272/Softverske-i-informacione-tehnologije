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
    /// Interaction logic for WOptionFields.xaml
    /// </summary>
    public partial class WOptionFields : Window
    {
        public WOptionFields()
        {
            InitializeComponent();

            //ComboBox SelectedIndex
            cbGradovi.SelectedIndex = 1;

            //ComboBox programsko definisanje
            Student s1 = new Student("Petar", "Petrovic");
            Student s2 = new Student("Marko", "Markovic");
            Student s3 = new Student("Milan", "Milanovic");
            cbStudenti.Items.Add(s1);
            cbStudenti.Items.Add(s2);
            cbStudenti.Items.Add(s3);

            //druga varijanta za popunjavanje liste studenata
            /*List<Student> studenti = new List<Student>();
            studenti.Add(s1);
            studenti.Add(s2);
            studenti.Add(s3);
            cbStudenti.ItemsSource = studenti;*/

            cbStudenti.DisplayMemberPath = "Prezime";
            cbStudenti.SelectedValuePath = "Ime";
            
            cbStudenti.SelectedItem = s2;

            //ListBox
            lbStudenti.Items.Add(s1);
            lbStudenti.Items.Add(s2);
            lbStudenti.Items.Add(s3);
        }

        private void btnTestCheckBox_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(cbPrihvatam.IsChecked.ToString());
        }

        private void btnComboSelected_Click(object sender, RoutedEventArgs e)
        {
            Student selektovaniStudent = (Student) cbStudenti.SelectedItem;
            MessageBox.Show(selektovaniStudent.ToString());
        }

        private void btnSelectedValue_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(cbStudenti.SelectedValue.ToString());
        }

        private void btnSelectedItems_Click(object sender, RoutedEventArgs e)
        {
            StringBuilder sb = new StringBuilder();
            foreach (Student s in lbStudenti.SelectedItems)
                sb.Append(s.ToString() + "\n");

            MessageBox.Show(sb.ToString());
                
        }
    }
}

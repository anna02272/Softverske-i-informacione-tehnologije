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

namespace StylesTest
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            List<Student> studenti = new List<Student>();

            Student s1 = new Student("Petar", "Petrovic");
            Student s2 = new Student("Marko", "Markovic");
            Student s3 = new Student("Milan", "Milanovic");

            studenti.Add(s1);
            studenti.Add(s2);
            studenti.Add(s3);

            lbStudenti.ItemsSource = studenti;           
        }

        private void btnTest_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("test");
        }
    }
}


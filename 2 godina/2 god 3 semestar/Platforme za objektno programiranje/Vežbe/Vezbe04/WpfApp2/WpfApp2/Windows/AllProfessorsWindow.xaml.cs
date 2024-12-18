using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using WpfApp2.Models;

namespace WpfApp2.Windows
{
    /// <summary>
    /// Interaction logic for AllProfessorsWindow.xaml
    /// </summary>
    public partial class AllProfessorsWindow : Window
    {
        public AllProfessorsWindow()
        {
            InitializeComponent();

            dgProfesori.ItemsSource = null;
            dgProfesori.ItemsSource = Data.Instance.Profesori;
        }

        private void miDodajProfesora_Click(object sender, RoutedEventArgs e)
        {
            AddEditProfessorWindow addEditProfessorWindow = new AddEditProfessorWindow();

            addEditProfessorWindow.Show();
        }
    }
}

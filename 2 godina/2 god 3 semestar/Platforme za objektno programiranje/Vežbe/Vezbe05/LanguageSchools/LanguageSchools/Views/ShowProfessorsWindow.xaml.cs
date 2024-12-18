using LanguageSchools.Models;
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

namespace LanguageSchools.Views
{
    /// <summary>
    /// Interaction logic for ShowProfessorsWindow.xaml
    /// </summary>
    public partial class ShowProfessorsWindow : Window
    {
        public ShowProfessorsWindow()
        {
            InitializeComponent();
            List<User> users = Data.Instance.ProfessorService.GetAll()
                .Select(p => p.User).ToList();
            dgProfessors.ItemsSource = users;
        }

        private void miAddProfessor_Click(object sender, RoutedEventArgs e)
        {
            var addEditProfessorWindow = new AddEditProfessorsWindow();

            var successeful = addEditProfessorWindow.ShowDialog();

            if ((bool)successeful)
            {
                List<User> users = Data.Instance.ProfessorService.GetAll()
                .Select(p => p.User).ToList();
                dgProfessors.ItemsSource = users;
            }
        }

        private void miUpdateProfessor_Click(object sender, RoutedEventArgs e)
        {
            var selectedUser = (User)dgProfessors.SelectedItem;
            if (selectedUser != null)
            {

            }
        }

        private void miDeleteProfessor_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}

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
    /// Interaction logic for AddEditProfessorsWindow.xaml
    /// </summary>
    public partial class AddEditProfessorsWindow : Window
    {
        private User newUser;
        public AddEditProfessorsWindow()
        {
            InitializeComponent();
            newUser = new User
            {
                UserType = EUserType.PROFESSOR
            };
           
            DataContext = newUser;
        }

        private void btnSave_Click(object sender, RoutedEventArgs e)
        {
            Data.Instance.ProfessorService.Add(newUser);

            DialogResult = true;
            Close();
        }
    }
}

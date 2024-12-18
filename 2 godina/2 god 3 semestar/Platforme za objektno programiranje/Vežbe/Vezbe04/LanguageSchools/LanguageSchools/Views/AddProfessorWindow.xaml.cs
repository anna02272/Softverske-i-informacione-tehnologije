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
    /// Interaction logic for AddProfessorWindow.xaml
    /// </summary>
    public partial class AddProfessorWindow : Window
    {
        private User newUser;
        public AddProfessorWindow()
        {
            InitializeComponent();
            newUser = new User
            {
                UserType = EUserType.PROFESSOR

            };
            DataContext = newUser;
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {

            this.Close();
        }

        private void btnSave_Click(object sender, RoutedEventArgs e)
        {
            ComboBoxItem comboBoxItem = (ComboBoxItem)cbUserType.SelectedItem;
            string value = comboBoxItem.Content.ToString();
            Enum.TryParse(value, out EGender gender);

            User user = new User
            {
                Email = txtEmail.Text,
                Password = txtPassword.Text,
                FirstName = txtFristName.Text,
                LastName = txtLastName.Text,
                UserType = EUserType.PROFESSOR,
                Gender = gender,
                IsActive = true
            };

            Data.Instance.ProfessorService.Add(user);

            this.DialogResult = true;

            this.Close();
        }
    }
}

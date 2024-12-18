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
    /// Interaction logic for WTextFields.xaml
    /// </summary>
    public partial class WTextFields : Window
    {
        public WTextFields()
        {
            InitializeComponent();

            tbTextBlock.Text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +  
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +  
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " + 
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " + 
                "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla " + 
                "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa " + 
                "qui officia deserunt mollit anim id est laborum.";
        }
    }
}

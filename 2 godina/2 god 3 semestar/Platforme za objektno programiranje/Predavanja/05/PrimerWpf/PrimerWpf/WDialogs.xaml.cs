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
using Microsoft.Win32;

namespace PrimerWpf
{
    /// <summary>
    /// Interaction logic for WDialogs.xaml
    /// </summary>
    public partial class WDialogs : Window
    {
        public WDialogs()
        {
            InitializeComponent();
        }

        private void btnMessage_Click(object sender, RoutedEventArgs e)
        {
            string tekst = "Da li ste sigurni?"; 
	        string naslov = "Potvrda";      
	        MessageBoxButton dugme = 	MessageBoxButton.YesNoCancel; 
	        MessageBoxImage ikona = MessageBoxImage.Warning; 
	        MessageBoxResult rez = MessageBox.Show(tekst, naslov, dugme, ikona); 
	        switch (rez)
	        {
		        case MessageBoxResult.Yes:
                    Console.WriteLine("Da");
                    break;
                case MessageBoxResult.No:
                    Console.WriteLine("Ne");
                    break;
	        }           
        }

        private void btnOpenFile_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog dlg = new OpenFileDialog();
            dlg.FileName = "Document"; // Default file name
            dlg.DefaultExt = ".txt"; // Default file extension
            dlg.Filter = "Text documents (.txt)|*.txt"; // Filter files by extension 

            // Show open file dialog box
            bool? result = dlg.ShowDialog();

            // Process open file dialog box results 
            if (result == true)
            {
                // Open document 
                string fileName = dlg.FileName;
                MessageBox.Show("Izabrali ste fajl: " + fileName);
            }
        }

        private void btnSaveFile_Click(object sender, RoutedEventArgs e)
        {
            // Configure save file dialog box
            SaveFileDialog dlg = new Microsoft.Win32.SaveFileDialog();
            dlg.FileName = "Document"; // Default file name
            dlg.DefaultExt = ".txt"; // Default file extension
            dlg.Filter = "Text documents (.txt)|*.txt"; // Filter files by extension 

            // Show save file dialog box
            bool? result = dlg.ShowDialog();

            // Process save file dialog box results 
            if (result == true)
            {
                // Save document 
                string fileName = dlg.FileName;
                MessageBox.Show("Izabrali ste snimanje u fajl: " + fileName);
            }
        }

        private void btnPrint_Click(object sender, RoutedEventArgs e)
        {
            PrintDialog dlg = new PrintDialog(); 
            dlg.PageRangeSelection = PageRangeSelection.AllPages; 
            dlg.UserPageRangeEnabled = true;
            bool? result = dlg.ShowDialog(); //prikaz
            if (result == true)
            {
                // Stampanje dokumenta
                //...
            }

        }
    }
}

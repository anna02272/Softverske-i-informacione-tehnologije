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

namespace PrimerWpf
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        //reakcija na dogadjaj u grafickoj komponenti - reakcija na klik misa
        private void btnPrikazi_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("Ime je: " + tbIme.Text);
        }

        //reakcija na dogadjaj u grafickoj komponenti - reakcija na zatvaranje prozora
        private void Window_Closed(object sender, EventArgs e)
        {
            //MessageBox.Show("Glavni prozor je upravo zatvoren.");
        }

        private void btnTekst_Click(object sender, RoutedEventArgs e)
        {
            WTextFields wTextFields = new WTextFields();
            wTextFields.ShowDialog();
        }

        private void btnDugme_Click(object sender, RoutedEventArgs e)
        {
            WButton wButton = new WButton();
            wButton.ShowDialog();
        }

        private void btnTextBoxes_Click(object sender, RoutedEventArgs e)
        {
            WTextBox w = new WTextBox();
            w.ShowDialog();
        }

        private void btnOptionFields_Click(object sender, RoutedEventArgs e)
        {
            WOptionFields w = new WOptionFields();
            w.ShowDialog();
        }

        private void btnGrid_Click(object sender, RoutedEventArgs e)
        {
            WGrid w = new WGrid();
            w.ShowDialog();
        }

        private void btnCanvas_Click(object sender, RoutedEventArgs e)
        {
            WCanvas w = new WCanvas();
            w.ShowDialog();
        }

        private void btnDockPanel_Click(object sender, RoutedEventArgs e)
        {
            WDockPanel w = new WDockPanel();
            w.ShowDialog();
        }

        private void btnStackPanel_Click(object sender, RoutedEventArgs e)
        {
            WStackPanel w = new WStackPanel();
            w.ShowDialog();
        }

        private void btnWrapPanel_Click(object sender, RoutedEventArgs e)
        {
            WWrapPanel w = new WWrapPanel();
            w.ShowDialog();
        }

        private void btnGroup_Click(object sender, RoutedEventArgs e)
        {
            WGroup w = new WGroup();
            w.ShowDialog();
        }

        private void btnDataGrid_Click(object sender, RoutedEventArgs e)
        {
            WDataGrid w = new WDataGrid();
            w.ShowDialog();
        }

        private void btnListView_Click(object sender, RoutedEventArgs e)
        {
            WListView w = new WListView();
            w.ShowDialog();
        }

        private void btnMenu_Click(object sender, RoutedEventArgs e)
        {
            WMenu w = new WMenu();
            w.ShowDialog();
        }

        private void btnMessage_Click(object sender, RoutedEventArgs e)
        {
            WDialogs w = new WDialogs();
            w.ShowDialog();
        }

        private void btnMediaElement_Click(object sender, RoutedEventArgs e)
        {
            WMedia w = new WMedia();
            w.ShowDialog();
        }

        private void btnCalendar_Click(object sender, RoutedEventArgs e)
        {
            WCalendar w = new WCalendar();
            w.ShowDialog();
        }

        private void btnTreeView_Click(object sender, RoutedEventArgs e)
        {
            WTreeView w = new WTreeView();
            w.ShowDialog();
        }

        private void Window_LostFocus(object sender, RoutedEventArgs e)
        {

        }
    }
}

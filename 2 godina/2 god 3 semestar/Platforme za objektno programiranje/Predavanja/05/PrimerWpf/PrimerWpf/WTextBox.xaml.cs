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
    /// Interaction logic for WTextBox.xaml
    /// </summary>
    public partial class WTextBox : Window
    {
        public WTextBox()
        {
            InitializeComponent();

            tbIme.Text = "Petar";

            //rich text box
            Paragraph paragraph = new Paragraph();
            rtbKomentar.Document = new FlowDocument(paragraph);

            String pocetak = "Petar Petrovic";
            String text = "Tekst komentara";
            Bold boldText = new Bold(new Run(pocetak + ":"));
            boldText.Foreground = Brushes.Blue;
            paragraph.Inlines.Add(boldText);
            paragraph.Inlines.Add(new LineBreak());
            paragraph.Inlines.Add(new LineBreak());
            paragraph.Inlines.Add(text);     
        }

        private void tbIme_TextChanged(object sender, TextChangedEventArgs e)
        {
            lbIme.Content = tbIme.Text;
        }

        private void btnPrikaziSifru_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(pwdSifra.Password);
        }
    }
}

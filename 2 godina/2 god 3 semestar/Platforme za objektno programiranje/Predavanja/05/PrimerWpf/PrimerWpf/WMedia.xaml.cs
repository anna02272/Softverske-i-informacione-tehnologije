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
    /// Interaction logic for WMedia.xaml
    /// </summary>
    public partial class WMedia : Window
    {
        public WMedia()
        {
            InitializeComponent();
        }

        private void btnStart_Click(object sender, RoutedEventArgs e)
        {
            meMovie.Play();
        }

        private void btnPause_Click(object sender, RoutedEventArgs e)
        {
            meMovie.Pause();
        }

        private void btnStop_Click(object sender, RoutedEventArgs e)
        {
            meMovie.Stop();
        }

        private void btnUbrzaj_Click(object sender, RoutedEventArgs e)
        {
            meMovie.SpeedRatio = 10;
        }
    }
}

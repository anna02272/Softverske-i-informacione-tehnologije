﻿using System;
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

namespace ADOWpfTest4
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            Aplikacija.Instance.UcitajFakultete();
        }

        private void btnFakulteti_Click(object sender, RoutedEventArgs e)
        {
            FakultetiWindow fw = new FakultetiWindow();
            fw.ShowDialog();
        }
    }
}

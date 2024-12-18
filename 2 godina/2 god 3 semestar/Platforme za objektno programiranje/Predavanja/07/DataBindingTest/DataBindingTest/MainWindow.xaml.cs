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
using System.Collections.ObjectModel;
using System.ComponentModel;

namespace DataBindingTest
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        //ObservableCollection zbog bindinga - da bi se promene izvrsene u listi automatski prikazale u grafickoj komponenti
        ObservableCollection<Student> listaStudenata = new ObservableCollection<Student>();
        Student s1 = new Student();

        public MainWindow()
        {
            InitializeComponent();

            s1.Ime = "Petar";
            s1.Prezime = "Petrovic";

            tbStudent.DataContext = s1;//postavljanje izvora podataka za text box
            
            /*
             * Programski binding text boxa 
             */
            Binding b = new Binding();
            b.Path = new PropertyPath("Prezime"); //Source Property
            tbStudentProgramski.SetBinding(TextBox.TextProperty, b); //Text u tbStudentProgramski je Dependency Property
            tbStudentProgramski.DataContext = s1; //s1 je Source Object. tbStudentProgramski je Dependency Object

            //Kreiranje liste studenata
            Student s2 = new Student();
            s2.Ime = "Marko";
            s2.Prezime = "Markovic";
            listaStudenata.Add(s1);
            listaStudenata.Add(s2);            

            //binding kolekcije na datagrid, pri cemu se kolone generisu automatski
            dgStudenti.AutoGenerateColumns = true; 
            //Za N kolona,svaka kolona zauzima N-ti deo grida
            dgStudenti.ColumnWidth = new DataGridLength(1, 
                DataGridLengthUnitType.Star); 
            dgStudenti.ItemsSource = listaStudenata;

            //binding kolekcija na data grid pri cemu se kolone rucno definisu
            DataGridTextColumn column1 = new DataGridTextColumn();
            column1.Header = "Ime";
            column1.Width = new DataGridLength(1, DataGridLengthUnitType.Star);
            column1.Binding = new Binding("Ime");
            dgStudentiManual.Columns.Add(column1);

            DataGridTextColumn column2 = new DataGridTextColumn();
            column2.Header = "Prezime";
            column2.Width = new DataGridLength(1, DataGridLengthUnitType.Star);
            column2.Binding = new Binding("Prezime");
            dgStudentiManual.Columns.Add(column2);

            dgStudentiManual.ItemsSource = listaStudenata;

            lvStudenti.DisplayMemberPath = "Prezime";//u listi se prikazuje prezime studenta            
            //kreiranje pogleda            
            CollectionViewSource cvs = new CollectionViewSource();
            cvs.Source = listaStudenata;
            lvStudenti.ItemsSource = cvs.View;
            lvStudenti.IsSynchronizedWithCurrentItem = true;// VAZNO! Mora se postaviti da bi se kontrola mogla bindovati na currentItem            

            //sortiranje
            cvs.SortDescriptions.Add(
                new SortDescription("Prezime", 
                    ListSortDirection.Ascending));
            
            //filtiriranje
            //cvs.Filter += new FilterEventHandler(PocetnoP); 

            //grupisanje            
            Student s3 = new Student();
            s3.Ime = "Ivan";
            s3.Prezime = "Petrovic";
            listaStudenata.Add(s3);

            Student s4 = new Student();
            s4.Ime = "Jelena";
            s4.Prezime = "Markovic";
            listaStudenata.Add(s4);

            PropertyGroupDescription groupDescription = new 
                PropertyGroupDescription();
            groupDescription.PropertyName = "Prezime";
            CollectionViewSource cvsGroup = new CollectionViewSource();
            cvsGroup.Source = listaStudenata;
            cvsGroup.GroupDescriptions.Add(groupDescription);
            dgGroup.ItemsSource = cvsGroup.View;
            dgGroup.ColumnWidth = new DataGridLength(1, DataGridLengthUnitType.Star);
            //potrebno je u data grid komponenti dodati grouping style kako bi prikazao grupisane podatke

            /*
             * Binding na trenutni element u pogledu 
             */
            Binding b1 = new Binding();
            b1.Path = new PropertyPath("/Ime");//treba / kao znak da se binduje na currentItem, ali radi i bez toga
            lbImeStudentaView.SetBinding(Label.ContentProperty, b1);
            lbImeStudentaView.DataContext = cvs.View;

            Binding b2 = new Binding();
            b2.Path = new PropertyPath("Prezime");
            lbPrezimeStudentaView.SetBinding(Label.ContentProperty, b2);
            lbPrezimeStudentaView.DataContext = cvs.View;
        }

        private void PocetnoP(object sender, FilterEventArgs e)
        {
            Student s = e.Item as Student;
            if (s != null)
            {
                e.Accepted = s.Ime.Length > 0 && s.Ime[0] == 'P';
            }
        }

        private void btnTest_Click(object sender, RoutedEventArgs e)
        {
            /*
             * Binding od izvora ka cilju. 
             * Radi samo ako izvorni objekat implementira INotifyPropertyChanged
             * i ako je Binding Mode TwoWay (to je po defaultu) ili OneWay.
             * Ovo se moze i probati izmenom Binding Mode-a
             */
            //s1.Ime = "Marko";     
            MessageBox.Show(s1.Ime);//od cilja ka izvoru radi samo ako je Binding Mode TwoWay ili OneWayToSource
        }

        private void btnSadrzajKolekcije_Click(object sender, RoutedEventArgs e)
        {
            /* Ilustracija bindinga od dataGrida ka ObservableCollection
             * Promena izvrsena u gridu se automatski vrsi i nad kolekcijom
             * */
            StringBuilder sb = new StringBuilder();
            foreach (Student s in listaStudenata)
                sb.Append(s.Ime + " " + s.Prezime + "\n");

            MessageBox.Show(sb.ToString());
        }

        private void btnAddStudent_Click(object sender, RoutedEventArgs e)
        {
            /*
             * Ilustracija bindinga od kolekcije ka grafickoj komponenti
             * Promena u kolekciji se automatski prikazuje u gridu
             */
            Student s = new Student();
            s.Ime = "Branko";
            s.Prezime = "Brankovic";
            listaStudenata.Add(s);
        }
    }
}

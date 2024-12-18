package Zad04Fakultet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Zad04Fakultet.DataClasses.Osoba;
import Zad04Fakultet.DataClasses.Profesor;
import Zad04Fakultet.DataClasses.Student;
import Zad04Fakultet.DataClasses.Utility;
import Zad04Fakultet.Database.HobiDB;
import Zad04Fakultet.Database.KategorijaFinanasiranjaDB;
import Zad04Fakultet.Database.ProfesorDB;
import Zad04Fakultet.Database.StudentDB;
import Zad04Fakultet.GUI.StudentDialog;
import Zad04Fakultet.GUI.UtilityDialog;
import Zad04Fakultet.ListAndTableModels.OsobeTabeleModel;
import Zad04Fakultet.ListAndTableModels.ProfesorTabeleModel;
import Zad04Fakultet.ListAndTableModels.StudentiTabeleModel;

@SuppressWarnings("serial")
public class OsnovniProzor extends JFrame {
	
	public Osoba selektovanaOsoba;
	public Student selektovanStudent;
	public Profesor selektovanProfesor;
	
	String sP = System.getProperty("file.separator");
	String putanjaOsoba, putanjaStudent, putanjaProfesor;
	
	//GUI
	JMenuBar 		jLinijaGlavnogMenija;
	JTabbedPane 	tabbedPane;
	
	//PRVA KARTICA ---------------------------------------------
	JPanel 			jPanelOsoba;
	
	//PRVI PANEL
	JPanel 			jPanelSearchAndSortOsoba;
	JTextField 		jTxtJMBGOsoba;
	JTextField 		jTxtImeIliPrezimeOsoba;
	
	//DRUGI PANEL
	JScrollPane 	jScrolOsoba;
	JTable 			jTableOsoba;
	OsobeTabeleModel modelOsoba;
	
	//DRUGA KARTICA --------------------------------------------
	JPanel 			jPanelStudent;
	//PRVI PANEL
	JPanel 			jPanelSearchAndSortStudent;
	JTextField 		jTxtJMBGStudent;
	JTextField 		jTxtImeIliPrezimeStudent;
	JTextField 		jTxtIndexStudent;
	JTextField 		jTxtTipFinansiranjaOdStudenta;
	JTextField		jTxtGodinaUpisaOdStudent;
	JTextField		jTxtGodinaUpisaDoStudent;
	
	
	//DRUGI PANEL
	JScrollPane 	jScrolStudent;
	JTable 			jTableStudent;
	StudentiTabeleModel modelStudent;
	
	//ZADNJI PANEL
	JPanel 			jPanelZanjiStudent;
	JButton 		jBtnAddNewStudent;
	JButton 		jBtnEditExistingStudent;
	JButton 		jBtnRemoveExistingStudent;
	JButton 		jBtnViewExistingStudent;
	
	//TRECA KARTICA  --------------------------------------------
	JPanel 			jPanelProfesor;
	//PRVI PANEL
	JPanel 			jPanelSearchAndSortProfesor;
	JTextField 		jTxtJMBGProfesor;
	JTextField 		jTxtImeIliPrezimeProfesor;
	JTextField 		jTxtZvanjeStudent;
	JTextField 		jTxtPlataOdProfesor, jTxtPlataDoProfesor;
	
	//DRUGI PANEL
	JScrollPane 	jScrolProfesor;
	JTable 			jTableProfesor;
	ProfesorTabeleModel modelProfesor;
	
	//ZADNJI PANEL
	JPanel 			jPanelZanjiProfesor;
	JButton 		jBtnAddNewProfesor;
	JButton 		jBtnEditExistingProfesor;
	JButton 		jBtnRemoveExistingProfesor;
	JButton 		jBtnViewExistingProfesor;
	
	
	public OsnovniProzor() {
		putanjaOsoba = "."+sP+"resource"+sP+"images"+sP+"persons"+sP+"osoba.jpg";
		putanjaStudent = "."+sP+"resource"+sP+"images"+sP+"persons"+sP+"student.jpg";
		putanjaProfesor = "."+sP+"resource"+sP+"images"+sP+"persons"+sP+"profesor.jpg";
		
		setTitle("Studentska slu\u017Eba");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension velicinaEkrana = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(new Point(velicinaEkrana.width/8, velicinaEkrana.height/8));
        setSize(velicinaEkrana.width/2, velicinaEkrana.height/2);
        postaviGlavniMeni();
        postaviProstorZaCrtanjeKomponenti();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
//        pack();
	}

    public void postaviGlavniMeni(){
    	jLinijaGlavnogMenija = new JMenuBar();
		setJMenuBar(jLinijaGlavnogMenija);
		
        JMenu jMenuRadSaPodacima = new JMenu("Rad sa podacima");
        jLinijaGlavnogMenija.add(jMenuRadSaPodacima);
        
        JMenuItem jMenuItemLoadData = new JMenuItem("O\u010Ditaj iz fajla");
        jMenuItemLoadData.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocitajPodatkeIzBaze();
			}
		});
        jMenuRadSaPodacima.add(jMenuItemLoadData);		
        
        JMenuItem jMenuItemSaveData = new JMenuItem("Pi\u0161i u fajl");
        jMenuItemSaveData.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sacuvajPodatkeUBazu();
			}
		});
        jMenuRadSaPodacima.add(jMenuItemSaveData);	
    }
    
	public void postaviProstorZaCrtanjeKomponenti(){
		
		tabbedPane = new JTabbedPane();
	    getContentPane().add(tabbedPane, BorderLayout.CENTER);

	    //PRVA KARTICA
	    jPanelOsoba = new JPanel(false); //koristi double buffer koji zauzima dodatni memorisjki prostor sa ciljem da se omoguce brza promena komponenti panela 
	    tabbedPane.addTab("Osobe", new ImageIcon(putanjaOsoba), jPanelOsoba, "Prikaz svih osoba");
	    tabbedPane.setMnemonicAt(0, KeyEvent.VK_O);
	    
	    postaviProstorPrveKartice();
	    
	    //DRUGA KARTICA
	    jPanelStudent = new JPanel(false); 
	    jPanelStudent.setLayout(new BorderLayout());
	    tabbedPane.addTab("Studenti", new ImageIcon(putanjaStudent), jPanelStudent, "Prikaz svih studenta");
	    tabbedPane.setMnemonicAt(1, KeyEvent.VK_S);
	    
	    postaviProstorDrugeKartice();
	    
	    //TRECA KARTICA
	    jPanelProfesor = new JPanel(false);
	    jPanelProfesor.setLayout(new BorderLayout());
	    tabbedPane.addTab("Profesori", new ImageIcon(putanjaProfesor), jPanelProfesor, "Prikaz svih profesora");
	    tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
	    
	    postaviProstorTreceKartice();
	}
	
	public void postaviProstorPrveKartice(){
		
	    jPanelOsoba.setLayout(new BorderLayout());
	    
		//PRVI PANEL ----------------------------------------------------------
		jPanelSearchAndSortOsoba = new JPanel();
		jPanelSearchAndSortOsoba.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Pretraga i Sortiranje", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		jPanelOsoba.add(jPanelSearchAndSortOsoba, BorderLayout.NORTH);
		
		jPanelSearchAndSortOsoba.setLayout(new GridBagLayout());
		
		//PRVI RED - PRVI PANEL
		JLabel jLblJMBGOsoba = new JLabel("JMBG:");
		jPanelSearchAndSortOsoba.add(jLblJMBGOsoba, UtilityDialog.vratiGBC(0, 0, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtJMBGOsoba = new JTextField(13);
		jPanelSearchAndSortOsoba.add(jTxtJMBGOsoba,  UtilityDialog.vratiGBC(1, 0, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		//DRUGI RED - PRVI PANEL
		JLabel jLblImeIliPrezimeOsoba = new JLabel("ime ili prezime:");
		jPanelSearchAndSortOsoba.add(jLblImeIliPrezimeOsoba,  UtilityDialog.vratiGBC(0, 1, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtImeIliPrezimeOsoba = new JTextField(13);
		jPanelSearchAndSortOsoba.add(jTxtImeIliPrezimeOsoba,  UtilityDialog.vratiGBC(1, 1, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		//TRECI RED - PRVI PANEL
		
		JButton jBtnTraziOsobe = new JButton("Tra\u017Ei");
		jBtnTraziOsobe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				traziOsoba();
				if(!modelOsoba.getListaOsoba().isEmpty())
					jTableOsoba.getSelectionModel().setSelectionInterval(0, 0);
			}
		});
		jPanelSearchAndSortOsoba.add(jBtnTraziOsobe,  UtilityDialog.vratiGBCFill(0, 2, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnPonistiPretraguOsobe = new JButton("Poni\u0161ti pretragu");
		jBtnPonistiPretraguOsobe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ponistiPretraguOsoba();
				if(!modelOsoba.getListaOsoba().isEmpty())
					jTableOsoba.getSelectionModel().setSelectionInterval(0, 0);
			}
		});
		jPanelSearchAndSortOsoba.add(jBtnPonistiPretraguOsobe, UtilityDialog.vratiGBCFill(1, 2, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		//CETVRTI RED - PRVI PANEL
		JButton jBtnSortOsobeJMBGASC = new JButton("JMBG ASC");
		jBtnSortOsobeJMBGASC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortOsobaJMBG(modelOsoba.getListaOsoba(), 1);
				modelOsoba.fireTableDataChanged();
				postaviSelektovanuOsobu(selektovanaOsoba);
			}
		});
		jPanelSearchAndSortOsoba.add(jBtnSortOsobeJMBGASC, UtilityDialog.vratiGBCFill(0, 3, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnSortOsobeImeASC = new JButton("Ime ASC");
		jBtnSortOsobeImeASC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortOsobaIme(modelOsoba.getListaOsoba(), 1);
				modelOsoba.fireTableDataChanged();
				postaviSelektovanuOsobu(selektovanaOsoba);
			}
		});
		jPanelSearchAndSortOsoba.add(jBtnSortOsobeImeASC, UtilityDialog.vratiGBCFill(1, 3, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnSortOsobePrezimeASC = new JButton("Prezime ASC");
		jBtnSortOsobePrezimeASC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortOsobaPrezime(modelOsoba.getListaOsoba(), 1);
				modelOsoba.fireTableDataChanged();
				postaviSelektovanuOsobu(selektovanaOsoba);
			}
		});
		jPanelSearchAndSortOsoba.add(jBtnSortOsobePrezimeASC, UtilityDialog.vratiGBCFill(2, 3, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		//PETI RED - PRVI PANEL
		
		JButton jBtnSortOsobeJMBGDESC = new JButton("JMBG DESC");
		jBtnSortOsobeJMBGDESC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortOsobaJMBG(modelOsoba.getListaOsoba(), -1);
				modelOsoba.fireTableDataChanged();
				postaviSelektovanuOsobu(selektovanaOsoba);
			}
		});
		jPanelSearchAndSortOsoba.add(jBtnSortOsobeJMBGDESC, UtilityDialog.vratiGBCFill(0, 4, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnSortOsobeImeDESC = new JButton("Ime DESC");
		jBtnSortOsobeImeDESC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortOsobaIme(modelOsoba.getListaOsoba(), -1);
				modelOsoba.fireTableDataChanged();
				postaviSelektovanuOsobu(selektovanaOsoba);
			}
		});
		jPanelSearchAndSortOsoba.add(jBtnSortOsobeImeDESC, UtilityDialog.vratiGBCFill(1, 4, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnSortOsobePrezimeDESC = new JButton("Prezime DESC");
		jBtnSortOsobePrezimeDESC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortOsobaPrezime(modelOsoba.getListaOsoba(), -1);
				modelOsoba.fireTableDataChanged();
				postaviSelektovanuOsobu(selektovanaOsoba);
			}
		});
		jPanelSearchAndSortOsoba.add(jBtnSortOsobePrezimeDESC, UtilityDialog.vratiGBCFill(2, 4, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		//SESTI RED - PRVI PANEL
		JButton jBtnPrikaziSve = new JButton("Prikazi sve");
		jBtnPrikaziSve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Osoba> osobe = new ArrayList<Osoba>();
				osobe.addAll(StudentDB.listaStudenta);
				osobe.addAll(ProfesorDB.listaProfesora);
				modelOsoba.setNewList(osobe);
				selektovanaOsoba=null;
				if(!modelOsoba.getListaOsoba().isEmpty()){
					jTableOsoba.getSelectionModel().setSelectionInterval(0, 0);
				}
			}
		});
		jPanelSearchAndSortOsoba.add(jBtnPrikaziSve, UtilityDialog.vratiGBCFill(0, 5, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnPrikaziAktivne = new JButton("Prikazi aktivne");
		jBtnPrikaziAktivne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Osoba> temp = new ArrayList<Osoba>();
				temp.addAll(StudentDB.listaStudenta);
				temp.addAll(ProfesorDB.listaProfesora);
				Utility.searchOsobaByStatus(temp, true);
				modelOsoba.setNewList(temp);
				selektovanaOsoba=null;
				if(!modelOsoba.getListaOsoba().isEmpty()){
					jTableOsoba.getSelectionModel().setSelectionInterval(0, 0);
				}
			}
		});
		jPanelSearchAndSortOsoba.add(jBtnPrikaziAktivne, UtilityDialog.vratiGBCFill(1, 5, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBrnPrikaziObrisane = new JButton("Prikazi obrisane");
		jBrnPrikaziObrisane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Osoba> temp = new ArrayList<Osoba>();
				temp.addAll(StudentDB.listaStudenta);
				temp.addAll(ProfesorDB.listaProfesora);
				Utility.searchOsobaByStatus(temp, false);
				modelOsoba.setNewList(temp);
				selektovanaOsoba=null;
				if(!modelOsoba.getListaOsoba().isEmpty()){
					jTableOsoba.getSelectionModel().setSelectionInterval(0, 0);
				}
			}
		});
		jPanelSearchAndSortOsoba.add(jBrnPrikaziObrisane, UtilityDialog.vratiGBCFill(2, 5, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		
		//DRUGI PANEL ----------------------------------------------------------
		ArrayList<Osoba> osobe = new ArrayList<Osoba>();
		osobe.addAll(StudentDB.listaStudenta);
		osobe.addAll(ProfesorDB.listaProfesora);
		modelOsoba= new OsobeTabeleModel(osobe);
		jTableOsoba = new JTable(modelOsoba);
		jTableOsoba.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel lsm = jTableOsoba.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && (e.getSource() instanceof ListSelectionModel)) {  	
					int row = jTableOsoba.getSelectedRow();
		        	if (row == -1) // nista nije selektovano 
		        		return;
		        	
		        	String identifikatorOsobe = (String) jTableOsoba.getValueAt(row, 0);
		        	for (Osoba temp : modelOsoba.getListaOsoba()) {
						if(temp.getJMBG().equalsIgnoreCase(identifikatorOsobe)){
							postaviSelektovanuOsobu(temp);
							break;
						}	
					}
		        }	
			}
		});
		
		jScrolOsoba = new JScrollPane(jTableOsoba);
		jPanelOsoba.add(jScrolOsoba, BorderLayout.CENTER);
	}
	
	public void postaviProstorDrugeKartice(){
		
		jPanelStudent.setLayout(new BorderLayout());
	    
		//PRVI PANEL ----------------------------------------------------------
		jPanelSearchAndSortStudent = new JPanel();
		jPanelSearchAndSortStudent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Pretraga i Sortiranje", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		jPanelStudent.add(jPanelSearchAndSortStudent, BorderLayout.NORTH);
		
		jPanelSearchAndSortStudent.setLayout(new GridBagLayout());
		
		//PRVI RED - PRVI PANEL
		JLabel jLblJMBGStudent = new JLabel("JMBG:");
		jPanelSearchAndSortStudent.add(jLblJMBGStudent, UtilityDialog.vratiGBC(0, 0, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		
		jTxtJMBGStudent = new JTextField(13);
		jPanelSearchAndSortStudent.add(jTxtJMBGStudent, UtilityDialog.vratiGBC(1, 0, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		//DRUGI RED - PRVI PANEL
		JLabel jLblImeIliPrezimeStudent = new JLabel("ime ili prezime:");
		jPanelSearchAndSortStudent.add(jLblImeIliPrezimeStudent, UtilityDialog.vratiGBC(0, 1, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtImeIliPrezimeStudent = new JTextField(13);
		jPanelSearchAndSortStudent.add(jTxtImeIliPrezimeStudent, UtilityDialog.vratiGBCFill(1, 1, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		//TRECI RED - PRVI PANEL
		JLabel jLblIndexStudent = new JLabel("index:");
		jPanelSearchAndSortStudent.add(jLblIndexStudent, UtilityDialog.vratiGBC(0, 2, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtIndexStudent = new JTextField(13);
		jPanelSearchAndSortStudent.add(jTxtIndexStudent,  UtilityDialog.vratiGBCFill(1, 2, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		//CETVRTI RED - PRVI PANEL
		JLabel jLblTipFinansiranjaStudent = new JLabel("Tip finansiranja:");
		jPanelSearchAndSortStudent.add(jLblTipFinansiranjaStudent, UtilityDialog.vratiGBC(0, 3, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtTipFinansiranjaOdStudenta = new JTextField(13);
		jPanelSearchAndSortStudent.add(jTxtTipFinansiranjaOdStudenta,  UtilityDialog.vratiGBCFill(1, 3, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
				
		//PETI RED - PRVI PANEL
		JLabel jLblGodinaUpisaOdStudent = new JLabel("Godina upisa od:");
		jPanelSearchAndSortStudent.add(jLblGodinaUpisaOdStudent, UtilityDialog.vratiGBC(0, 4, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtGodinaUpisaOdStudent = new JTextField(13);
		jPanelSearchAndSortStudent.add(jTxtGodinaUpisaOdStudent, UtilityDialog.vratiGBCFill(1, 4, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JLabel jLblGodinaUpisaDoStudent = new JLabel("Godina upisa do:");
		jPanelSearchAndSortStudent.add(jLblGodinaUpisaDoStudent, UtilityDialog.vratiGBC(2, 4, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtGodinaUpisaDoStudent = new JTextField(13);
		jPanelSearchAndSortStudent.add(jTxtGodinaUpisaDoStudent, UtilityDialog.vratiGBCFill(3, 4, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		//SESTI RED - PRVI PANEL
		
		JButton jBtnTraziStudenta = new JButton("Tra\u017Ei");
		jBtnTraziStudenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				traziStudenta();
				if(!modelStudent.getListaStudenta().isEmpty()){
					jTableStudent.getSelectionModel().setSelectionInterval(0, 0);
				}
			}
		});
		jPanelSearchAndSortStudent.add(jBtnTraziStudenta, UtilityDialog.vratiGBCFill(0, 5, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnPonistiPretraguStudenta = new JButton("Poni\u0161ti pretragu");
		jBtnPonistiPretraguStudenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ponistiPretraguStudenta();
				if(!modelStudent.getListaStudenta().isEmpty()){
					jTableStudent.getSelectionModel().setSelectionInterval(0, 0);
				}
			}
		});
		jPanelSearchAndSortStudent.add(jBtnPonistiPretraguStudenta, UtilityDialog.vratiGBCFill(1, 5, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		//SEDMI RED - PRVI PANEL
		
		JButton jBtnSortStudentaJMBGASC = new JButton("JMBG ASC");
		jBtnSortStudentaJMBGASC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortOsobaJMBG(modelStudent.getListaStudenta(), 1);
				modelStudent.fireTableDataChanged();
				if(selektovanStudent!=null && !modelStudent.getListaStudenta().isEmpty()){
					//TO DO treba nekako omoguciti selekciju selektovanog studenta u tabeli, a ne selekcija prvog u tabeli
					jTableStudent.getSelectionModel().setSelectionInterval(0, 0);
				}
			}
		});
		jPanelSearchAndSortStudent.add(jBtnSortStudentaJMBGASC,  UtilityDialog.vratiGBCFill(0, 6, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnSortStudentaPrezimeASC = new JButton("Prezime ASC");
		jBtnSortStudentaPrezimeASC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortOsobaPrezime(modelStudent.getListaStudenta(), 1);
				modelStudent.fireTableDataChanged();
				postaviSelektovanogStudenta(selektovanStudent);
			}
		});
		jPanelSearchAndSortStudent.add(jBtnSortStudentaPrezimeASC, UtilityDialog.vratiGBCFill(1, 6, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnSortStudentaIndexASC = new JButton("Index ASC");
		jBtnSortStudentaIndexASC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortStudentIndex(modelStudent.getListaStudenta(), 1);
				modelStudent.fireTableDataChanged();
				postaviSelektovanogStudenta(selektovanStudent);
			}
		});
		jPanelSearchAndSortStudent.add(jBtnSortStudentaIndexASC, UtilityDialog.vratiGBCFill(2, 6, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnSortStudentaGodinaUpisaASC = new JButton("Godina upisa ASC");
		jBtnSortStudentaGodinaUpisaASC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortStudentGodinaUpisa(modelStudent.getListaStudenta(), 1);
				modelStudent.fireTableDataChanged();
				postaviSelektovanogStudenta(selektovanStudent);
			}
		});
		jPanelSearchAndSortStudent.add(jBtnSortStudentaGodinaUpisaASC, UtilityDialog.vratiGBCFill(3, 6, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		//OSMI RED - PRVI PANEL
		
		JButton jBtnSortStudentaJMBGDESC = new JButton("JMBG DESC");
		jBtnSortStudentaJMBGDESC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortOsobaJMBG(modelStudent.getListaStudenta(), -1);
				modelStudent.fireTableDataChanged();
				postaviSelektovanogStudenta(selektovanStudent);
			}
		});
		jPanelSearchAndSortStudent.add(jBtnSortStudentaJMBGDESC, UtilityDialog.vratiGBCFill(0, 7, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnSortStudentaPrezimeDESC = new JButton("Prezime DESC");
		jBtnSortStudentaPrezimeDESC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortOsobaPrezime(modelStudent.getListaStudenta(), -1);
				modelStudent.fireTableDataChanged();
				postaviSelektovanogStudenta(selektovanStudent);
			}
		});
		jPanelSearchAndSortStudent.add(jBtnSortStudentaPrezimeDESC, UtilityDialog.vratiGBCFill(1, 7, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnSortStudentaIndexDESC = new JButton("Index DESC");
		jBtnSortStudentaIndexDESC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortStudentIndex(modelStudent.getListaStudenta(), -1);
				modelStudent.fireTableDataChanged();
				postaviSelektovanogStudenta(selektovanStudent);
			}
		});
		jPanelSearchAndSortStudent.add(jBtnSortStudentaIndexDESC, UtilityDialog.vratiGBCFill(2, 7, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnSortStudentaGodinaUpisaDESC = new JButton("Godina Upisa DESC");
		jBtnSortStudentaGodinaUpisaDESC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Utility.sortStudentGodinaUpisa(modelStudent.getListaStudenta(), -1);
				modelStudent.fireTableDataChanged();
				postaviSelektovanogStudenta(selektovanStudent);
			}
		});
		jPanelSearchAndSortStudent.add(jBtnSortStudentaGodinaUpisaDESC, UtilityDialog.vratiGBCFill(3, 7, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		//DEVETI RED - PRVI PANEL
		JButton jBtnPrikaziSve = new JButton("Prikazi sve");
		jBtnPrikaziSve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modelStudent.setNewList(StudentDB.listaStudenta);
				selektovanStudent=null;
				if(!modelStudent.getListaStudenta().isEmpty()){
					jTableStudent.getSelectionModel().setSelectionInterval(0, 0);
				}
			}
		});
		jPanelSearchAndSortStudent.add(jBtnPrikaziSve, UtilityDialog.vratiGBCFill(0, 8, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBtnPrikaziAktivne = new JButton("Prikazi aktivne");
		jBtnPrikaziAktivne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Student> temp = new ArrayList<Student>();
				temp.addAll(StudentDB.listaStudenta);
				Utility.searchOsobaByStatus(temp, true);
				modelStudent.setNewList(temp);
				selektovanStudent=null;
				if(!modelStudent.getListaStudenta().isEmpty()){
					jTableStudent.getSelectionModel().setSelectionInterval(0, 0);
				}
			}
		});
		jPanelSearchAndSortStudent.add(jBtnPrikaziAktivne, UtilityDialog.vratiGBCFill(1, 8, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
		
		JButton jBrnPrikaziObrisane = new JButton("Prikazi obrisane");
		jBrnPrikaziObrisane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Student> temp = new ArrayList<Student>();
				temp.addAll(StudentDB.listaStudenta);
				Utility.searchOsobaByStatus(temp, false);
				modelStudent.setNewList(temp);
				selektovanStudent=null;
				if(!modelStudent.getListaStudenta().isEmpty()){
					jTableStudent.getSelectionModel().setSelectionInterval(0, 0);
				}
			}
		});
		jPanelSearchAndSortStudent.add(jBrnPrikaziObrisane, UtilityDialog.vratiGBCFill(2, 8, GridBagConstraints.HORIZONTAL, 1, -1, new Insets(5, 5, 5, 5)));
					
		//DRUGI PANEL ----------------------------------------------------------
		modelStudent= new StudentiTabeleModel(StudentDB.listaStudenta);
		jTableStudent = new JTable(modelStudent);
		jTableStudent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel lsm = jTableStudent.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && (e.getSource() instanceof ListSelectionModel)) {  	
					int row = jTableStudent.getSelectedRow();
		        	if (row == -1) // nista nije selektovano 
		        		return;
		        	
		        	String identifikatorStudenta = (String) jTableStudent.getValueAt(row, 0);
		        	for (Student temp : modelStudent.getListaStudenta()) {
						if(temp.getJMBG().equalsIgnoreCase(identifikatorStudenta)){
							postaviSelektovanogStudenta(temp);
							break;
						}	
					}
		        }	
			}
		});
		
		jScrolStudent = new JScrollPane(jTableStudent);
		jPanelStudent.add(jScrolStudent, BorderLayout.CENTER);	
		
		//ZADNJI PANEL
		jPanelZanjiStudent = new JPanel();
		jPanelZanjiStudent.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Opcije rada sa tabelom", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		jPanelStudent.add(jPanelZanjiStudent, BorderLayout.SOUTH);
		
		jBtnAddNewStudent = new JButton("Dodaj Novog");
		jBtnAddNewStudent.setName("id:jBtnAddNew");
		jBtnAddNewStudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dodajNovogStudenta();
			}
		});
		
		jBtnEditExistingStudent = new JButton("Izmeni selektovanog");
		jBtnEditExistingStudent.setName("id:jBtnEditExisting");
		jBtnEditExistingStudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				izmeniPostojecegStudenta();
			}
		});

		jBtnRemoveExistingStudent = new JButton("Obri\u0161i selektovanog");
		jBtnRemoveExistingStudent.setName("id:jBtnRemoveExisting");
		jBtnRemoveExistingStudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				obrisiPostojecegStudenta();
			}
		});

		jBtnViewExistingStudent = new JButton("Pregled selektovanog");
		jBtnViewExistingStudent.setName("id:jBtnViewExisting");
		jBtnViewExistingStudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pregledPostojecegStudenta();
			}
		});
		
		jPanelZanjiStudent.add(jBtnAddNewStudent);
		jPanelZanjiStudent.add(jBtnEditExistingStudent);
		jPanelZanjiStudent.add(jBtnRemoveExistingStudent);
		jPanelZanjiStudent.add(jBtnViewExistingStudent);
	}
	
	public void postaviProstorTreceKartice(){
		
	}
	
	public void postaviSelektovanuOsobu(Osoba os){
		selektovanaOsoba = os;
		if(os!=null && !modelOsoba.getListaOsoba().isEmpty() && jTableOsoba!=null){
			ArrayList<Osoba> temp = modelOsoba.getListaOsoba();
			for (int i = 0; i < temp.size(); i++) {
				Osoba el = temp.get(i);
				if(el.getJMBG().equals(os.getJMBG())){
					jTableOsoba.getSelectionModel().setSelectionInterval(i, i);
					break;
				}
			}
		}
	}
	
	public void postaviSelektovanogStudenta(Student st){
		selektovanStudent = st;
		if(st!=null && !modelStudent.getListaStudenta().isEmpty() && jTableStudent!=null){
			ArrayList<Student> temp = modelStudent.getListaStudenta();
			for (int i = 0; i < temp.size(); i++) {
				Student el = temp.get(i);
				if(el.getJMBG().equals(st.getJMBG())){
					jTableStudent.getSelectionModel().setSelectionInterval(i, i);
					break;
				}
			}
		}
	}
	
	public void postaviSelektovanogProfesora(Profesor pr){
		selektovanProfesor = pr;
		if(pr!=null && !modelProfesor.getLista().isEmpty() && jTableProfesor!=null){
			ArrayList<Profesor> temp = modelProfesor.getLista();
			for (int i = 0; i < temp.size(); i++) {
				Profesor el = temp.get(i);
				if(el.getJMBG().equals(pr.getJMBG())){
					jTableProfesor.getSelectionModel().setSelectionInterval(i, i);
					break;
				}
			}
		}
	}
	
	public void dodajNovogStudenta(){
		Student tempStudent = new Student("", "", "", "",null);
		StudentDialog stD = new StudentDialog(this, "Dodavanje novog Studenta", true, tempStudent, UtilityDialog.UNOS);
		stD.setVisible(true);
		if(!tempStudent.getJMBG().equals("")){
			StudentDB.listaStudenta.add(tempStudent);
			modelOsoba.addValue(tempStudent);
			modelStudent.addValue(tempStudent);				//redefinisana metoda u modelu, poziva i fire metodu koja implicitno poziva iscrtavanje liste
			postaviSelektovanuOsobu(tempStudent);
			postaviSelektovanogStudenta(tempStudent);
		}
	}

	public void dodajNovogProfesora(){
		
	}
	
	public void izmeniPostojecegStudenta(){
		if(selektovanStudent!=null && selektovanStudent instanceof Student){
			StudentDialog stD = new StudentDialog(this, "Izmena postoje\u0107eg Studenta", true, selektovanStudent, UtilityDialog.IZMENA);
			stD.setVisible(true);
			modelOsoba.setVelueById(selektovanStudent, selektovanStudent.getJMBG());
			modelStudent.setVelueById(selektovanStudent, selektovanStudent.getJMBG());
			postaviSelektovanuOsobu(selektovanStudent);
			postaviSelektovanogStudenta(selektovanStudent);
		}
		else {
			JOptionPane.showMessageDialog(this, "Nije selektovan ni jedan student", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void izmeniPostojecegProfesora(){
		
	}
	
	public void obrisiPostojecegStudenta(){
		if(selektovanStudent!=null && selektovanStudent instanceof Student){
			//Custom button text
			Object[] naziviOpcija = {"Da, \u017Eelim", "Ne dolazi u obzir!", "Odustani"};
			int n2 = JOptionPane.showOptionDialog(this, "Da li ste sigurni da \u017Eelite obrisati selektovanog studenta?", "Dialog potvrde",
			    JOptionPane.YES_NO_CANCEL_OPTION,	
			    JOptionPane.QUESTION_MESSAGE,		//tip poruke moze biti QUESTION_MESSAGE , PLAIN_MESSAGE
			    null,								//korisnicka ikonica, null ako ne postoji
			    naziviOpcija,						//nazivi dugmadi
			    naziviOpcija[1]);					//inicijalna vrednost
			
			if(n2 == 0){
				selektovanStudent.setStatusZapisa(false); //logicko brisanje
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Nije selektovan ni jedan student", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void obrisiPostojecegProfesora(){
		
	}
	
	public void pregledPostojecegStudenta(){
		if(selektovanStudent!=null && selektovanStudent instanceof Student){
			StudentDialog stD = new StudentDialog(this, "Pregled postoje\u0107eg Studenta", true, selektovanStudent, UtilityDialog.PREGLED);
			stD.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(this, "Nije selektovan ni jedan student", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void pregledPostojecegProfesora(){
		
	}
	
	public void traziOsoba(){
		String JMBG = jTxtJMBGOsoba.getText();
		String imeIliPrezime = jTxtImeIliPrezimeOsoba.getText();
		Utility.searchOsobaByJMBG(modelOsoba.getListaOsoba(), JMBG);
		Utility.searchOsobaByImeIliPrezime(modelOsoba.getListaOsoba(), imeIliPrezime);
		modelOsoba.fireTableDataChanged();
		selektovanaOsoba = null;
	}
	
	public void ponistiPretraguOsoba(){
		jTxtJMBGOsoba.setText("");
		jTxtImeIliPrezimeOsoba.setText("");
		if(modelOsoba!=null){
			ArrayList<Osoba> osobe = new ArrayList<Osoba>();
			osobe.addAll(StudentDB.listaStudenta);
			osobe.addAll(ProfesorDB.listaProfesora);
			modelOsoba.setNewList(osobe);
			selektovanaOsoba=null;
		}
	}
	
	public void traziStudenta(){
		String JMBG = jTxtJMBGStudent.getText();
		String imeIliPrezime = jTxtImeIliPrezimeStudent.getText();
		String index = jTxtIndexStudent.getText();
		String tipFinansiranje = jTxtTipFinansiranjaOdStudenta.getText();
		String godinaUpisaOd = jTxtGodinaUpisaOdStudent.getText();
		String godinaUpisaDo = jTxtGodinaUpisaDoStudent.getText();
		
		Utility.searchOsobaByJMBG(modelStudent.getListaStudenta(), JMBG);
		Utility.searchOsobaByImeIliPrezime(modelStudent.getListaStudenta(), imeIliPrezime);
		Utility.searchStudentByIndex(modelStudent.getListaStudenta(), index);
		Utility.searchStudentByTipFinansiranja(modelStudent.getListaStudenta(), tipFinansiranje);
		Utility.searchStudentByGodinaUpisaOdDo(modelStudent.getListaStudenta(), godinaUpisaOd, godinaUpisaDo);
		modelStudent.fireTableDataChanged();
		selektovanStudent = null;
	}
	
	public void ponistiPretraguStudenta(){
		jTxtJMBGStudent.setText("");
		jTxtImeIliPrezimeStudent.setText("");
		jTxtIndexStudent.setText("");
		jTxtTipFinansiranjaOdStudenta.setText("");
		jTxtGodinaUpisaOdStudent.setText("");
		jTxtGodinaUpisaDoStudent.setText("");
		if(modelStudent!=null){
			modelStudent.setNewList(StudentDB.listaStudenta);
			selektovanStudent=null;
		}
	}
	
	public void traziProfesora(){
		
	}
	
	public void ponistiPretraguProfesora(){
		
	}
	
	public void ocitajPodatkeIzBaze(){
		
		HobiDB.ocintan=false;
		KategorijaFinanasiranjaDB.ocintan=false;
		StudentDB.ocintan=false;
		ProfesorDB.ocintan=false;
		
		HobiDB.citajIzFajla();
		KategorijaFinanasiranjaDB.citajIzFajla();
		StudentDB.citajIzFajla();
		ProfesorDB.citajIzFajla();
		
		osveziModeleTabela();
	}
	
	public void sacuvajPodatkeUBazu(){
		HobiDB.pisiUFajl();
		KategorijaFinanasiranjaDB.pisiUFajl();
		StudentDB.pisiUFajl();
		ProfesorDB.pisiUFajl();
	}
	
	public void osveziModeleTabela(){
		if(modelOsoba!=null){
			ArrayList<Osoba> osobe = new ArrayList<Osoba>();
			osobe.addAll(StudentDB.listaStudenta);
			osobe.addAll(ProfesorDB.listaProfesora);
			modelOsoba.setNewList(osobe);
			selektovanaOsoba=null;
		}
		if(modelStudent!=null){
			modelStudent.setNewList(StudentDB.listaStudenta);
			selektovanStudent=null;
		}
		if(modelProfesor!=null){
			modelProfesor.setNewList(ProfesorDB.listaProfesora);
			selektovanProfesor=null;
		}
	}
}

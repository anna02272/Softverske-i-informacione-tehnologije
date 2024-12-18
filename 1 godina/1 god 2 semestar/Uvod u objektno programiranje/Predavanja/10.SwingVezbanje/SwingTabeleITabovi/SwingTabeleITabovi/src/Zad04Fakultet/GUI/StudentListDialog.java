package Zad04Fakultet.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Zad04Fakultet.DataClasses.IntPreuzmiOsoba;
import Zad04Fakultet.DataClasses.Osoba;
import Zad04Fakultet.DataClasses.Student;
import Zad04Fakultet.Database.StudentDB;
import Zad04Fakultet.ListAndTableModels.StudentListModel;

@SuppressWarnings({"serial","rawtypes", "unchecked"})
public class StudentListDialog extends JDialog {

	String sP = System.getProperty("file.separator");
	private int mode;
	public Osoba selektovana;
	
	//GUI
	//PRVI RED
	JLabel jLblSelektovani;
		
	//DRUGI RED
	JPanel 	jCentralniPanel;
	JButton jBtnPrikaziSve;
	JButton jBtnPrikaziAktivne;
	JButton jBrnPrikaziObrisane;
	
	JScrollPane jScrol1;
	JList jList1;
	StudentListModel modelListe;
	
	//ZADNJI RED
	JPanel jZadnjiPanel;
	JButton jBtnAddNew;
	JButton jBtnEditExisting;
	JButton jBtnRemoveExisting;
	JButton jBtnViewExisting;
	JButton jBtnGetSelected;

	public StudentListDialog(JFrame owner, String title, boolean modal, int md) {
		super(owner, title, modal);
		mode = md;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		String putanja = "."+sP+"resource"+sP+"images"+sP+"smiley"+sP+"happy_smiley_face.jpg";
		postaviIkonicuProzora(putanja);
		postaviProstorZaCrtanjeKomponenti(); 
		podesiOsobineKomponentiUOdnosuNaRezimRada();
        pack();
	}

	public StudentListDialog(JDialog owner, String title, boolean modal, int md) {
		super(owner, title, modal);
		mode = md;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		String putanja = "."+sP+"resource"+sP+"images"+sP+"smiley"+sP+"happy_smiley_face.jpg";
		postaviIkonicuProzora(putanja);
		postaviProstorZaCrtanjeKomponenti(); 
		podesiOsobineKomponentiUOdnosuNaRezimRada();
        pack();
	}
	
	public void postaviIkonicuProzora(String putanja){
		Image img = Toolkit.getDefaultToolkit().getImage(putanja); //preuzima sliku sa definisane putanje koja može biti tipa JPG i PNG
		setIconImage(img); //postavlja ikonicu prozora
	}
	
	public void postaviProstorZaCrtanjeKomponenti(){

		//PRVI RED
		jLblSelektovani = new JLabel("Selektovani student je:");
		getContentPane().add(jLblSelektovani, BorderLayout.NORTH);
	
		//DRUGI RED
		jCentralniPanel = new JPanel();
		jCentralniPanel.setLayout(new BorderLayout());
		getContentPane().add(jCentralniPanel, BorderLayout.CENTER);
		
		jBtnPrikaziSve = new JButton("Prikazi Sve");
		jBtnPrikaziSve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modelListe.setNewList(StudentDB.listaStudenta);
			}
		});
		
		jBtnPrikaziAktivne = new JButton("Prikazi Aktivne");
		jBtnPrikaziAktivne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Student> temp = new ArrayList<Student>();
				temp.addAll(StudentDB.listaStudenta);
				for (int i = temp.size()-1; i >= 0; i--) {
					Student el = temp.get(i);
					if(el.isStatusZapisa()==false)
						temp.remove(i);
				}
				modelListe.setNewList(temp);
			}
		});
		
		jBrnPrikaziObrisane = new JButton("Prikazi Obrisane");
		jBrnPrikaziObrisane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Student> temp = new ArrayList<Student>();
				temp.addAll(StudentDB.listaStudenta);
				for (int i = temp.size()-1; i >= 0; i--) {
					Student el = temp.get(i);
					if(el.isStatusZapisa()==true)
						temp.remove(i);
				}
				modelListe.setNewList(temp);
			}
		});
		
		JPanel 	jListaPrikazPanel = new JPanel();
		jCentralniPanel.add(jListaPrikazPanel, BorderLayout.NORTH);
		jListaPrikazPanel.add(jBtnPrikaziSve);
		jListaPrikazPanel.add(jBtnPrikaziAktivne);
		jListaPrikazPanel.add(jBrnPrikaziObrisane);
		
		modelListe = new StudentListModel(StudentDB.listaStudenta);
		jList1 = new JList(modelListe);
		jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jList1.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				 if (!e.getValueIsAdjusting() && jList1.getSelectedValue()!=null) {  	
			        	Osoba os = (Osoba) jList1.getSelectedValue();
			        	postaviSelektovanogStudenta(os);
			        }
			}
		});

		jScrol1 = new JScrollPane(jList1); //kreiranje vertikalnih i horizontalnih klizaca
		jCentralniPanel.add(jScrol1, BorderLayout.CENTER);
		
		
		//ZADNJI RED
		jZadnjiPanel = new JPanel();
		jZadnjiPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Opcije rada sa listom", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		getContentPane().add(jZadnjiPanel, BorderLayout.SOUTH);
		
		jBtnAddNew = new JButton("Dodaj Novog");
		jBtnAddNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dodajNovogStudenta();
			}
		});
		
		jBtnEditExisting = new JButton("Izmeni selektovanog");
		jBtnEditExisting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				izmeniPostojecegStudenta();
			}
		});
		jBtnRemoveExisting = new JButton("Obri\u0161i selektovanog");
		jBtnRemoveExisting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				obrisiPostojecegStudenta();
			}
		});
		jBtnViewExisting = new JButton("Pregled selektovanog");
		jBtnViewExisting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pregledPostojecegStudenta();
			}
		});
		jBtnGetSelected = new JButton("Preuzmi selektovanog");
		jBtnGetSelected.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				vratiSelektovanu();
			}
		});
		
		jZadnjiPanel.add(jBtnAddNew);
		jZadnjiPanel.add(jBtnEditExisting);
		jZadnjiPanel.add(jBtnRemoveExisting);
		jZadnjiPanel.add(jBtnViewExisting);
		jZadnjiPanel.add(jBtnGetSelected);
	}

	public void dodajNovogStudenta(){
		Student st = new Student("", "", "", "",null);
		StudentDialog stD = new StudentDialog(this, "Dodavanje novog Studenta", true, st, UtilityDialog.UNOS);
		stD.setVisible(true);
		if(!st.getJMBG().equals("")){
			StudentDB.listaStudenta.add(st);
			modelListe.addElement(selektovana);		//redefinisana metoda u modelu, poziva i fire metodu koja implicitno poziva iscrtavanje liste
			postaviSelektovanogStudenta(selektovana);
		}
	}

	public void izmeniPostojecegStudenta(){
		if(selektovana!=null && selektovana instanceof Student){
			StudentDialog stD = new StudentDialog(this, "Izmena postoje\u0107eg Studenta", true, (Student)selektovana, UtilityDialog.IZMENA);
			stD.setVisible(true);
			int sel = jList1.getSelectedIndex();
			modelListe.setElementAt(selektovana, sel); //redefinisana metoda u modelu, poziva i fire metodu koja implicitno poziva iscrtavanje liste
			//jList1.repaint();  
			postaviSelektovanogStudenta(selektovana);
		}
		else {
			JOptionPane.showMessageDialog(null, "Nije selektovan ni jedan student", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void obrisiPostojecegStudenta(){
		if(selektovana!=null && selektovana instanceof Student){
			//Custom button text
			Object[] naziviOpcija = {"Da, \u017Eelim", "Ne dolazi u obzir!", "Odustani"};
			int n2 = JOptionPane.showOptionDialog(null, "Da li ste sigurni da \u017Eelite obrisati selektovanog studenta?", "Dialog potvrde",
			    JOptionPane.YES_NO_CANCEL_OPTION,	
			    JOptionPane.QUESTION_MESSAGE,		//tip poruke moze biti QUESTION_MESSAGE , PLAIN_MESSAGE
			    null,								//korisnicka ikonica, null ako ne postoji
			    naziviOpcija,						//nazivi dugmadi
			    naziviOpcija[1]);					//inicijalna vrednost
			
			if(n2 == 0){
				selektovana.setStatusZapisa(false); //logicko brisanje
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Nije selektovan ni jedan student", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void pregledPostojecegStudenta(){
		if(selektovana!=null && selektovana instanceof Student){
			StudentDialog stD = new StudentDialog(this, "Pregled postoje\u0107eg Studenta", true, (Student)selektovana, UtilityDialog.PREGLED);
			stD.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Nije selektovan ni jedan student", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void vratiSelektovanu(){
		Window win = getOwner();
		if(win!=null){
			if(win instanceof IntPreuzmiOsoba){
				IntPreuzmiOsoba roditelj = (IntPreuzmiOsoba) win;
				roditelj.preuzmiOsobu(selektovana);
			}
		}
		dispose();
	}
	
	public void postaviSelektovanogStudenta(Osoba os){
		selektovana = os;
		jLblSelektovani.setText("Selektovani student je:" + os);
	}
	
	public void postaviSelektovanogStudentaIModelListe(Osoba os){
		if(modelListe.indexOf(os)!=-1){
			jList1.setSelectedValue(os, true);
			selektovana = os;
			jLblSelektovani.setText("Selektovani student je:" + os);
		}	
	}
	
	public void podesiOsobineKomponentiUOdnosuNaRezimRada(){
		if (mode == UtilityDialog.PREGLED){
			jBtnAddNew.setEnabled(true);
			jBtnEditExisting.setEnabled(true);
			jBtnRemoveExisting.setEnabled(true);
			jBtnViewExisting.setEnabled(true);
			jBtnGetSelected.setEnabled(false);
		}
		else if(mode == UtilityDialog.PREZMI){
			jBtnAddNew.setEnabled(false);
			jBtnEditExisting.setEnabled(false);
			jBtnRemoveExisting.setEnabled(false);
			jBtnViewExisting.setEnabled(false);
			jBtnGetSelected.setEnabled(true);
		}
	}
}

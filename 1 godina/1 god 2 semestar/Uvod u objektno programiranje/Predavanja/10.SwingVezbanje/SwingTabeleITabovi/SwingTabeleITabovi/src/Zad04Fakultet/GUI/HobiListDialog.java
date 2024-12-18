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

import Zad04Fakultet.DataClasses.Hobi;
import Zad04Fakultet.DataClasses.IntPreuzmiHobi;
import Zad04Fakultet.Database.HobiDB;
import Zad04Fakultet.ListAndTableModels.HobiListModel;

@SuppressWarnings({"serial","rawtypes", "unchecked"})
public class HobiListDialog extends JDialog {

	public Hobi selektovana;
	
	String sP = System.getProperty("file.separator");
	private int mode;
	
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
	HobiListModel modelListe;
	
	//ZADNJI RED
	JPanel jZadnjiPanel;
	JButton jBtnAddNew;
	JButton jBtnEditExisting;
	JButton jBtnRemoveExisting;
	JButton jBtnViewExisting;
	JButton jBtnGetSelected;
	
	public HobiListDialog(JFrame owner, String title, boolean modal, int md) {
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

	public HobiListDialog(JDialog owner, String title, boolean modal, int md) {
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
		jLblSelektovani = new JLabel("Selektovana kategorija finansiranja je:");
		getContentPane().add(jLblSelektovani, BorderLayout.NORTH);
	
		//DRUGI RED
		jCentralniPanel = new JPanel();
		jCentralniPanel.setLayout(new BorderLayout());
		getContentPane().add(jCentralniPanel, BorderLayout.CENTER);
		
		jBtnPrikaziSve = new JButton("Prikazi Sve");
		jBtnPrikaziSve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modelListe.setNewList(HobiDB.lista);
			}
		});
		
		jBtnPrikaziAktivne = new JButton("Prikazi Aktivne");
		jBtnPrikaziAktivne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Hobi> temp = new ArrayList<Hobi>();
				temp.addAll(HobiDB.lista);
				for (int i = temp.size()-1; i >= 0; i--) {
					Hobi el = temp.get(i);
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
				ArrayList<Hobi> temp = new ArrayList<Hobi>();
				temp.addAll(HobiDB.lista);
				for (int i = temp.size()-1; i >= 0; i--) {
					Hobi el = temp.get(i);
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
		
		modelListe = new HobiListModel(HobiDB.lista);
		jList1 = new JList(modelListe);
		jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jList1.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				 if (!e.getValueIsAdjusting() && jList1.getSelectedValue()!=null) {  	
					 	Hobi h = (Hobi) jList1.getSelectedValue();
			        	postaviSelektovanu(h);
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
				dodajNovu();
			}
		});
		jBtnEditExisting = new JButton("Izmeni selektovanog");
		jBtnEditExisting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				izmeniPostojecu();
			}
		});
		jBtnRemoveExisting = new JButton("Obri\u0161i selektovanog");
		jBtnRemoveExisting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				obrisiPostojecu();
			}
		});
		jBtnViewExisting = new JButton("Pregled selektovanog");
		jBtnViewExisting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pregledPostojece();
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
	public void dodajNovu(){
		selektovana = new Hobi("", "");
//		HobiDijalog di = new HobiDijalog(this, "Prozor za dodavanje hobija", true, selektovana, UtilityDialog.UNOS);
//		di.setVisible(true);
//		if(!selektovana.getSifra().equals("")){
//			modelListe.addElement(selektovana);
//			postaviSelektovanuIModelListe(selektovana);
//		}
	}

	public void izmeniPostojecu(){
		if(selektovana!=null && selektovana instanceof Hobi){
//			HobiDijalog di = new HobiDijalog(this, "Izmena postoje\u0107eg hobija", true, selektovana, UtilityDialog.IZMENA);
//			di.setVisible(true);
//			int sel = jList1.getSelectedIndex();
//			modelListe.setElementAt(selektovana, sel); //redefinisana metoda u modelu, poziva i fire metodu koja implicitno poziva iscrtavanje liste  
//			postaviSelektovanuIModelListe(selektovana);
		}
		else {
			JOptionPane.showMessageDialog(null, "Nije selektovan ni jedna kategorija", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void obrisiPostojecu(){
		if(selektovana!=null && selektovana instanceof Hobi){
			//Custom button text
			Object[] naziviOpcija = {"Da, \u017Eelim", "Ne dolazi u obzir!", "Odustani"};
			int n2 = JOptionPane.showOptionDialog(null, "Da li ste sigurni da \u017Eelite obrisati selektovani hobi?", "Dialog potvrde",
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
			JOptionPane.showMessageDialog(null, "Nije selektovan ni jedan hobi", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void pregledPostojece(){
		if(selektovana!=null && selektovana instanceof Hobi){
//			HobiDijalog di = new HobiDijalog(this, "Pregled postoje\u0107eg hobija", true, selektovana, UtilityDialog.PREGLED);
//			di.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Nije selektovan ni jedna kategorija", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void vratiSelektovanu(){
		Window win = getOwner();
		if(win!=null){
			if(win instanceof IntPreuzmiHobi){
				IntPreuzmiHobi roditelj = (IntPreuzmiHobi) win;
				roditelj.preuzmiHobi(selektovana);
			}
		}
		dispose();
	}
	
	public void postaviSelektovanu(Hobi el){
		selektovana = el;
		jLblSelektovani.setText("Selektovan hobi je:"  + el.getNaziv());
	}
	
	public void postaviSelektovanuIModelListe(Hobi el){
		if(modelListe.indexOf(el)!=-1){
			jList1.setSelectedValue(el, true);
			selektovana = el;
			jLblSelektovani.setText("Selektovan hobi je:"  + el.getNaziv());
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

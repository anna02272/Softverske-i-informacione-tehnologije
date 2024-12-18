package Zad04Fakultet.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import Zad04Fakultet.DataClasses.IntPreuzmiKategorijaFinansiranja;
import Zad04Fakultet.DataClasses.KategorijaFinanasiranja;
import Zad04Fakultet.Database.KategorijaFinanasiranjaDB;
import Zad04Fakultet.ListAndTableModels.KategorijaFinansiranjaListModel;

@SuppressWarnings({"serial","rawtypes", "unchecked"})
public class KategorijaFinansiranjaListDialog extends JDialog {

	public KategorijaFinanasiranja selektovana;
	
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
	KategorijaFinansiranjaListModel modelListe;
	
	//ZADNJI RED
	JPanel jZadnjiPanel;
	JButton jBtnAddNew;
	JButton jBtnEditExisting;
	JButton jBtnRemoveExisting;
	JButton jBtnViewExisting;
	JButton jBtnGetSelected;
	
	public KategorijaFinansiranjaListDialog() {	
		setTitle("Lista kategorija");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);					
        postaviProstorZaCrtanjeKomponenti();
        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setVisible(true);
	}
	
	public KategorijaFinansiranjaListDialog(JFrame owner, String title, boolean modal, int md) {
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

	public KategorijaFinansiranjaListDialog(JDialog owner, String title, boolean modal, int md) {
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
				modelListe.setNewList(KategorijaFinanasiranjaDB.listaKategorijaFinanasiranja);
			}
		});
		
		jBtnPrikaziAktivne = new JButton("Prikazi Aktivne");
		jBtnPrikaziAktivne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<KategorijaFinanasiranja> temp = new ArrayList<KategorijaFinanasiranja>();
				temp.addAll(KategorijaFinanasiranjaDB.listaKategorijaFinanasiranja);
				for (int i = temp.size()-1; i >= 0; i--) {
					KategorijaFinanasiranja el = temp.get(i);
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
				ArrayList<KategorijaFinanasiranja> temp = new ArrayList<KategorijaFinanasiranja>();
				temp.addAll(KategorijaFinanasiranjaDB.listaKategorijaFinanasiranja);
				for (int i = temp.size()-1; i >= 0; i--) {
					KategorijaFinanasiranja el = temp.get(i);
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
		
		modelListe = new KategorijaFinansiranjaListModel(KategorijaFinanasiranjaDB.listaKategorijaFinanasiranja);
		jList1 = new JList(modelListe);
		jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jList1.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				 if (!e.getValueIsAdjusting() && jList1.getSelectedValue()!=null) {  	
					 	KategorijaFinanasiranja kf = (KategorijaFinanasiranja) jList1.getSelectedValue();
			        	postaviSelektovanu(kf);
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
		KategorijaFinanasiranja kat = new KategorijaFinanasiranja("", "", "");
		KategorijaFinansiranjaDijalog katFinD = new KategorijaFinansiranjaDijalog(this, "Prozor za dodavanje kategorije", true, kat, UtilityDialog.UNOS);
		katFinD.setVisible(true);
		if(!kat.getSifra().equals("")){
			KategorijaFinanasiranjaDB.listaKategorijaFinanasiranja.add(kat);
			modelListe.addElement(selektovana);
			postaviSelektovanuIModelListe(selektovana);
		}
	}

	public void izmeniPostojecu(){
		if(selektovana!=null && selektovana instanceof KategorijaFinanasiranja){
			KategorijaFinansiranjaDijalog katFinD = new KategorijaFinansiranjaDijalog(this, "Izmena postoje\u0107e Kategorije finansiranja", true, selektovana, UtilityDialog.IZMENA);
			katFinD.setVisible(true);
			int sel = jList1.getSelectedIndex();
			modelListe.setElementAt(selektovana, sel); //redefinisana metoda u modelu, poziva i fire metodu koja implicitno poziva iscrtavanje liste
			//jList1.repaint();  
			postaviSelektovanuIModelListe(selektovana);
		}
		else {
			JOptionPane.showMessageDialog(null, "Nije selektovan ni jedna kategorija", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void obrisiPostojecu(){
		if(selektovana!=null && selektovana instanceof KategorijaFinanasiranja){
			//Custom button text
			Object[] naziviOpcija = {"Da, \u017Eelim", "Ne dolazi u obzir!", "Odustani"};
			int n2 = JOptionPane.showOptionDialog(null, "Da li ste sigurni da \u017Eelite obrisati selektovanu kategoriju finansiranja?", "Dialog potvrde",
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
			JOptionPane.showMessageDialog(null, "Nije selektovan ni jedna kategorija", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void pregledPostojece(){
		if(selektovana!=null && selektovana instanceof KategorijaFinanasiranja){
			KategorijaFinansiranjaDijalog katFinD = new KategorijaFinansiranjaDijalog(this, "Pregled postoje\u0107e Kategorije finansiranja", true, selektovana, UtilityDialog.PREGLED);
			katFinD.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "Nije selektovan ni jedna kategorija", "Gre\u0161ka", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void vratiSelektovanu(){
		Window win = getOwner();
		if(win!=null){
			if(win instanceof IntPreuzmiKategorijaFinansiranja){
				IntPreuzmiKategorijaFinansiranja roditelj = (IntPreuzmiKategorijaFinansiranja) win;
				roditelj.preuzmiKategorijaFinansiranja(selektovana);
			}
		}
		dispose();
	}
	
	public void postaviSelektovanu(KategorijaFinanasiranja el){
		selektovana = el;
		jLblSelektovani.setText("Selektovana kategorija finansiranja je:"  + el.getNaziv());
	}
	
	public void postaviSelektovanuIModelListe(KategorijaFinanasiranja el){
		if(modelListe.indexOf(el)!=-1){
			jList1.setSelectedValue(el, true);
			selektovana = el;
			jLblSelektovani.setText("Selektovana kategorija finansiranja je:"  + el.getNaziv());
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
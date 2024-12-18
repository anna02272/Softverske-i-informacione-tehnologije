package Zad04Fakultet.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Zad04Fakultet.DataClasses.KategorijaFinanasiranja;

@SuppressWarnings({"serial"})
public class KategorijaFinansiranjaDijalog extends JDialog{
	
	private KategorijaFinanasiranja kategorijaFinanasiranjaObrade;
	private int mode;
	
	private KategorijaFinanasiranja nadkategorijaFinanasiranja;
	
	String sP = System.getProperty("file.separator");
	
	//PRVI RED
	JLabel jLblSifra; JTextField jTxtSifra;
	
	//DRUGI RED
	JLabel jLblNaziv; JTextField jTxtNaziv;
	
	//TRECI RED
	JLabel jLblOpis; JTextArea jTxtAreaOpis;
	
	//CETVRTI RED
	JLabel jLblNadkategorijaFinansiranja; JTextField jTxtNadkategorijaFinansiranja; JButton jBtnPreuzmiKategoriju;
	
	//PETI RED
	JRadioButton jRdBtnStatusZapisaOpcija1; JRadioButton jRdBtnStatusZapisaOpcija2; ButtonGroup buttonGroupZaStatusZapisa;
	
	//ZADNJI RED
	JButton jBtnSave; JButton jBtnCancel;
			
	public KategorijaFinansiranjaDijalog(JFrame owner, String title, boolean modal, KategorijaFinanasiranja katFin, int md) {
		super(owner, title, modal);
		kategorijaFinanasiranjaObrade = katFin;
		mode = md;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		String putanja = "."+sP+"resource"+sP+"images"+sP+"smiley"+sP+"happy_smiley_face.jpg";
		postaviIkonicuProzora(putanja);
		postaviProstorZaCrtanjeKomponenti(); 
		podesiOsobineKomponentiUOdnosuNaRezimRada();
		postaviVrednostKomponenti();
        pack();
	}

	public KategorijaFinansiranjaDijalog(JDialog owner, String title, boolean modal, KategorijaFinanasiranja katFin, int md) {
		super(owner, title, modal);
		kategorijaFinanasiranjaObrade = katFin;
		mode = md;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		String putanja = "."+sP+"resource"+sP+"images"+sP+"smiley"+sP+"happy_smiley_face.jpg";
		postaviIkonicuProzora(putanja);
		postaviProstorZaCrtanjeKomponenti(); 
		podesiOsobineKomponentiUOdnosuNaRezimRada();
		postaviVrednostKomponenti();
        pack();
	}
	
	public void postaviIkonicuProzora(String putanja){
		Image img = Toolkit.getDefaultToolkit().getImage(putanja); //preuzima sliku sa definisane putanje koja može biti tipa JPG i PNG
		setIconImage(img); //postavlja ikonicu prozora
	}
	
	public void postaviProstorZaCrtanjeKomponenti(){
		setLayout(new GridBagLayout());
		
		//PRVI RED
		jLblSifra = new JLabel("sifra:");
		getContentPane().add(jLblSifra, UtilityDialog.vratiGBC(0, 0, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtSifra = new JTextField(13);
		getContentPane().add(jTxtSifra, UtilityDialog.vratiGBC(1, 0, 1, 1, GridBagConstraints.LINE_START, null));
		
		//DRUGI RED
		jLblNaziv = new JLabel("naziv:");
		getContentPane().add(jLblNaziv, UtilityDialog.vratiGBC(0, 1, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtNaziv = new JTextField(20);
		getContentPane().add(jTxtNaziv, UtilityDialog.vratiGBCFill(1, 1, GridBagConstraints.HORIZONTAL, 1, -1, null));
		
		//TRECI RED
		jLblOpis = new JLabel("opis:");
		getContentPane().add(jLblOpis, UtilityDialog.vratiGBC(0, 2, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtAreaOpis = new JTextArea();
		jTxtAreaOpis.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jTxtAreaOpis.setRows(5);	
		jTxtAreaOpis.setLineWrap(true);	
		
		JScrollPane jScrol1 = new JScrollPane(jTxtAreaOpis);
		getContentPane().add(jScrol1, UtilityDialog.vratiGBCFill(1, 2, GridBagConstraints.BOTH, 1, 1, null));
		
		//CETVRTI RED
		jLblNadkategorijaFinansiranja = new JLabel("nadkategorija finansiranja:");
		getContentPane().add(jLblNadkategorijaFinansiranja, UtilityDialog.vratiGBC(0, 3, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtNadkategorijaFinansiranja = new JTextField(20);
		jTxtNadkategorijaFinansiranja.setEditable(false);
		getContentPane().add(jTxtNadkategorijaFinansiranja, UtilityDialog.vratiGBCFill(1, 3, GridBagConstraints.HORIZONTAL, 1, -1, null));
		
		jBtnPreuzmiKategoriju = new JButton("Prezmi");
		getContentPane().add(jBtnPreuzmiKategoriju, UtilityDialog.vratiGBC(2, 3, 1, 1, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5)));
		
		//PETI RED
		JPanel jPanelStatusZapisa = new JPanel();
		jPanelStatusZapisa.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Status zapisa", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		getContentPane().add(jPanelStatusZapisa, new GridBagConstraints(0, 4, 2, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		jRdBtnStatusZapisaOpcija1 = new JRadioButton("Aktivan");
		jRdBtnStatusZapisaOpcija1.setSelected(true);
		jPanelStatusZapisa.add(jRdBtnStatusZapisaOpcija1);
		
		jRdBtnStatusZapisaOpcija2 = new JRadioButton("Obrisan");
		jPanelStatusZapisa.add(jRdBtnStatusZapisaOpcija2);
		
		buttonGroupZaStatusZapisa = new ButtonGroup();
		buttonGroupZaStatusZapisa.add(jRdBtnStatusZapisaOpcija1);
		buttonGroupZaStatusZapisa.add(jRdBtnStatusZapisaOpcija2);
		
		//ZADNJI RED
		jBtnSave = new JButton("Sacuvaj");
		jBtnSave.setMnemonic('S');
		jBtnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//provera
				if(proveriVrednostKomponenti()){
					postaviVrednostObjekta();
					dispose();
				}
			}
		});
		getContentPane().add(jBtnSave, UtilityDialog.vratiGBC(0, 5, 1, 1, GridBagConstraints.LINE_END, new Insets(5, 5, 5, 5)));
		
		jBtnCancel = new JButton("Odustani");
		jBtnCancel.setMnemonic('O');
		jBtnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		getContentPane().add(jBtnCancel, UtilityDialog.vratiGBC(1, 5, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
	}
	public boolean proveriVrednostKomponenti(){
boolean retVal	= true;
		
		boolean greskaUnosa = false;
		String  porukaUnosa = "Niste uneli sledece vrednosti:\n";
		
		if(jTxtSifra.getText().equals("")){
			greskaUnosa = true;
			porukaUnosa += "\t- sifra\n";
		}
		if(jTxtNaziv.getText().equals("")){
			greskaUnosa = true;
			porukaUnosa += "\t- naziv\n";
		}
		
		boolean greskaFormatiranja = false;
		String  porukaFormatiranja = "Niste pravilno uneli sledece vrednosti:\n";
		
		//provera za foramte jTextField komponenti npr za brojeve
		
		if (greskaUnosa) {
			JOptionPane.showMessageDialog(this, porukaUnosa, "Gre\u0161ka",JOptionPane.ERROR_MESSAGE);
			retVal = false;
		}
		else if(greskaFormatiranja){
			JOptionPane.showMessageDialog(this, porukaFormatiranja, "Gre\u0161ka",JOptionPane.ERROR_MESSAGE);
			retVal = false;
		}
		
		return retVal;
	}
	
	
	public void podesiOsobineKomponentiUOdnosuNaRezimRada(){
		if (mode == UtilityDialog.PREGLED){
			jTxtSifra.setEditable(false);
			jTxtNaziv.setEditable(false);
			jTxtNaziv.setEditable(false);
			jTxtAreaOpis.setEditable(false);
			jBtnPreuzmiKategoriju.setEnabled(false);
			jRdBtnStatusZapisaOpcija1.setEnabled(false);
			jRdBtnStatusZapisaOpcija2.setEnabled(false);
			jBtnSave.setEnabled(false);
			jBtnCancel.setEnabled(false);
		}
		else if(mode == UtilityDialog.UNOS){
			jTxtSifra.setEditable(true);
			jTxtNaziv.setEditable(true);
			jTxtAreaOpis.setEditable(true);
			jBtnPreuzmiKategoriju.setEnabled(true);
			jRdBtnStatusZapisaOpcija1.setEnabled(true);
			jRdBtnStatusZapisaOpcija2.setEnabled(true);
			jBtnSave.setEnabled(true);
			jBtnCancel.setEnabled(true);
		}
		else if (mode == UtilityDialog.IZMENA){
			jTxtSifra.setEditable(false);
			jTxtNaziv.setEditable(true);
			jTxtNaziv.setEditable(true);
			jTxtAreaOpis.setEditable(true);
			jBtnPreuzmiKategoriju.setEnabled(true);
			jRdBtnStatusZapisaOpcija1.setEnabled(true);
			jRdBtnStatusZapisaOpcija2.setEnabled(true);
			jBtnSave.setEnabled(true);
			jBtnCancel.setEnabled(true);
		}
	}
	
	public void postaviVrednostKomponenti(){
		jTxtSifra.setText(kategorijaFinanasiranjaObrade.getSifra());
		jTxtNaziv.setText(kategorijaFinanasiranjaObrade.getNaziv());
		jTxtAreaOpis.setText(kategorijaFinanasiranjaObrade.getOpis());
		nadkategorijaFinanasiranja=kategorijaFinanasiranjaObrade.getNadkategorija();
		if(kategorijaFinanasiranjaObrade.getNadkategorija()!=null)
			jTxtNadkategorijaFinansiranja.setText(kategorijaFinanasiranjaObrade.getNadkategorija().getNaziv());
		jRdBtnStatusZapisaOpcija1.setSelected(kategorijaFinanasiranjaObrade.isStatusZapisa());
		jRdBtnStatusZapisaOpcija2.setSelected(!kategorijaFinanasiranjaObrade.isStatusZapisa());
	}
	
	public void postaviVrednostObjekta(){
		kategorijaFinanasiranjaObrade.setSifra(jTxtSifra.getText());
		kategorijaFinanasiranjaObrade.setNaziv(jTxtNaziv.getText());
		kategorijaFinanasiranjaObrade.setOpis(jTxtAreaOpis.getText());
		kategorijaFinanasiranjaObrade.setNadkategorija(nadkategorijaFinanasiranja);
		kategorijaFinanasiranjaObrade.setStatusZapisa(jRdBtnStatusZapisaOpcija1.isSelected());
	}
	
	public KategorijaFinanasiranja getKategorijaFinanasiranjaObrade() {
		return kategorijaFinanasiranjaObrade;
	}

	public void setKategorijaFinanasiranjaObrade(
			KategorijaFinanasiranja kategorijaFinanasiranjaObrade) {
		this.kategorijaFinanasiranjaObrade = kategorijaFinanasiranjaObrade;
	}

	public KategorijaFinanasiranja getNadkategorijaFinanasiranja() {
		return nadkategorijaFinanasiranja;
	}

	public void setNadkategorijaFinanasiranja(
			KategorijaFinanasiranja nadkategorijaFinanasiranja) {
		this.nadkategorijaFinanasiranja = nadkategorijaFinanasiranja;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
		podesiOsobineKomponentiUOdnosuNaRezimRada();
	}
}

package Zad04Fakultet.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Zad04Fakultet.DataClasses.Hobi;
import Zad04Fakultet.DataClasses.IntPreuzmiHobi;
import Zad04Fakultet.DataClasses.IntPreuzmiKategorijaFinansiranja;
import Zad04Fakultet.DataClasses.KategorijaFinanasiranja;
import Zad04Fakultet.DataClasses.Student;
import Zad04Fakultet.ListAndTableModels.HobiListModel;


@SuppressWarnings({"serial","rawtypes", "unchecked"})
public class StudentDialog extends JDialog implements IntPreuzmiKategorijaFinansiranja, IntPreuzmiHobi{
	
	private Student studentObrade;
	private int mode;
	
	private KategorijaFinanasiranja katFinOdStudentaObrade;
	
	String sP = System.getProperty("file.separator");
	
	//PRVI RED
	JLabel jLblJMBG; JTextField jTxtJMBG;
	
	//DRUGI RED
	JLabel jLblIme; JTextField jTxtIme;
	
	//TRECI RED
	JLabel jLblPrezime; JTextArea jTxtAreaPrezime;
	
	//CETVRTI RED
	JLabel jLblIndex; JTextField jTxtIndex;
	
	//PETI RED
	JLabel jLblKategorijaFinansiranja; JTextField jTxtKategorijaFinansiranja; JButton jBtnPreuzmiKategoriju;
	
	//SESTI RED
	JRadioButton jRdBtnStatusZapisaOpcija1; JRadioButton jRdBtnStatusZapisaOpcija2; ButtonGroup buttonGroupZaStatusZapisa;
	
	//SEDMI RED
	JPanel 				jPanelHobiji;
	JLabel				jLblSelektovaniHobi;
	JScrollPane 		jScrolHobiji;
	JList				jListHobiji;
	HobiListModel 		modelListHobiji;
	JButton				jBtnDodajHobi;
	JButton				jBtnUkloniHobi;
	
	//ZADNJI RED
	JButton jBtnSave; JButton jBtnCancel;
			
	public StudentDialog(JFrame owner, String title, boolean modal, Student st, int md) {
		super(owner, title, modal);
		studentObrade = st;
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

	public StudentDialog(JDialog owner, String title, boolean modal, Student st, int md) {
		super(owner, title, modal);
		studentObrade = st;
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
		jLblJMBG = new JLabel("JMBG:");
		getContentPane().add(jLblJMBG, UtilityDialog.vratiGBC(0, 0, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtJMBG = new JTextField(13);
		getContentPane().add(jTxtJMBG, UtilityDialog.vratiGBC(1, 0, 1, 1, GridBagConstraints.LINE_START, null));
		
		//DRUGI RED
		jLblIme = new JLabel("ime:");
		getContentPane().add(jLblIme, UtilityDialog.vratiGBC(0, 1, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtIme = new JTextField(20);
		getContentPane().add(jTxtIme, UtilityDialog.vratiGBCFill(1, 1, GridBagConstraints.HORIZONTAL, 1, -1, null));
		
		//TRECI RED
		jLblPrezime = new JLabel("prezime:");
		getContentPane().add(jLblPrezime, UtilityDialog.vratiGBC(0, 2, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtAreaPrezime = new JTextArea();
		jTxtAreaPrezime.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jTxtAreaPrezime.setRows(5);	
		jTxtAreaPrezime.setLineWrap(true);	
		
		JScrollPane jScrol1 = new JScrollPane(jTxtAreaPrezime);
		getContentPane().add(jScrol1, UtilityDialog.vratiGBCFill(1, 2, GridBagConstraints.BOTH, 1, 1, null));
		
		//CETVRTI RED
		jLblIndex = new JLabel("index:");
		getContentPane().add(jLblIndex, UtilityDialog.vratiGBC(0, 3, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtIndex = new JTextField(20);
		getContentPane().add(jTxtIndex, UtilityDialog.vratiGBCFill(1, 3, GridBagConstraints.HORIZONTAL, 1, -1, null));
		
		//PETI RED
		jLblKategorijaFinansiranja = new JLabel("kategorija finansiranja:");
		getContentPane().add(jLblKategorijaFinansiranja, UtilityDialog.vratiGBC(0, 4, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
		jTxtKategorijaFinansiranja = new JTextField(20);
		jTxtKategorijaFinansiranja.setEditable(false);
		getContentPane().add(jTxtKategorijaFinansiranja, UtilityDialog.vratiGBCFill(1, 4, GridBagConstraints.HORIZONTAL, 1, -1, null));
		
		jBtnPreuzmiKategoriju = new JButton("Preuzmi");
		jBtnPreuzmiKategoriju.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pozoviDijalogZaPreuzimanjeKategorijaFinansiranja();
			}
		});
		
		getContentPane().add(jBtnPreuzmiKategoriju, UtilityDialog.vratiGBC(2, 4, 1, 1, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5)));
		
		//SESTI RED
		JPanel jPanelStatusZapisa = new JPanel();
		jPanelStatusZapisa.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Status zapisa", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		getContentPane().add(jPanelStatusZapisa, new GridBagConstraints(0, 5, 2, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		jRdBtnStatusZapisaOpcija1 = new JRadioButton("Aktivan");
		jRdBtnStatusZapisaOpcija1.setSelected(true);
		jPanelStatusZapisa.add(jRdBtnStatusZapisaOpcija1);
		
		jRdBtnStatusZapisaOpcija2 = new JRadioButton("Obrisan");
		jPanelStatusZapisa.add(jRdBtnStatusZapisaOpcija2);
		
		buttonGroupZaStatusZapisa = new ButtonGroup();
		buttonGroupZaStatusZapisa.add(jRdBtnStatusZapisaOpcija1);
		buttonGroupZaStatusZapisa.add(jRdBtnStatusZapisaOpcija2);
		
		//SEDMI RED
		postaviPanelZaHobije();
		
		//ZADNJI RED
		jBtnSave = new JButton("Sacuvaj");
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
		jBtnSave.setMnemonic('S');
		getContentPane().add(jBtnSave, UtilityDialog.vratiGBC(0, 7, 1, 1, GridBagConstraints.LINE_END, new Insets(5, 5, 5, 5)));
		
		jBtnCancel = new JButton("Odustani");
		jBtnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		jBtnCancel.setMnemonic('O');
		getContentPane().add(jBtnCancel, UtilityDialog.vratiGBC(1, 7, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
		
	}
	
	private void postaviPanelZaHobije(){
		jPanelHobiji = new JPanel();
		jPanelHobiji.setLayout(new GridBagLayout());
		jPanelHobiji.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Lista Vrednsoti", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		getContentPane().add(jPanelHobiji, new GridBagConstraints(0, 6, 2, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		//PRVI RED
		jLblSelektovaniHobi = new JLabel("Hobi:");
		jPanelHobiji.add(jLblSelektovaniHobi, UtilityDialog.vratiGBC(0, 0, 2, 1, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5)));
		
		//DRUGI RED
		modelListHobiji = new HobiListModel(new ArrayList<Hobi>());
		jListHobiji = new JList(modelListHobiji);
		jListHobiji.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListHobiji.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jListHobiji.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				 if (!e.getValueIsAdjusting() && jListHobiji.getSelectedValue()!=null) {  	
					 jLblSelektovaniHobi.setText(jListHobiji.getSelectedValue().toString());
			        }
			}
		});

		jScrolHobiji = new JScrollPane(jListHobiji); //kreiranje vertikalnih i horizontalnih klizaca
		jPanelHobiji.add(jScrolHobiji, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0)); 
				
		//ZADNJI RED
		jBtnDodajHobi = new JButton("DODAJ");
		jBtnDodajHobi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pozoviDijalogZaPreuzimanjeHobija();
			}
		});
		jPanelHobiji.add(jBtnDodajHobi, UtilityDialog.vratiGBC(0, 2, 1, 1, GridBagConstraints.LINE_END, new Insets(5, 5, 5, 5)));
		
		jBtnUkloniHobi = new JButton("OBRI\u0160I");
		jBtnUkloniHobi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int slektovan = jListHobiji.getSelectedIndex();
				if(slektovan!=-1 && slektovan>=0){
					modelListHobiji.remove(slektovan);
					if(modelListHobiji.getSize()>0){
						jListHobiji.setSelectedIndex(0);
					}
				}
			}
		});
		jPanelHobiji.add(jBtnUkloniHobi, UtilityDialog.vratiGBC(1, 2, 1, 1, GridBagConstraints.LINE_START, new Insets(5, 5, 5, 5)));
	}
	
	public boolean proveriVrednostKomponenti(){
		boolean retVal	= true;
		
		boolean greskaUnosa = false;
		String  porukaUnosa = "Niste uneli sledece vrednosti:\n";
		
		if(jTxtJMBG.getText().equals("")){
			greskaUnosa = true;
			porukaUnosa += "\t- JMBG\n";
		}
		if(jTxtIme.getText().equals("")){
			greskaUnosa = true;
			porukaUnosa += "\t- ime\n";
		}
		if(jTxtAreaPrezime.getText().equals("")){
			greskaUnosa = true;
			porukaUnosa += "\t- prezime\n";
		}
		
		if(jTxtIndex.getText().equals("")){
			greskaUnosa = true;
			porukaUnosa += "\t- broj indexa\n";
		}
		if(katFinOdStudentaObrade==null){
			greskaUnosa = true;
			porukaUnosa += "\t- kategorija finansiranja\n";
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
			jTxtJMBG.setEditable(false);
			jTxtIme.setEditable(false);
			jTxtIme.setEditable(false);
			jTxtAreaPrezime.setEditable(false);
			jTxtIndex.setEditable(false);
			jBtnPreuzmiKategoriju.setEnabled(false);
			jBtnDodajHobi.setEnabled(false);
			jBtnUkloniHobi.setEnabled(false);
			jRdBtnStatusZapisaOpcija1.setEnabled(false);
			jRdBtnStatusZapisaOpcija2.setEnabled(false);
			jBtnSave.setEnabled(false);
			jBtnCancel.setEnabled(false);
		}
		else if(mode == UtilityDialog.UNOS){
			jTxtJMBG.setEditable(true);
			jTxtIme.setEditable(true);
			jTxtAreaPrezime.setEditable(true);
			jTxtIndex.setEditable(true);
			jBtnPreuzmiKategoriju.setEnabled(true);
			jBtnDodajHobi.setEnabled(true);
			jBtnUkloniHobi.setEnabled(true);
			jRdBtnStatusZapisaOpcija1.setEnabled(true);
			jRdBtnStatusZapisaOpcija2.setEnabled(true);
			jBtnSave.setEnabled(true);
			jBtnCancel.setEnabled(true);
		}
		else if (mode == UtilityDialog.IZMENA){
			jTxtJMBG.setEditable(false);
			jTxtIme.setEditable(true);
			jTxtIme.setEditable(true);
			jTxtAreaPrezime.setEditable(true);
			jTxtIndex.setEditable(true);
			jBtnPreuzmiKategoriju.setEnabled(true);
			jBtnDodajHobi.setEnabled(true);
			jBtnUkloniHobi.setEnabled(true);
			jRdBtnStatusZapisaOpcija1.setEnabled(true);
			jRdBtnStatusZapisaOpcija2.setEnabled(true);
			jBtnSave.setEnabled(true);
			jBtnCancel.setEnabled(true);
		}
	}
	
	public void postaviVrednostKomponenti(){
		jTxtJMBG.setText(studentObrade.getJMBG());
		jTxtIme.setText(studentObrade.getIme());
		jTxtAreaPrezime.setText(studentObrade.getPrezime());
		jTxtIndex.setText(studentObrade.getIndex());
		katFinOdStudentaObrade=studentObrade.getKatFin();
		if(katFinOdStudentaObrade!=null)
			jTxtKategorijaFinansiranja.setText(studentObrade.getKatFin().getNaziv());
		
		modelListHobiji.setNewList(studentObrade.getHobiji());
		if(modelListHobiji.getSize()>0){
			jListHobiji.setSelectedIndex(0);
		}
			
		jRdBtnStatusZapisaOpcija1.setSelected(studentObrade.isStatusZapisa());
		jRdBtnStatusZapisaOpcija2.setSelected(!studentObrade.isStatusZapisa());
	}
	
	public void postaviVrednostObjekta(){
		studentObrade.setJMBG(jTxtJMBG.getText());
		studentObrade.setIme(jTxtIme.getText());
		studentObrade.setPrezime(jTxtAreaPrezime.getText());
		studentObrade.setIndex(jTxtIndex.getText());
		studentObrade.setKatFin(katFinOdStudentaObrade);
		studentObrade.getHobiji().clear();
		studentObrade.getHobiji().addAll(modelListHobiji.getListaElemenata());
		studentObrade.setStatusZapisa(jRdBtnStatusZapisaOpcija1.isSelected());
	}
	
	public void pozoviDijalogZaPreuzimanjeKategorijaFinansiranja(){
		KategorijaFinansiranjaListDialog kat = new KategorijaFinansiranjaListDialog(this, "Prikaz liste kategorija", true, UtilityDialog.PREZMI);
		kat.postaviSelektovanuIModelListe(katFinOdStudentaObrade);
		kat.setVisible(true);
	}
	
	void pozoviDijalogZaPreuzimanjeHobija() {
		HobiListDialog h = new HobiListDialog(this, "Prikaz liste hobija", true, UtilityDialog.PREZMI);
		h.postaviSelektovanuIModelListe(null);
		h.setVisible(true);
	}
	
	@Override
	public void preuzmiKategorijaFinansiranja(KategorijaFinanasiranja el) {
		katFinOdStudentaObrade = el;
		if(katFinOdStudentaObrade!=null){
			jTxtKategorijaFinansiranja.setText(katFinOdStudentaObrade.getNaziv());
		}
	}

	@Override
	public void preuzmiHobi(Hobi el) {
		if(el!=null){
			modelListHobiji.addElement(el);
			jListHobiji.setSelectedValue(el, true);
		}
	}
	
	public Student getStudentObrade() {
		return studentObrade;
	}

	public void setStudentObrade(Student studentObrade) {
		this.studentObrade = studentObrade;
	}
	
	public KategorijaFinanasiranja getKatFinOdStudentaObrade() {
		return katFinOdStudentaObrade;
	}
	
	public void setKatFinOdStudentaObrade(
			KategorijaFinanasiranja katFinOdStudentaObrade) {
		this.katFinOdStudentaObrade = katFinOdStudentaObrade;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
		podesiOsobineKomponentiUOdnosuNaRezimRada();
	}
}

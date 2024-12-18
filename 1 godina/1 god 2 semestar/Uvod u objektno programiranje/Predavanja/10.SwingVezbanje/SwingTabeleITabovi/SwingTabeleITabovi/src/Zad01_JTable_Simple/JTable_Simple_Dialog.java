package Zad01_JTable_Simple;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class JTable_Simple_Dialog extends JDialog {

	public Racun selektovan;
	public ArrayList<Racun> listaRacuna;
	
	public String[] columnNames = {"Sifra", "Naziv", "U Upotrebi", "Stanje"};
	Object[][] data;
	
	//GUI
	//PRVI PANEL
	JPanel jPanelSearch;
	JLabel jLblSelektovan;
	
	//DRUGI PANEL
	JPanel 	jCentralniPanel;
	JButton jBtnPrikaziSve;
	JButton jBtnPrikaziAktivne;
	JButton jBrnPrikaziObrisane;
	
	JScrollPane jScrol1;
	JTable jTable1;
	
	//ZADNJI PANEL
	JPanel jPanelZanji;
	JButton jBtnAddNew;
	JButton jBtnEditExisting;
	JButton jBtnRemoveExisting;
	JButton jBtnViewExisting;
	JButton jBtnGetSelected;
	
	public JTable_Simple_Dialog(ArrayList<Racun> racuni) {
		listaRacuna = new ArrayList<Racun>();
		listaRacuna.addAll(racuni);
		setData(listaRacuna);
		
		setTitle("JTable Jednostavan primer");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension velicinaEkrana = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(new Point(velicinaEkrana.width/4, velicinaEkrana.height/4));
        postaviProstorZaCrtanjeKomponenti();
        pack();
        setSize(new Dimension(getWidth(), getHeight()/2));
        setVisible(true);
	}
	
	public void postaviProstorZaCrtanjeKomponenti(){
		
		//PRVI PANEL
		jPanelSearch = new JPanel();
		jPanelSearch.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Pretraga", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		getContentPane().add(jPanelSearch, BorderLayout.NORTH);
		
		jLblSelektovan = new JLabel("Selektovan racun je:");
		jPanelSearch.add(jLblSelektovan);
		
		//DRUGI PANEL
		jCentralniPanel = new JPanel();
		jCentralniPanel.setLayout(new BorderLayout());
		getContentPane().add(jCentralniPanel, BorderLayout.CENTER);
		
		jBtnPrikaziSve = new JButton("Prikazi Sve");
		jBtnPrikaziAktivne = new JButton("Prikazi Aktivne");
		jBrnPrikaziObrisane = new JButton("Prikazi Obrisane");
		
		JPanel 	jTabelaPrikazPanel = new JPanel();
		jCentralniPanel.add(jTabelaPrikazPanel, BorderLayout.NORTH);
		jTabelaPrikazPanel.add(jBtnPrikaziSve);
		jTabelaPrikazPanel.add(jBtnPrikaziAktivne);
		jTabelaPrikazPanel.add(jBrnPrikaziObrisane);
		
		/** Kreiranje jednostavne tabele ***/
		jTable1 = new JTable(data, columnNames);
		
		jScrol1 = new JScrollPane(jTable1);
		jCentralniPanel.add(jScrol1, BorderLayout.CENTER);
		
		//ZADNJI PANEL
		jPanelZanji = new JPanel();
		jPanelZanji.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Opcije rada sa tabelom", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		getContentPane().add(jPanelZanji, BorderLayout.SOUTH);
		
		jBtnAddNew = new JButton("Dodaj Novog");
		jBtnEditExisting = new JButton("Izmeni selektovanog");
		jBtnRemoveExisting = new JButton("Obri\u0161i selektovanog");
		jBtnViewExisting = new JButton("Pregled selektovanog");
		jBtnGetSelected = new JButton("Preuzmi selektovanog");
		
		jPanelZanji.add(jBtnAddNew);
		jPanelZanji.add(jBtnEditExisting);
		jPanelZanji.add(jBtnRemoveExisting);
		jPanelZanji.add(jBtnViewExisting);
		jPanelZanji.add(jBtnGetSelected);
	}

	public void setData(ArrayList<Racun> listR) {
		this.data = new Object [listR.size()] [columnNames.length];
		for (int i = 0; i < listR.size(); i++) {
			Racun r = listR.get(i);
			data[i][0] = r.getIndetifikator();
			data[i][1] = r.getNazivRacuna();
			data[i][2] = r.isAktivan();
			data[i][3] = r.getStanjeRacuna();
		}
	}
}

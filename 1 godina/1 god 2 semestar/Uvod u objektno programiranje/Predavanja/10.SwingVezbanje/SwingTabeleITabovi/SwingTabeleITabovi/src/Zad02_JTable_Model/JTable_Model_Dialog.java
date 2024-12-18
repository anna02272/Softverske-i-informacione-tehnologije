package Zad02_JTable_Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class JTable_Model_Dialog extends JDialog {

	public Racun selektovan;
	
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
	RacuniModelTabele modelTabele1;
	
	//ZADNJI PANEL
	JPanel jPanelZanji;
	JButton jBtnAddNew;
	JButton jBtnEditExisting;
	JButton jBtnRemoveExisting;
	JButton jBtnViewExisting;
	
	public JTable_Model_Dialog() {
		setTitle("JTable Model primer");
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
		jBtnPrikaziSve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modelTabele1.setNewList(Zad02.vratiListuRacuna());
			}
		});
		
		jBtnPrikaziAktivne = new JButton("Prikazi Aktivne");
		jBtnPrikaziAktivne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Racun> temp = new ArrayList<Racun>();
				temp.addAll(Zad02.vratiListuRacuna());
				for (int i = temp.size()-1; i >= 0; i--) {
					Racun el = temp.get(i);
					if(el.isStatusZapisa()==false)
						temp.remove(i);
				}
				modelTabele1.setNewList(temp);
			}
		});
		jBrnPrikaziObrisane = new JButton("Prikazi Obrisane");
		jBrnPrikaziObrisane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Racun> temp = new ArrayList<Racun>();
				temp.addAll(Zad02.vratiListuRacuna());
				for (int i = temp.size()-1; i >= 0; i--) {
					Racun el = temp.get(i);
					if(el.isStatusZapisa()==true)
						temp.remove(i);
				}
				modelTabele1.setNewList(temp);
			}
		});
		
		JPanel 	jTabelaPrikazPanel = new JPanel();
		jCentralniPanel.add(jTabelaPrikazPanel, BorderLayout.NORTH);
		jTabelaPrikazPanel.add(jBtnPrikaziSve);
		jTabelaPrikazPanel.add(jBtnPrikaziAktivne);
		jTabelaPrikazPanel.add(jBrnPrikaziObrisane);
		
		modelTabele1= new RacuniModelTabele(Zad02.vratiListuRacuna());
		jTable1 = new JTable(modelTabele1);
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel lsm = jTable1.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && (e.getSource() instanceof ListSelectionModel)) {  	
					int row = jTable1.getSelectedRow();
		        	if (row == -1) // nista nije selektovano 
		        		return;
		        	
		        	String identifikatorRacuna = (String) jTable1.getValueAt(row, 0);
		        	for (Racun temp : modelTabele1.getListaElemenata()) {
						if(temp.getIndetifikator().equalsIgnoreCase(identifikatorRacuna)){
							postaviSelektovanRacun(temp);
							break;
						}	
					}
		        }	
			}
		});
		
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
		
		jPanelZanji.add(jBtnAddNew);
		jPanelZanji.add(jBtnEditExisting);
		jPanelZanji.add(jBtnRemoveExisting);
		jPanelZanji.add(jBtnViewExisting);
	}
	
	public void postaviSelektovanRacun(Racun ra){
		selektovan = ra;
		jLblSelektovan.setText("Selektovan racun je:"+ra.getIndetifikator() + " naziv: " + ra.getNazivRacuna() +"a stanje je:"+ra.getStanjeRacuna());
	}
}

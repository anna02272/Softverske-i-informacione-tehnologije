package gui.formeZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import gui.formeZaDodavanjeIIzmenu.ProdavciForma;
import main.ProdavnicaMain;
import osobe.Prodavac;
import prodavnica.Prodavnica;

public class ProdavciProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable prodavciTabela;
	
	private Prodavnica prodavnica;
	
	public ProdavciProzor(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Prodavci");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlja = new String[] {"Ime", "Prezime", "Pol", "Korisnicko ime", "Sifra"};
		Object[][] sadrzaj = new Object[prodavnica.sviNeobrisaniZaposleni().size()][zaglavlja.length];
		
		for(int i=0; i<prodavnica.sviNeobrisaniZaposleni().size(); i++) {
			Prodavac prodavac = prodavnica.sviNeobrisaniZaposleni().get(i);
			sadrzaj[i][0] = prodavac.getIme();
			sadrzaj[i][1] = prodavac.getPrezime();
			sadrzaj[i][2] = prodavac.getPol();
			sadrzaj[i][3] = prodavac.getKorisnickoIme();
			sadrzaj[i][4] = prodavac.getLozinka();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		prodavciTabela = new JTable(tableModel);
		
		prodavciTabela.setRowSelectionAllowed(true);
		prodavciTabela.setColumnSelectionAllowed(false);
		prodavciTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		prodavciTabela.setDefaultEditor(Object.class, null);
		prodavciTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(prodavciTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = prodavciTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 3).toString();
					Prodavac prodavac = prodavnica.nadjiProdavca(korisnickoIme);
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete prodavca?", 
							korisnickoIme + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						prodavac.setObrisan(true);
						tableModel.removeRow(red);
						prodavnica.snimiZaposlene(ProdavnicaMain.PRODAVCI_FAJL);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProdavciForma pf = new ProdavciForma(prodavnica, null);
				pf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = prodavciTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = tableModel.getValueAt(red, 3).toString();
					Prodavac prodavac = prodavnica.nadjiProdavca(korisnickoIme);
					if(prodavac == null) {
						JOptionPane.showMessageDialog(null, "Greska prilikom pronalazenja prodavca sa tim korisnickim imenom", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						ProdavciForma pf = new ProdavciForma(prodavnica, prodavac);
						pf.setVisible(true);
					}
				}
			}
		});
	}
}

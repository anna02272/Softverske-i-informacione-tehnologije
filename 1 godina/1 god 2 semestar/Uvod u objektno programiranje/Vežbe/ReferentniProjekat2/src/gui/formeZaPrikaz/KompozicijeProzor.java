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

import artikli.Disk;
import artikli.Kompozicija;
import gui.formeZaDodavanjeIIzmenu.KompozicijeForma;
import main.ProdavnicaMain;
import prodavnica.Prodavnica;

public class KompozicijeProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable kompozicijeTabela;
	
	private Prodavnica prodavnica;
	
	public KompozicijeProzor(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Kompozicije");
		setSize(300, 300);
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
		
		String[] zaglavlja = new String[] {"Naziv", "Trajanje", "Disk"};
		Object[][] sadrzaj = new Object[prodavnica.sveNeobrisaneKompozicije().size()][zaglavlja.length];
		
		for(int i=0; i<prodavnica.sveNeobrisaneKompozicije().size(); i++) {
			Kompozicija kompozicija = prodavnica.sveNeobrisaneKompozicije().get(i);
			Disk disk = prodavnica.pronadjiDisk(kompozicija);
			sadrzaj[i][0] = kompozicija.getNaziv();
			sadrzaj[i][1] = kompozicija.getTrajanje();
			sadrzaj[i][2] = disk == null ? "--" : disk.getNaziv();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		kompozicijeTabela = new JTable(tableModel);
		
		kompozicijeTabela.setRowSelectionAllowed(true);
		kompozicijeTabela.setColumnSelectionAllowed(false);
		kompozicijeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		kompozicijeTabela.setDefaultEditor(Object.class, null);
		kompozicijeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(kompozicijeTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = kompozicijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String naziv = tableModel.getValueAt(red, 0).toString();
					Kompozicija k = prodavnica.pronadjiKompoziciju(naziv);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete kompoziciju?", 
							naziv + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						k.setObrisana(true);
						tableModel.removeRow(red);
						prodavnica.snimiKompozicije(ProdavnicaMain.KOMPOZICIJE_FAJL);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KompozicijeForma kf = new KompozicijeForma(prodavnica, null);
				kf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = kompozicijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String naziv = tableModel.getValueAt(red, 0).toString();
					Kompozicija k = prodavnica.pronadjiKompoziciju(naziv);
					KompozicijeForma kf = new KompozicijeForma(prodavnica, k);
					kf.setVisible(true);
				}
			}
		});
	}
}

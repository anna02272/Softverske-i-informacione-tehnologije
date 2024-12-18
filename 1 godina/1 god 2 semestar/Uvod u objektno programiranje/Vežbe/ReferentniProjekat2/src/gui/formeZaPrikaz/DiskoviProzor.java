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
import gui.formeZaDodavanjeIIzmenu.DiskoviForma;
import main.ProdavnicaMain;
import prodavnica.Prodavnica;

public class DiskoviProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable diskoviTabela;
	
	private Prodavnica prodavnica;
	
	public DiskoviProzor(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Diskovi");
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
		
		String[] zaglavlja = new String[] {"ID", "Naziv", "Cena", "Izvodjac", "Zanr"};
		Object[][] sadrzaj = new Object[prodavnica.sviNeobrisaniDiskovi().size()][zaglavlja.length];
		
		for(int i=0; i<prodavnica.sviNeobrisaniDiskovi().size(); i++) {
			Disk disk = prodavnica.sviNeobrisaniDiskovi().get(i);
			sadrzaj[i][0] = disk.getIdentifikacioniKod();
			sadrzaj[i][1] = disk.getNaziv();
			sadrzaj[i][2] = disk.getCena();
			sadrzaj[i][3] = disk.getIzvodjac();
			sadrzaj[i][4] = disk.getZanr();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		diskoviTabela = new JTable(tableModel);
		
		diskoviTabela.setRowSelectionAllowed(true);
		diskoviTabela.setColumnSelectionAllowed(false);
		diskoviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		diskoviTabela.setDefaultEditor(Object.class, null);
		diskoviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(diskoviTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = diskoviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String diskID = tableModel.getValueAt(red, 0).toString();
					Disk disk = prodavnica.pronadjiDisk(diskID);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete disk?", 
							diskID + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						disk.setObrisan(true);
						tableModel.removeRow(red);
						prodavnica.snimiDiskove(ProdavnicaMain.DISKOVI_FAJL);
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DiskoviForma df = new DiskoviForma(prodavnica, null);
				df.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = diskoviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String diskID = tableModel.getValueAt(red, 0).toString();
					Disk disk = prodavnica.pronadjiDisk(diskID);
					DiskoviForma df = new DiskoviForma(prodavnica, disk);
					df.setVisible(true);
				}
			}
		});
	}
}

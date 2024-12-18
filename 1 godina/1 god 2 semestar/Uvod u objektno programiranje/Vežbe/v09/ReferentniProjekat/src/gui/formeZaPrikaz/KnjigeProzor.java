package gui.formeZaPrikaz;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import artikli.Knjiga;
import prodavnica.Prodavnica;

public class KnjigeProzor extends JFrame {

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable knjigeTabela;
	
	private Prodavnica prodavnica;
	
	public KnjigeProzor(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Knjige");
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
		
		String[] zaglavlja = new String[] {"ID", "Naziv", "Cena", "Autor", "Broj strana", "Korice"};
		Object[][] sadrzaj = new Object[prodavnica.sveNeobrisaneKnjige().size()][zaglavlja.length];
		
		for(int i=0; i<prodavnica.sveNeobrisaneKnjige().size(); i++) {
			Knjiga knjiga = prodavnica.sveNeobrisaneKnjige().get(i);
			sadrzaj[i][0] = knjiga.getIdentifikacioniKod();
			sadrzaj[i][1] = knjiga.getNaziv();
			sadrzaj[i][2] = knjiga.getCena();
			sadrzaj[i][3] = knjiga.getAutor();
			sadrzaj[i][4] = knjiga.getBrojStrana();
			sadrzaj[i][5] = knjiga.isTvrdeKorice() ? "Tvrde" : "Meke";
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		knjigeTabela = new JTable(tableModel);
		
		knjigeTabela.setRowSelectionAllowed(true);
		knjigeTabela.setColumnSelectionAllowed(false);
		knjigeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		knjigeTabela.setDefaultEditor(Object.class, null);
		knjigeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(knjigeTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	
	private void initActions() {}
}

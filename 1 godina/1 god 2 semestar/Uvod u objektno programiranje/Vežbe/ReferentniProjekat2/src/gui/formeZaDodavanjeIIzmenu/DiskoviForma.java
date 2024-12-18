package gui.formeZaDodavanjeIIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import artikli.Disk;
import artikli.Kompozicija;
import main.ProdavnicaMain;
import net.miginfocom.swing.MigLayout;
import prodavnica.Prodavnica;

public class DiskoviForma extends JFrame {

	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblCena = new  JLabel("Cena");
	private JTextField txtCena = new JTextField(20);
	private JLabel lblNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(20);
	private JLabel lblIzvodjac = new JLabel("Izvodjac");
	private JTextField txtIzvodjac = new JTextField(20);
	private JLabel lblZanr = new JLabel("Zanr");
	private JTextField txtZanr = new JTextField(20);
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Disk disk;
	private Prodavnica prodavnica;
	
	public DiskoviForma(Prodavnica prodavnica, Disk disk) {
		this.prodavnica = prodavnica;
		this.disk = disk;
		if(disk == null) {
			setTitle("Dodavanje diska");
		}else {
			setTitle("Izmena podataka - " + disk.getIdentifikacioniKod());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][]20[]");
		setLayout(layout);
		
		if(disk != null) {
			// Ne zelimo da moze da se promeni ID diska, pa je polje disable-ovano:
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblCena);
		add(txtCena);
		add(lblNaziv);
		add(txtNaziv);
		add(lblIzvodjac);
		add(txtIzvodjac);
		add(lblZanr);
		add(txtZanr);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String id = txtID.getText().trim();
					double cena = Double.parseDouble(txtCena.getText().trim());
					String naziv = txtNaziv.getText().trim();
					String izvodjac = txtIzvodjac.getText().trim();
					String zanr = txtZanr.getText().trim();
					
					if(disk == null) { // DODAVANJE:
						Disk novi = new Disk(id, cena, naziv, false, izvodjac, zanr, new ArrayList<Kompozicija>());
						prodavnica.dodajDisk(novi);
					}else {
						disk.setCena(cena);
						disk.setNaziv(naziv);
						disk.setIzvodjac(izvodjac);
						disk.setZanr(zanr);
					}
					prodavnica.snimiDiskove(ProdavnicaMain.DISKOVI_FAJL);
					DiskoviForma.this.dispose();
					DiskoviForma.this.setVisible(false);
				}
			}
		});
	};
	
	private void popuniPolja() {
		txtID.setText(disk.getIdentifikacioniKod());
		txtCena.setText(String.valueOf(disk.getCena()));
		txtNaziv.setText(disk.getNaziv());
		txtIzvodjac.setText(disk.getIzvodjac());
		txtZanr.setText(disk.getZanr());
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		}else if(disk == null) {
			String id = txtID.getText().trim();
			Disk pronadjen = prodavnica.pronadjiDisk(id);
			if(pronadjen != null) {
				poruka += "- Disk sa unetim ID vec postoji\n";
				ok = false;
			}
		}
		
		try {
			Double.parseDouble(txtCena.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Cena mora biti broj\n";
			ok = false;
		}
		
		if(txtNaziv.getText().trim().equals("")) {
			poruka += "- Morate uneti naziv\n";
			ok = false;
		}
		
		if(txtIzvodjac.getText().trim().equals("")) {
			poruka += "- Morate uneti izvodjaca\n";
			ok = false;
		}
		
		if(txtZanr.getText().trim().equals("")) {
			poruka += "- Morate uneti zanr\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}

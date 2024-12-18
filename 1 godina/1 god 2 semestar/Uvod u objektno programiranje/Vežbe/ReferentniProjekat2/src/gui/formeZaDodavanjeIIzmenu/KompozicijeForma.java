package gui.formeZaDodavanjeIIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import artikli.Disk;
import artikli.Kompozicija;
import main.ProdavnicaMain;
import net.miginfocom.swing.MigLayout;
import prodavnica.Prodavnica;

public class KompozicijeForma extends JFrame {

	private JLabel lblNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(20);
	private JLabel lblTrajanje = new JLabel("Trajanje");
	private JTextField txtTrajanje = new JTextField(20);
	private JLabel lblDisk = new JLabel("Disk");
	private JComboBox<String> cbDiskovi = new JComboBox<String>();
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Prodavnica prodavnica;
	private Kompozicija kompozicija;
	
	public KompozicijeForma(Prodavnica prodavnica, Kompozicija kompozicija) {
		this.prodavnica = prodavnica;
		this.kompozicija = kompozicija;
		if(kompozicija == null) {
			setTitle("Dodavanje kompozicije");
		}else {
			setTitle("Izmena podataka - " + kompozicija.getNaziv());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][]20[]");
		setLayout(layout);
		
		for(Disk disk : prodavnica.sviNeobrisaniDiskovi()) {
			cbDiskovi.addItem(disk.getIdentifikacioniKod());
		}
		
		if(kompozicija != null) {
			popuniPolja();
		}
		
		add(lblNaziv);
		add(txtNaziv);
		add(lblTrajanje);
		add(txtTrajanje);
		add(lblDisk);
		add(cbDiskovi);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String naziv = txtNaziv.getText().trim();
					int trajanje = Integer.parseInt(txtTrajanje.getText().trim());
					String diskID = cbDiskovi.getSelectedItem().toString();
					Disk disk = prodavnica.pronadjiDisk(diskID);
					
					if(kompozicija == null) {
						Kompozicija nova = new Kompozicija(naziv, trajanje, false);
						if(disk != null) {
							disk.getKompozicije().add(nova);
						}
					}else {
						kompozicija.setNaziv(naziv);
						kompozicija.setTrajanje(trajanje);
						Disk stariDisk = prodavnica.pronadjiDisk(kompozicija);
						if(stariDisk != null) {
							stariDisk.getKompozicije().remove(kompozicija);
						}
						if(disk != null) {
							disk.getKompozicije().add(kompozicija);
						}
					}
					prodavnica.snimiKompozicije(ProdavnicaMain.KOMPOZICIJE_FAJL);
					KompozicijeForma.this.dispose();
					KompozicijeForma.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
		txtNaziv.setText(kompozicija.getNaziv());
		txtTrajanje.setText(String.valueOf(kompozicija.getTrajanje()));
		Disk disk = prodavnica.pronadjiDisk(kompozicija);
		cbDiskovi.setSelectedItem(disk.getIdentifikacioniKod());
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtNaziv.getText().trim().equals("")) {
			poruka += "- Morate uneti naziv\n";
			ok = false;
		}else  if(kompozicija == null) {
			String naziv = txtNaziv.getText().trim();
			Kompozicija pronadjena = prodavnica.pronadjiKompoziciju(naziv);
			if(pronadjena != null) {
				poruka += "- Kompozicija sa tim nazivom vec postoji\n";
				ok = false;
			}
		}
		
		try {
			Integer.parseInt(txtTrajanje.getText().trim());
		}catch(NumberFormatException e) {
			poruka += "- Trajanje mora biti broj\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}

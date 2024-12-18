package gui.formeZaDodavanjeIIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.ProdavnicaMain;
import net.miginfocom.swing.MigLayout;
import osobe.Pol;
import osobe.Prodavac;
import prodavnica.Prodavnica;

public class ProdavciForma extends JFrame {

	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblSifra = new JLabel("Sifra");
	private JPasswordField pfSifra = new JPasswordField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JButton btnOk = new JButton("OK");
	private JButton btnCanel = new JButton("Cancel");
	
	private Prodavnica prodavnica;
	private Prodavac prodavac;
	
	public ProdavciForma(Prodavnica prodavnica, Prodavac prodavac) {
		this.prodavnica = prodavnica;
		this.prodavac = prodavac;
		if(prodavac == null) {
			setTitle("Dodavanje prodavca");
		}else {
			setTitle("Izmena podataka - " + prodavac.getKorisnickoIme());
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
		
		if(prodavac != null) {
			popuniPolja();
		}
		
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(lblPol);
		add(cbPol);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCanel);
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String sifra = new String(pfSifra.getPassword()).trim();
					Pol pol = (Pol)cbPol.getSelectedItem();
					
					if(prodavac == null) { // DODAVANJE:
						Prodavac novi = new Prodavac(ime, prezime, pol, korisnickoIme, sifra, false);
						prodavnica.dodajProdavca(novi);
					}else { // IZMENA:
						prodavac.setIme(ime);
						prodavac.setPrezime(prezime);
						prodavac.setKorisnickoIme(korisnickoIme);
						prodavac.setLozinka(sifra);
						prodavac.setPol(pol);
					}
					prodavnica.snimiZaposlene(ProdavnicaMain.PRODAVCI_FAJL);
					ProdavciForma.this.dispose();
					ProdavciForma.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
		txtIme.setText(prodavac.getIme());
		txtPrezime.setText(prodavac.getPrezime());
		txtKorisnickoIme.setText(prodavac.getKorisnickoIme());
		pfSifra.setText(prodavac.getLozinka());
		cbPol.setSelectedItem(prodavac.getPol());
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
			ok = false;
		}
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}else if(prodavac == null){
			String korisnickoIme = txtKorisnickoIme.getText().trim();
			Prodavac pronadjeni = prodavnica.nadjiProdavca(korisnickoIme);
			if(pronadjeni != null) {
				poruka += "- Prodavac sa tim korisnickim imenom vec postoji\n";
				ok = false;
			}
		}
		
		String sifra = new String(pfSifra.getPassword()).trim();
		if(sifra.equals("")) {
			poruka += "- Unesite sifru\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
}

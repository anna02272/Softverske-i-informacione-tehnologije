package gui.formeZaDodavanjeIIzmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import artikli.Knjiga;
import main.ProdavnicaMain;
import net.miginfocom.swing.MigLayout;
import prodavnica.Prodavnica;

public class KnjigeForma extends JFrame {

	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblCena = new  JLabel("Cena");
	private JTextField txtCena = new JTextField(20);
	private JLabel lblNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(20);
	private JLabel lblAutor = new JLabel("Autor");
	private JTextField txtAutor = new JTextField(20);
	private JLabel lblBrojStrana = new JLabel("Broj strana");
	private JTextField txtBrojStrana = new JTextField(20);
	private JLabel lblTvrdeKorice = new JLabel("Tvrde korice");
	// Za bool atribute je najbolje koristiti checkbox komponentu:
	private JCheckBox cbTvrdeKorice = new JCheckBox();
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Prodavnica prodavnica;
	private Knjiga knjiga;
	
	public KnjigeForma(Prodavnica prodavnica, Knjiga knjiga) {
		this.prodavnica = prodavnica;
		this.knjiga = knjiga;
		if(knjiga == null) {
			setTitle("Dodavanje knjige");
		}else {
			setTitle("Izmena podataka - " + knjiga.getIdentifikacioniKod());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	public void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][]20[]");
		setLayout(layout);
		
		if(knjiga != null) {
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblCena);
		add(txtCena);
		add(lblNaziv);
		add(txtNaziv);
		add(lblAutor);
		add(txtAutor);
		add(lblBrojStrana);
		add(txtBrojStrana);
		add(lblTvrdeKorice);
		add(cbTvrdeKorice);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	public void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String id = txtID.getText().trim();
					double cena = Double.parseDouble(txtCena.getText().trim());
					String naziv = txtNaziv.getText().trim();
					String autor = txtAutor.getText().trim();
					int brojStrana = Integer.parseInt(txtBrojStrana.getText().trim());
					boolean tvrdeKorice = cbTvrdeKorice.isSelected();
					
					if(knjiga == null) { // DODAVANJE:
						Knjiga nova = new Knjiga(id, cena, naziv, false, autor, brojStrana, tvrdeKorice);
						prodavnica.dodajKnjigu(nova);
					}else {
						knjiga.setCena(cena);
						knjiga.setNaziv(naziv);
						knjiga.setAutor(autor);
						knjiga.setBrojStrana(brojStrana);
						knjiga.setTvrdeKorice(tvrdeKorice);
					}
					prodavnica.snimiKnjige(ProdavnicaMain.KNJIGE_FAJL);
					KnjigeForma.this.dispose();
					KnjigeForma.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
		txtID.setText(knjiga.getIdentifikacioniKod());
		txtCena.setText(String.valueOf(knjiga.getCena()));
		txtNaziv.setText(knjiga.getNaziv());
		txtAutor.setText(knjiga.getAutor());
		txtBrojStrana.setText(String.valueOf(knjiga.getBrojStrana()));
		// Za checkbox treba da postavimo da li je chekiran ili nije:
		cbTvrdeKorice.setSelected(knjiga.isTvrdeKorice());
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		}else if(knjiga == null) {
			String id = txtID.getText().trim();
			Knjiga pronadjena = prodavnica.pronadjiKnjigu(id);
			if(pronadjena != null) {
				poruka += "- Knjiga sa unetim ID vec postoji\n";
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
		
		if(txtAutor.getText().trim().equals("")) {
			poruka += "- Morate uneti autora\n";
			ok = false;
		}
		
		try {
			Integer.parseInt(txtBrojStrana.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Broj strana mora biti broj\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}

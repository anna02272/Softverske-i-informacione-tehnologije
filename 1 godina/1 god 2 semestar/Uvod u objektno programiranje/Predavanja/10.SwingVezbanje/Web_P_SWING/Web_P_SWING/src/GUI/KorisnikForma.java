package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.OsobaDAO;
import model.osobe.Admin;
import model.osobe.Korisnik;
import model.osobe.Kupac;
import model.osobe.Osoba;
import net.miginfocom.swing.MigLayout;
import util.WindowUtils;

public class KorisnikForma extends JFrame {

	private JLabel lPozdrav;
	private JLabel lKorIm;
	private JTextField tKorIme;
	private JLabel lLoz;
	private JPasswordField tLoz;
	private JButton bOK;
	private JButton bCancel;
	private JLabel lTipK;
	private JComboBox<String> cTipK;
	private JLabel lOsoba;
	private JComboBox<String> cOsoba;
	private JLabel lEmailIliStanje;
	private JTextField tEmailIliStanje;
	private JFrame thisFrame = this;
	private boolean modeAdd = true;

	public KorisnikForma(KorisniciTableModel ktm, Korisnik k, int row) {
		if(k!=null)
			modeAdd = false;
		setTitle("Korisnik");
		setSize(300, 300);
		WindowUtils.centerOnScreen(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initGUI(ktm, k, row);
		pack();
	}

	private void initGUI(KorisniciTableModel ktm, Korisnik kor, int row) {
		setLayout(new MigLayout("wrap 2", "", ""));
		lPozdrav = new JLabel("Korisnik:");
		add(lPozdrav, "span 2, align center");
		lKorIm = new JLabel("Korisinicko ime:");
		add(lKorIm, "align right");
		tKorIme = new JTextField(15);
		tKorIme.setEditable(modeAdd);
		add(tKorIme);
		lLoz = new JLabel("Lozinka:");
		add(lLoz, "align right");
		tLoz = new JPasswordField(15);
		add(tLoz);
		cTipK = new JComboBox<>();
		cTipK.addItem("Admin");
		cTipK.addItem("Kupac");
		lTipK = new JLabel("Tip korisnika:");
		add(lTipK, "align right");
		add(cTipK);
		lOsoba = new JLabel("Osoba:");
		add(lOsoba, "align right");
		cOsoba = new JComboBox<>(getOsobe());
		add(cOsoba);
		lEmailIliStanje = new JLabel("Email:");
		add(lEmailIliStanje, "align right");
		tEmailIliStanje = new JTextField(20);
		add(tEmailIliStanje);
		bOK = new JButton("OK");
		add(bOK, "span, split 2, sizegroup btn, align center");
		bCancel = new JButton("Cancel");
		add(bCancel, "sizegroup btn, align center");

		if(kor!=null) {
			setKorisnikToFilelds(kor);
		}
		
		cTipK.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String s = (String) cTipK.getSelectedItem();
				if("Admin".equals(s))
					lEmailIliStanje.setText("Email:");
				else
					lEmailIliStanje.setText("Novac:");
				
			}
		});
		
		bOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Kupac k = fromFieldsKupac();
				if(kor!=null) {
					ktm.update(k, row);
				}else{
					ktm.add(k);
				}
				
				thisFrame.dispose();
			}
		});
	}

	private String[] getOsobe() {
		OsobaDAO oDAO = new OsobaDAO();
		HashMap<String, Osoba> map = oDAO.loadAllOsobaFromFile();
		
		String[] osobe = new String[map.values().size()];
		int pos = 0;
		for (Osoba o : map.values()) {
			osobe[pos] = o.getJmbg();
			pos++;
		}
		return osobe;
	}

	private Kupac fromFieldsKupac() {
		OsobaDAO oDAO = new OsobaDAO();
		HashMap<String, Osoba> map = oDAO.loadAllOsobaFromFile();
		
		return new Kupac(this.tKorIme.getText(), new String(this.tLoz.getPassword()),
				map.get(this.cOsoba.getSelectedItem()),
				Double.parseDouble(this.tEmailIliStanje.getText()));
	}

	private void setKorisnikToFilelds(Korisnik k) {
		this.tKorIme.setText(k.getKorisnickoIme());
		this.tLoz.setText(k.getLozinka());

		if (k instanceof Kupac) {
			Kupac kup = (Kupac) k;
			this.cOsoba.setSelectedItem(kup.getOsoba().getJmbg());
			this.cTipK.setSelectedIndex(1);
			this.tEmailIliStanje.setText(Double.toString(kup.getStanjeNaRacunu()));
		} else {
			Admin a = (Admin) k;
			this.cOsoba.setSelectedItem(a.getOsoba().getJmbg());
			this.cTipK.setSelectedIndex(0);
			this.tEmailIliStanje.setText(a.getEmail());
		}
	}
}

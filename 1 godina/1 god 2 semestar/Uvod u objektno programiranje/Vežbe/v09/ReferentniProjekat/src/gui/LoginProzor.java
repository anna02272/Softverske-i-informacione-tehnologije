package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import osobe.Prodavac;
import prodavnica.Prodavnica;

public class LoginProzor extends JFrame {

	private JLabel lblGreeting = new JLabel("Dobrodošli. Molimo da se prijavite.");
	private JLabel lblUsername = new JLabel("Korisničko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblPassword = new JLabel("Šifra");
	private JPasswordField pfPassword = new JPasswordField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Prodavnica prodavnica;
	
	public LoginProzor(Prodavnica prodavnica) {
		this.prodavnica = prodavnica;
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		add(lblGreeting, "span 2");
		add(lblUsername);
		add(txtKorisnickoIme);
		add(lblPassword);
		add(pfPassword);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
		
		txtKorisnickoIme.setText("petarp");
		pfPassword.setText("12345");
		getRootPane().setDefaultButton(btnOk);
	}
	
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.dispose();
				LoginProzor.this.setVisible(false);
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnikoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfPassword.getPassword()).trim();
				
				if(korisnikoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za prijavu.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					Prodavac prijavljeni = prodavnica.login(korisnikoIme, sifra);
					if(prijavljeni == null) {
						JOptionPane.showMessageDialog(null, "Pogrešni login podaci.", "Greška", JOptionPane.WARNING_MESSAGE);
					}else {
						LoginProzor.this.dispose();
						LoginProzor.this.setVisible(false);
						GlavniProzor gp = new GlavniProzor(prodavnica, prijavljeni);
						gp.setVisible(true);
					}
				}
			}
		});
		
	}
}

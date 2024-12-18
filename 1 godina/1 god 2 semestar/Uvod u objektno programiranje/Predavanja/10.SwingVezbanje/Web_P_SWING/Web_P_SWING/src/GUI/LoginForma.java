package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.KorisnikDAO;
import model.osobe.Korisnik;
import net.miginfocom.swing.MigLayout;
import util.WindowUtils;

public class LoginForma extends JFrame {
	private JLabel lPozdrav;
	private JLabel lKorIme;
	private JTextField tKorIme;
	private JLabel lLozika;
	private JPasswordField tLoz;
	private JLabel lTipKorisnika;
	private JComboBox<String> cTipKorisnika;
	private JButton bOK;
	private JButton bCancel;
	private JFrame thisFrame;
	
	public LoginForma() {
		setTitle("Prodavnica");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new MigLayout("wrap 2"));
		WindowUtils.centerOnScreen(this);
		initGui();
		pack();
	}
	
	private void initGui() {
		lPozdrav = new JLabel("Ulogujte se:");
		add(lPozdrav, "span 2, align center");
		lKorIme = new JLabel("Kor ime:");
		add(lKorIme, "align right");
		tKorIme = new JTextField(15);
		add(tKorIme);
		lLozika = new JLabel("Lozinka:");
		add(lLozika, "align right");
		tLoz = new JPasswordField(15);
		add(tLoz);
		cTipKorisnika = new JComboBox<>();
		cTipKorisnika.addItem("Admin");
		cTipKorisnika.addItem("Kupac");
		lTipKorisnika = new JLabel("Tip korisnika:");
		add(lTipKorisnika, "align right");
		add(cTipKorisnika);
		bOK = new JButton("OK");
		add(bOK, "span, split 2, sizegroup btn, align center");
		bCancel = new JButton("Cancel");
		add(bCancel, "span, split 2, sizegroup btn, align center");
		
		bOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ki = tKorIme.getText();
				String loz = new String(tLoz.getPassword());
				KorisnikDAO dao = new KorisnikDAO();
				Korisnik k = dao.loadKorisnikByKorisnickoIme(ki);
				
				if(k!=null) {
					System.out.println(k);
					try {
						if(k.getLozinka().equals(loz) && 
								(k.getClass().equals(Class.forName("model.osobe."+(String)cTipKorisnika.getSelectedItem())))) {
							MainFrame mf = new MainFrame();
							mf.setVisible(true);
						}else{
							JOptionPane.showConfirmDialog(thisFrame,"Greska2");
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					JOptionPane.showConfirmDialog(thisFrame,"Greska");
				}
			}
		});
	}
	
	public static void main(String args []) {
		LoginForma lf = new LoginForma();
		lf.setVisible(true);
	}
}

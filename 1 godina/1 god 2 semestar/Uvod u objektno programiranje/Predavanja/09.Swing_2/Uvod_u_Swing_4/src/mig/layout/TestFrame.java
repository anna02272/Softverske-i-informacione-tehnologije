package mig.layout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class TestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel settingsLabel = new JLabel("Dashboard Settings");
	JLabel nameLabel = new JLabel("Name:");
	JLabel zipLabel = new JLabel("Zip Code:");
	JLabel emailLabel = new JLabel("Email:");
	JLabel avatarLabel = new JLabel("Avatar Image:");
	JTextField fNameField = new JTextField(10);
	JTextField mNameField = new JTextField(1);
	JTextField lNameField = new JTextField(15);
	JTextField zipField = new JTextField(5);
	JTextField emailField = new JTextField(20);
	JTextField avatarField = new JTextField(30);
	JButton okBttn = new JButton("Ok");
	JButton cancelBttn = new JButton("Cancel");
	JButton helpBttn = new JButton("Help");

	public TestFrame() {
		setTitle("Mig Layout test");
		setSize(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
//		pack();
	}

	private void initGUI() {
		add(buildMiGDashboard());
	}

	private JPanel buildMiGDashboard() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		buildMiGForm(panel);
		return panel;
	}

	private void buildMiGForm(JPanel panel) {
		//span je merge cells (ako nema broja onda sve celije)
		panel.add(settingsLabel, "span, center, gapbottom 15");
		//align label poravnava labele po default sistem poravmanju koje je left svuda osim OSX
		panel.add(nameLabel, "align label");
		panel.add(fNameField);
		panel.add(mNameField);

		// wrap keyword starts a new row
		panel.add(lNameField, "wrap");

		// align label triggers platform-specific label alignment
		panel.add(zipLabel, "align label");
		panel.add(zipField, "wrap");
		panel.add(emailLabel, "align label");

		// span keyword lets emailField use the rest of the row
		panel.add(emailField, "span"); //iako moze da iskoristi ceo red velicina polja odredjena je i konstruktorom
		panel.add(avatarLabel, "align label");
		panel.add(avatarField, "span");

		// "tag ok" - tag identifies the type of button - zgodno za portabilnost, razliciti os postavljau OK, Cancel, Help na razlicite pozicije
		panel.add(okBttn, "span, split 3, sizegroup bttn, align center");

		// sizegroups set all members to the size of the biggest member
		panel.add(cancelBttn, "sizegroup bttn, align center"); //na osnovu tagova mig moze da odredit polozaj dugmica za odredjenu platformu
		panel.add(helpBttn, "sizegroup bttn, align center");
	}
}

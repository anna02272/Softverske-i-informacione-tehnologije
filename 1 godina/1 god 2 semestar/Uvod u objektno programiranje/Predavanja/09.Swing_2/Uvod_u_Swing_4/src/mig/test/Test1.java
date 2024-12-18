package mig.test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class Test1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Test1() {
		setTitle("Mig Layout test");
		setSize(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
//		pack();
	}

	public void initGUI() {
		// Layout, Column and Row constraints as arguments.
		MigLayout layout = new MigLayout("", "[right]20[grow]", "[]10[]");
		JPanel panel = new JPanel(layout);

		panel.add(new JLabel("Enter size:"),   "");
		panel.add(new JTextField(30),          "wrap");
		panel.add(new JLabel("Enter weight:"), "");
		panel.add(new JTextArea("Neki \nTekst", 5, 20),          "");
		getContentPane().add(panel);
	}
}

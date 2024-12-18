package dogadjaji;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JTextFieldTest extends JFrame {

	public JTextFieldTest() {
		setSize(400, 200);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(label);
		getContentPane().add(tf);

		tf.addKeyListener(new Reakcija());
	}

	/** Rukovalac dogadjajima definisan kao inner klasa */
	class Reakcija extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_A)
				label.setText("Pritisnuo taster a");
			else
				label.setText("Tekst");
		}
	}

	JTextField tf = new JTextField(30);
	JLabel label = new JLabel("Tekst");

	public static void main(String[] args) {
		JTextFieldTest mf = new JTextFieldTest();
		mf.setVisible(true);
	}
}

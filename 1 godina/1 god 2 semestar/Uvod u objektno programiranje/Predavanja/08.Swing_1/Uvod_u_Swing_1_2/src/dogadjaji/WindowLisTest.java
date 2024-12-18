package dogadjaji;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class WindowLisTest extends JFrame {

	public WindowLisTest() {
		setSize(300, 200);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				// ovako dobijamo WindowLisTest objekat iz anonimne klase
				int answer = JOptionPane.showConfirmDialog(WindowLisTest.this, "Da li zelite da prekinete sa radom?",
						"Kraj rada", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (answer == JOptionPane.YES_OPTION)
					System.exit(0);
			} // WindowClosing
				// Ovo su sve metode WindowListenera, dakle, sve se moraju
				// implementirati:

			public void windowOpened(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}
		}); // addWindowListener
	}

	public static void main(String[] args) {
		WindowLisTest mf = new WindowLisTest();
		mf.setVisible(true);
	}
}

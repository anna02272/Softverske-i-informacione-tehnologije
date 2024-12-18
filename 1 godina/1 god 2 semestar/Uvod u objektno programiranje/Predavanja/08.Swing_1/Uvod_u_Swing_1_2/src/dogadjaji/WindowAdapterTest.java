package dogadjaji;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class WindowAdapterTest extends JFrame {

	public WindowAdapterTest() {
		setSize(300, 200);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// ovako dobijamo WindowLisTest objekat iz anonimne klase
				int answer = JOptionPane.showConfirmDialog(WindowAdapterTest.this,
						"Da li zelite da prekinete sa radom?", "Kraj rada", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (answer == JOptionPane.YES_OPTION)
					System.exit(0);
			}

		}); // addWindowListener
	}

	public static void main(String[] args) {
		WindowAdapterTest mf = new WindowAdapterTest();
		mf.setVisible(true);
	}
}

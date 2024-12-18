package dijalozi;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class JDialogTest {

	public static void main(String [] args) {
		
		JFrame frame = new JFrame("Moj prozor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		JDialog jd = new JDialog(frame, "Moj Dijalog", true);
		jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jd.setLocation(200, 300);
		jd.setSize(300, 300);
		jd.setVisible(true);
	}
}

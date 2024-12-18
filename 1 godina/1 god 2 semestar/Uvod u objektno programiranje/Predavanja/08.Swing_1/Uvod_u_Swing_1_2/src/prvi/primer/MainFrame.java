package prvi.primer;


import java.awt.Frame;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		
		setSize(300, 200); // Dimenzije su inicijalno (0, 0)
		setTitle("My First GUI App");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(100, 50);
//		setBounds(200, 300, 100, 200);
//		setEnabled(false);
//		setUndecorated(true);
//		setExtendedState(JFrame.ICONIFIED);
	}
	
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true); // Prozor je inicijalno nevidljiv
	}
}

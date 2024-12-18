package prvi.primer;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame2 extends JFrame {
	
	public MainFrame2() {
		
		setSize(300, 200); // Dimenzije su inicijalno (0, 0)
		setTitle("My First GUI App");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new JButton("DUGME1"), BorderLayout.EAST);
		getContentPane().add(new JButton("DUGME2"), BorderLayout.SOUTH);
		add(new JButton("DUGME3"),BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		MainFrame2 mf = new MainFrame2();
		mf.setVisible(true); // Prozor je inicijalno nevidljiv
	}
}

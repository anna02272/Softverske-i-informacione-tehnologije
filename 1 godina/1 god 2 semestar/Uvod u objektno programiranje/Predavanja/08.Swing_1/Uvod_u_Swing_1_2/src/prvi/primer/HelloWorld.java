package prvi.primer;

import javax.swing.JFrame;

public class HelloWorld {
	
	public static void main(String [] args) {
		
		JFrame frame = new JFrame("Hello World!");
		frame.setSize(300, 300);
//		frame.setTitle("My First GUI App");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

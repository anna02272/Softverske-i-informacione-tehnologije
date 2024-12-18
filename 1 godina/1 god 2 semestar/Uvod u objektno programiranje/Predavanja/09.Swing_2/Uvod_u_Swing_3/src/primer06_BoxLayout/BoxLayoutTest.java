package primer06_BoxLayout;

import javax.swing.Box;import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BoxLayoutTest extends JFrame {

	public BoxLayoutTest() {
		setTitle("Box Layout Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 100);
		
		//Instead of using BoxLayout directly, many programs use the Box class. 
		//The Box class is a lightweight container that uses a BoxLayout. 
		//It also provides handy methods to help you use BoxLayout well. 
		//Adding components to multiple nested boxes is a powerful way to 
		//get the arrangement you want.
		Box b = Box.createHorizontalBox(); 
//		Box b = Box.createVerticalBox(); 
		JButton button1 = new JButton("Ok"); 
		JButton button2 = new JButton("Cancel"); 
		b.add(button1); 
//		b.add(Box.createGlue());
//		b.add(Box.createHorizontalStrut(30));
		b.add(button2);
		
		add(b);
	}	
	public static void main(String [] args) {
		BoxLayoutTest bt = new BoxLayoutTest();
		bt.setVisible(true);
	}
} 

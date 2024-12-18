package primer06_BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.color.ColorSpace;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class BoxLayoutTest2 extends JFrame {

	public BoxLayoutTest2() {
		setTitle("Box Layout Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//komponente se postavljau jedna iza druge
		BoxLayout manager = new BoxLayout(getContentPane(), BoxLayout.X_AXIS);			
		setLayout(manager); 
		
		JLabel label = new JLabel("Name:"); 
		label.setBorder(new LineBorder(new Color(255, 0, 0)));
		add(label); //dodavanje labele
		//Creates an invisible component that's always the specified size
//		Component c = Box.createRigidArea(new Dimension(300, 100));
//		add(c); 
//		Box.Filler f = new Box.Filler(new Dimension(50, 50), new Dimension(250, 250), new Dimension(500, 500));
//		add(f);
		JTextArea textArea = new JTextArea("Inicijalni tekst \nNeki Tekst", 10, 30); 
		add(textArea); //dodavanje linije za unos
		pack();

	}	
	public static void main(String [] args) {
		BoxLayoutTest2 bt = new BoxLayoutTest2();
		bt.setVisible(true);
	}
} 

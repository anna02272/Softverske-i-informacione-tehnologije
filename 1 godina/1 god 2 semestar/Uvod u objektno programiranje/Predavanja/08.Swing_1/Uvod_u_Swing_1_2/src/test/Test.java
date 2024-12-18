package test;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;

public class Test extends JFrame {
	
	public Test() {
		setSize(300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		String colors[] = {"Black", "Blue", "Green"};
		JList<Object> colorList = new JList<Object>(colors);
		colorList.setVisibleRowCount(2);
		getContentPane().add(colorList, BorderLayout.NORTH);
		JComboBox<String> colorBox = new JComboBox<>(colors);
		getContentPane().add(colorBox, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		Test mf = new Test();
		mf.setVisible(true);
	}
}
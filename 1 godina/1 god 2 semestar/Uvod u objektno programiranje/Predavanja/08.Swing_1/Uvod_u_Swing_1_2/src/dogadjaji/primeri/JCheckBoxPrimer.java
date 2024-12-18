package dogadjaji.primeri;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class JCheckBoxPrimer {
	
	public static void main(String [] args) {
		
		JFrame frame = new JFrame("Check box primer");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
		
		JCheckBox cb = new JCheckBox("Select");
		frame.getContentPane().add(cb, BorderLayout.CENTER);
		
		cb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					System.out.println("Selected!");
				}
			}
		});
		cb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					System.out.println("Selected2!");
					int w = frame.getSize().width;
					int h = frame.getSize().height;
					frame.resize(new Dimension(w-5, h-5));
				}
			}
		});
		
		frame.setVisible(true);
	}
}

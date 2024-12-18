package dogadjaji.primeri;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class JRadioButtonPrimer {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Radio button primer");
		frame.setSize(300, 300);
//		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JRadioButton rb1 = new JRadioButton("Prvo dugme");
		JRadioButton rb2 = new JRadioButton("Drugo dugme");
		ButtonGroup bgr = new ButtonGroup();
		bgr.add(rb1);
		bgr.add(rb2);
		frame.setLayout(new FlowLayout());
		frame.getContentPane().add(rb1);
		frame.getContentPane().add(rb2);

		ItemListener il = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				AbstractButton ab = (AbstractButton) e.getSource();
				System.out.println(ab.getText() + "\t" + (e.getStateChange()==1?"-->ukljucio":"iskljucio"));
			}
		};

		rb1.addItemListener(il);
		rb2.addItemListener(il);
		frame.setVisible(true);
	}
}

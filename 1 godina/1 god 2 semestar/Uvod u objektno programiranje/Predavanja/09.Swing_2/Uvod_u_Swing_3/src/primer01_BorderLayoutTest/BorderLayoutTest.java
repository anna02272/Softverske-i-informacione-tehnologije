package primer01_BorderLayoutTest;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutTest extends JFrame {

	private static final long serialVersionUID = -5364461553119050682L;

	public BorderLayoutTest() {
		setTitle("BorderLayout example");
		BorderLayout bl = new BorderLayout();

		// rastojanje izmedju komponenti
		bl.setHgap(5);
		bl.setVgap(15);

		setLayout(bl);

		JButton s = new JButton("Sever");
		s.setPreferredSize(new Dimension(100, 100));

		JButton j = new JButton("Jug");
		j.setPreferredSize(new Dimension(50, 50));
		
		JButton i = new JButton("Istok");
		i.setPreferredSize(new Dimension(100, 100));
		
		JButton z = new JButton("Zapad");
		z.setPreferredSize(new Dimension(50, 50));
		
		JButton c = new JButton("Centar");
		c.setPreferredSize(new Dimension(200, 250));
		/*
		 * BorderLayout postuje preferiranu visinu severne i juzne komponente 
		 * i sirinu istocne i zapadne. 
		 * Centralnu komponentu razvlaci u svim pravcima 
		 * pri cemu ce pokusati da ispostuje pref. velicine.
		 */

		getContentPane().add(s, BorderLayout.NORTH);
		getContentPane().add(j, BorderLayout.SOUTH);
		getContentPane().add(i, BorderLayout.EAST);
		getContentPane().add(z, BorderLayout.WEST);
		getContentPane().add(c, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		BorderLayoutTest b = new BorderLayoutTest();
		b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.pack();
//		b.setSize(500, 500);
//		b.setResizable(false);
		b.setVisible(true);
	}

}

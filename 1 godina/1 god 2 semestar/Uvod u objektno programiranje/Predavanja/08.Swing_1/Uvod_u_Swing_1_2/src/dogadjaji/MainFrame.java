package dogadjaji;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {

	public MainFrame() {
		setSize(300, 200);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(bOK, BorderLayout.NORTH);

		// Povezivanje sa izvorom događaja (dodavanje reakcije izbora dugmeta)
		bOK.addActionListener(new MyListener());

		// ili...
//		bOK.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
	}

	private JButton bOK = new JButton("OK");

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
	}
}

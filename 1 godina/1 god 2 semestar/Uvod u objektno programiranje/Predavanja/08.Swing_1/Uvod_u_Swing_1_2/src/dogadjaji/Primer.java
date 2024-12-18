package dogadjaji;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Primer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Primer() {
		setSize(300, 200);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		getContentPane().add(bOK, BorderLayout.NORTH);
		getContentPane().add(bCancel, BorderLayout.SOUTH);

		// dodajemo reakcije na dogadjaje dugmadima
		bOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int r = (int) Math.round(Math.random() * 256);
				int g = (int) Math.round(Math.random() * 256);
				int b = (int) Math.round(Math.random() * 256);
				
				bOK.setBackground(new Color(r, g, b));
				
//				if(ev.getSource() instanceof JButton)
//					((JButton) ev.getSource()).setBackground(new Color(r, g, b));
				
//				obradiKlikMisa(ev);
			} // bOK menja boju na svaki klik misa
		});
		
		
		
		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.out.println(ev.getSource().getClass());
				
				 System.exit(0);
				 
//				 JButton dugme = (JButton) ev.getSource();
//				 (dugme).setText(dugme.getText()+" Oktazi");
			} // bCancel prekida rad programa
		});
		
//		bCancel.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				obradiKlikMisa(ev);
//			} // bCancel prekida rad programa
//		});
	}

	private void obradiKlikMisa(ActionEvent ev) {
		int r = (int) Math.round(Math.random() * 256);
		int g = (int) Math.round(Math.random() * 256);
		int b = (int) Math.round(Math.random() * 256);
		if(ev.getSource() instanceof JButton)
			((JButton) ev.getSource()).setBackground(new Color(r, g, b));
	}
	
	private JButton bOK = new JButton("OK");
	private JButton bCancel = new JButton("Cancel");

	public static void main(String[] args) {
		Primer p = new Primer();
		p.setVisible(true);
	}
}

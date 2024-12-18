package dijalozi;

import javax.swing.JOptionPane;

public class Test {
	
	public static void main(String [] args) {
		
		JOptionPane.showConfirmDialog(null, "Poruka");
		int odgovor = JOptionPane.showConfirmDialog(null, "Poruka", "Naslov", JOptionPane.YES_NO_OPTION);
		System.out.println(odgovor);
		JOptionPane.showMessageDialog(null, "Test");
		JOptionPane.showMessageDialog(null, "Poruka", "Naslov", JOptionPane.ERROR_MESSAGE);
	}
}

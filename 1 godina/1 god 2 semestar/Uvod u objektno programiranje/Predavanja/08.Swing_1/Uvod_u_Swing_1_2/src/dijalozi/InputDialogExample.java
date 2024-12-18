package dijalozi;

import javax.swing.*;

class InputDialogExample {
	
	public static void main(String[] args) {
		
		String name = JOptionPane.showInputDialog(null, "What's yer name, pardner?");
		JOptionPane.showMessageDialog(null, "Yeehaw, " + name);
		String [] vrednosti = new String[] { "Pera", "Mika" };
		String izbor = (String) JOptionPane.showInputDialog(null, "naslov", "poruka", JOptionPane.INFORMATION_MESSAGE, null,
				vrednosti, 0);
		System.out.println(izbor);
	}
}

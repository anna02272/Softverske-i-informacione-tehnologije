package dijalozi;

import javax.swing.*;

class MessageDialogExample {
	
	public static void main(String[] args) {
		
		JOptionPane.showMessageDialog(null, "Second message");
		JOptionPane.showMessageDialog(null, "Third message", "Title of dialog", JOptionPane.WARNING_MESSAGE);
	}
}

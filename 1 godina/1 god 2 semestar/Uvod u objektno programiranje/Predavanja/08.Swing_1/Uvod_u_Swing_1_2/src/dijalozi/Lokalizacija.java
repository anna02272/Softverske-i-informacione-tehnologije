package dijalozi;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Lokalizacija {

	public static void main(String[] args) {

		// tekst na default dugmicima
		UIManager.put("OptionPane.yesButtonText", "Da");
		UIManager.put("OptionPane.noButtonText", "Ne");
		UIManager.put("OptionPane.cancelButtonText", "Otka\u017ei");
		UIManager.put("OptionPane.okButtonText", "OK");

		int answer1 = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Pitanje",
				JOptionPane.YES_NO_CANCEL_OPTION);

		int answer2 = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Pitanje",
				JOptionPane.OK_CANCEL_OPTION);
	}

}

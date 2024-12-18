package dogadjaji;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Implementacija osluškivača
public class MyListener implements ActionListener {
	// ActionPerformed je jedina metoda ActionListener interfejsa
	public void actionPerformed(ActionEvent ev) {
		System.exit(0); // Ovde se smešta kod koji se izvršava u slučaju izbora
						// dugmeta
	}
}

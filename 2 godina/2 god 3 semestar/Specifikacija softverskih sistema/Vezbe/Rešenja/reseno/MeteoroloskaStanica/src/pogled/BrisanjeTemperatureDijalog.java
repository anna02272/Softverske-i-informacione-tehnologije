package pogled;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import kontroler.KontrolerStanice;

public class BrisanjeTemperatureDijalog extends JDialog {

	private KontrolerStanice kontrolerStanice;

	public BrisanjeTemperatureDijalog(KontrolerStanice kontrolerStanice, int index) {

		int odgovor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete izabrano merenje?",
				"Upozorenje", JOptionPane.YES_NO_OPTION);

		if (odgovor == JOptionPane.YES_OPTION) {
			kontrolerStanice.obrisiMerenje(index);
			this.setVisible(false);
		}
	}

}

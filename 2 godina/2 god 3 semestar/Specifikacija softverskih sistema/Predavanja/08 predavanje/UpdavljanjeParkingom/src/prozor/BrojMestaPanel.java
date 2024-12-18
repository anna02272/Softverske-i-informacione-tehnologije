package prozor;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import automat.Konstante;
import automat.event.UpdateEvent;
import automat.event.UpdateListener;

/**
 * Panel za prikaz raspolozivih broja mesta na parkingu
 *
 */

public class BrojMestaPanel extends JPanel implements UpdateListener {	
	private JLabel prikazMesta;	
	
	public BrojMestaPanel() {		
		setLayout(new GridLayout(2, 0));
		
		JLabel natpis = new JLabel("Broj slobodnih mesta", SwingConstants.CENTER);	
		add(natpis);
		
		prikazMesta = new JLabel(String.valueOf(Konstante.MAX_BROJ_MESTA), SwingConstants.CENTER);			
	
		prikazMesta.setFont(new Font(prikazMesta.getFont().getName(), Font.PLAIN, 40));		
		add(prikazMesta);					
	}
	
	//reakcija na dogadjaj o promeni podataka u kontroleru parkinga:
	public void updatePerformed(UpdateEvent e) {
		prikazMesta.setText(String.valueOf(e.getBrojMesta()));
	}
}

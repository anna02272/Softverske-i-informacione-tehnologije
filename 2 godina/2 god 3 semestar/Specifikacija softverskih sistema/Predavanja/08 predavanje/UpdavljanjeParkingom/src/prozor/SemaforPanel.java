package prozor;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import automat.SvetloNaSemaforu;
import automat.event.UpdateEvent;
import automat.event.UpdateListener;

/**
 * Panel koji simulira svetla na semaforu
 *
 */
public class SemaforPanel extends JPanel implements UpdateListener {
	
	private JPanel crvenoSvetlo;
	private JPanel zelenoSvetlo;	
	
	public SemaforPanel() {
		setLayout(new GridLayout(2, 0));
		
		crvenoSvetlo = new JPanel();				
		zelenoSvetlo = new JPanel();		
		
		add(crvenoSvetlo);
		add(zelenoSvetlo);		
		
		zelenoSvetlo.setBackground(Color.green);		
		crvenoSvetlo.setBackground(Color.gray);		
	}
	
	//reakcija na dogadjaj o promeni podataka u kontroleru parkinga:
	public void updatePerformed(UpdateEvent e) {
		if (e.getAktivnoSvetlo() == SvetloNaSemaforu.Crveno) {
			crvenoSvetlo.setBackground(Color.red);
			zelenoSvetlo.setBackground(Color.gray);
		}
		else {
			crvenoSvetlo.setBackground(Color.gray);
			zelenoSvetlo.setBackground(Color.green);
		}
	}
}

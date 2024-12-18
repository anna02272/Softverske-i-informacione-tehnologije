/**
 * 
 */
package automat;

/**
 * Stanje koje je aktivno kada ima mesta na parkingu
 *
 */

public class ImaMesta extends Stanje {
	
	public ImaMesta(KontrolerParkinga aKontekst) {
		super(aKontekst);
	}	
	
	@Override
	public void entry() {		
		
	}

	@Override
	public void exit() {	

	}

	@Override
	public void usaoAutomobil() {	
		if (kontekst.getBrojSlobodnihMesta() > 1) {
			kontekst.azurirajBrojMesta(-1);			
		}
		else {
			//tranzicija
			kontekst.azurirajBrojMesta(-1);
			kontekst.promeniTekuceStanje(new SvePopunjeno(kontekst));
		}		
	}

	@Override
	public void izasaoAutomobil() {
		if (kontekst.getBrojSlobodnihMesta() < Konstante.MAX_BROJ_MESTA) {
			kontekst.azurirajBrojMesta(1);
		}				
	}		

}

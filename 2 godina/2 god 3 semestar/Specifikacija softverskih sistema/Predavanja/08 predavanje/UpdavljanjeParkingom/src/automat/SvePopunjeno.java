/**
 * 
 */
package automat;

/**
 * Stanje koje je aktivno kada nema mesta na parkingu
 *
 */

public class SvePopunjeno extends Stanje {
	
	public SvePopunjeno(KontrolerParkinga aKontekst) {
		super(aKontekst);
	}

	
	@Override
	public void entry() {		
		kontekst.upaliCrveno();
	}

	
	@Override
	public void exit() {		
		kontekst.upaliZeleno();
	}	
	
	public void usaoAutomobil() {		

	}
	
	@Override
	public void izasaoAutomobil() {		
		kontekst.azurirajBrojMesta(1);	;
		kontekst.promeniTekuceStanje(new ImaMesta(kontekst));
	}

}

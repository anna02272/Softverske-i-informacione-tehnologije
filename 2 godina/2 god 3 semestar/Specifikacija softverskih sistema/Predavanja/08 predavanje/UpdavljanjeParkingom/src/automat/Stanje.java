package automat;

/**
 * Stanje - apstraktni predak hijerarhije 
 *
 */

public abstract class Stanje {
	
	protected KontrolerParkinga kontekst;
	
	public abstract void entry();
	public abstract void exit();
	public abstract void usaoAutomobil();
	public abstract void izasaoAutomobil();
	
	public Stanje(KontrolerParkinga aKontekst) {
		kontekst = aKontekst;		
	}
}

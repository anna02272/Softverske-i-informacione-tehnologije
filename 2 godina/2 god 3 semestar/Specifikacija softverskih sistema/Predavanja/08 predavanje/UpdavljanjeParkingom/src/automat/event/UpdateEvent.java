package automat.event;

import java.util.EventObject;

import automat.SvetloNaSemaforu;

/**
 * Dogadjaj koji opisuje promene u podacima kontrolera.
 * Kontroler parkinga prosledjuje ovaj dogadjaj svima koji su se registrovali da ga slusaju.
 * U ozbiljnijim sistemima obicno postoji hijerarhija dogadjaja gde svaki opisuje neku specificnu promenu. 
 * Za vezbu, mozete napraviti hijerarhiju dogadjaja: apstraktni UpdateEvent i vise naslednika: za promenjeno svetlo  
 * na semaforu, za promenjen broj slobodnih mesta, kada je izaslo poslednje vozilo...
 */

public class UpdateEvent extends EventObject {	
	private int brojMesta;
	private SvetloNaSemaforu aktivnoSvetlo;
	
	public UpdateEvent(Object object, int aBrojMesta, SvetloNaSemaforu aAktivnoSvetlo) {		
		super(object);
		brojMesta = aBrojMesta;
		aktivnoSvetlo = aAktivnoSvetlo;
	}
	
	public int getBrojMesta() {
		return brojMesta;
	}
	
	public SvetloNaSemaforu getAktivnoSvetlo( ) {
		return aktivnoSvetlo;
	}

}

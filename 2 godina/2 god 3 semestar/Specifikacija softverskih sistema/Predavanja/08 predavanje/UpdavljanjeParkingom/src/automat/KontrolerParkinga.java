package automat;

import java.util.ArrayList;
import java.util.List;

import automat.event.*;

/**
 * Kontroler parkinga - klasa koja predstavlja kontekst za izvršavanje konacnog automata
 * 
 */

public class KontrolerParkinga {		
	
	private int brojSlobodnihMesta;
	private SvetloNaSemaforu aktivnoSvetlo;
	
	//Tekuce stanje:
	private Stanje tekuceStanje;				
	
	public  KontrolerParkinga() {
		//Inicijalizacija kontrolera:
		tekuceStanje = new ImaMesta(this);
		brojSlobodnihMesta = Konstante.MAX_BROJ_MESTA;
		aktivnoSvetlo = SvetloNaSemaforu.Zeleno;
	}
	
	public SvetloNaSemaforu getAktivnoSveto() {
		return aktivnoSvetlo;
	}
	
	public int getBrojSlobodnihMesta() {
		return brojSlobodnihMesta;
	}
	
	public void upaliCrveno() {
		aktivnoSvetlo = SvetloNaSemaforu.Crveno;
		osveziSemafor();
	}
	
	public void upaliZeleno() {
		aktivnoSvetlo = SvetloNaSemaforu.Zeleno;
		osveziSemafor();
	}		
	
	//Automat informacije o dogadjajima koje dobija od svojih senzora prosledjuje svom aktivnom stanju:
	public void usaoAutomobil() {				
		tekuceStanje.usaoAutomobil();
	}
	
	public void izasaoAutomobil() {
		tekuceStanje.izasaoAutomobil();
	}
	
	public void azurirajBrojMesta(int zaKoliko) {
		brojSlobodnihMesta = brojSlobodnihMesta + zaKoliko; 
		osveziSemafor();
	}
	
	public void promeniTekuceStanje(Stanje novoStanje) {
		tekuceStanje.exit();
		novoStanje.entry();
		tekuceStanje = novoStanje;				
	}
	
	//Lista onih koji "osluskuju" promenu podataka  (videti Observer pattern)
	//Nije deo State pattern-a, ovde je uveden radi izbegavanja cvrstog sprezanja sa elementima korisnickog interfejsa
	//Osim toga, na ovaj nacin se vise razlicitih elemenata korisnickog interfejsa (cak i oni koji nisu poznati u trenutku inicijalnog dizajna aplikacije) 
	// mogu pretpaltiti da slusaju promene
	private List<UpdateListener> listeners = new ArrayList<UpdateListener>();
		
	//Metoda za dodavanje listener-a (pretplata na slusanje) 
	public void addListener(UpdateListener listener) {
		listeners.add(listener);
	}
		
	//Metoda za uklanjanje listener-a
	public void removeListener(UpdateListener listener) {
		listeners.remove(listener);
	}
	
	public void osveziSemafor() {		
		UpdateEvent e = new UpdateEvent(this, brojSlobodnihMesta, aktivnoSvetlo);
		//Slanje dogadjaja da se desila promena svima koji su se registrovali za pracenje promena:
		for (UpdateListener updateListener : listeners) {
			updateListener.updatePerformed(e);
		}
		
		//U slucaju realnog kontrolera, ovde bi se direktno izdavale komande izlaznim uredjajima.
	}
}

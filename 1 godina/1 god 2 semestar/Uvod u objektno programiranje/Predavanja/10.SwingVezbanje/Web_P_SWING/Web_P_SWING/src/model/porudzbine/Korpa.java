package model.porudzbine;

import java.util.ArrayList;
import java.util.Date;

import util.StringUtils;

public class Korpa {
	private static int brojacId = 0;
	
	private int id;
	private Date datumKreiranja;
	
	private ArrayList<Stavka> stavke;
	// metode za rad sa korpom

	public Korpa() {
		this.id = ++brojacId;;
		stavke = new ArrayList<>();
		datumKreiranja = new Date();
	}
	
	public Korpa(int id) {
		this.id = id;
		stavke = new ArrayList<>();
		datumKreiranja = new Date();
		
		if(brojacId<id)
			brojacId = id;
	}

	public Korpa(int id, Date datumKreiranja) {
		this.id = id;
		this.datumKreiranja = datumKreiranja;
		this.stavke = new ArrayList<Stavka>();
		
		if(brojacId<id)
			brojacId = id;
	}
	
	@Override
	public String toString() {
		return id + " " + StringUtils.formatDate(datumKreiranja);
	}
	
	public String toStringAllStavke() {
		StringBuilder sb = new StringBuilder();
		for (Stavka s : stavke) {
			sb.append("\t"+s.toString()+"\n");
		}
		return sb.toString();
	}
	
	public double izracunajUkupnuCenu() {
		double cena = 0;
		for (Stavka stavka : stavke) {
			cena += stavka.getArtikal().getCena() * stavka.getKolicina();
		}
		return cena + (cena * Porudzbina.PDV);
	}
	
	public ArrayList<Stavka> getStavke() {
		return stavke;
	}

	public void setStavke(ArrayList<Stavka> stavke) {
		this.stavke = stavke;
	}

	public Date getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}

package model.artikli;

import util.StringUtils;

public abstract class Artikal {
	
	private static int brojacId = 0;
	
	private int id;
	private String naziv;
	private double cena;
	private int godinaProizvodnje;
	private int brojRaspolozivihPrimeraka;
	private int brojProdatihPrimeraka;

	public Artikal(String naziv, double cena, int godinaProizvodnje,
			int brojRaspolozivihPrimeraka,int brojProdatihPrimeraka) {
		this.id = ++brojacId;
		this.naziv = naziv;
		this.cena = cena;
		this.godinaProizvodnje = godinaProizvodnje;
		this.brojRaspolozivihPrimeraka=brojRaspolozivihPrimeraka;
		this.brojProdatihPrimeraka=brojProdatihPrimeraka;
	}
	
	public Artikal(int id, String naziv, double cena, int godinaProizvodnje,
			int brojRaspolozivihPrimeraka,int brojProdatihPrimeraka) {
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
		this.godinaProizvodnje = godinaProizvodnje;
		this.brojRaspolozivihPrimeraka=brojRaspolozivihPrimeraka;
		this.brojProdatihPrimeraka=brojProdatihPrimeraka;
		
		if(brojacId<id)
			brojacId = id;	
	}

	@Override
	public String toString() {
		return id + " " + StringUtils.clean(naziv) + " " + cena + " " + godinaProizvodnje
				+ " " + brojRaspolozivihPrimeraka+ " " + brojProdatihPrimeraka;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public int getBrojRaspolozivihPrimeraka() {
		return brojRaspolozivihPrimeraka;
	}

	public void setBrojRaspolozivihPrimeraka(int brojRaspolozivihPrimeraka) {
		this.brojRaspolozivihPrimeraka = brojRaspolozivihPrimeraka;
	}

	public int getBrojProdatihPrimeraka() {
		return brojProdatihPrimeraka;
	}

	public void setBrojProdatihPrimeraka(int brojProdatihPrimeraka) {
		this.brojProdatihPrimeraka = brojProdatihPrimeraka;
	}
}

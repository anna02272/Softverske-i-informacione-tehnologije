package model.artikli;

import util.StringUtils;

public class Knjiga extends Artikal {

	private String autor;
	private int brojStrana;
	private String izdavac;

	public Knjiga(String naziv, double cena, int godinaProizvodnje, 
			int brojRaspolozivihPrimeraka,
			int brojProdatihPrimeraka, 
			String autor, int brojStrana, String izdavac) {
		super(naziv, cena, godinaProizvodnje, brojRaspolozivihPrimeraka, brojProdatihPrimeraka);
		this.autor = autor;
		this.brojStrana = brojStrana;
		this.izdavac = izdavac;
	}
	
	public Knjiga(int id, String naziv, double cena, int godinaProizvodnje, int brojRaspolozivihPrimeraka,
			int brojProdatihPrimeraka, String autor, int brojStrana, String izdavac) {
		super(id, naziv, cena, godinaProizvodnje, brojRaspolozivihPrimeraka, brojProdatihPrimeraka);
		this.autor = autor;
		this.brojStrana = brojStrana;
		this.izdavac = izdavac;
	}

	@Override
	public String toString() {
		return super.toString() + " " + StringUtils.clean(autor) + " " + brojStrana + " " + StringUtils.clean(izdavac);
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getBrojStrana() {
		return brojStrana;
	}

	public void setBrojStrana(int brojStrana) {
		this.brojStrana = brojStrana;
	}

	public String getIzdavac() {
		return izdavac;
	}

	public void setIzdavac(String izdavac) {
		this.izdavac = izdavac;
	}
}

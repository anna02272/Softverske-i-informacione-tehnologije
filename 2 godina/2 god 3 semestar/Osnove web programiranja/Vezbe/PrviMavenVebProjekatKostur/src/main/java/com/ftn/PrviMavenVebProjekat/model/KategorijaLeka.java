package com.ftn.PrviMavenVebProjekat.model;

public class KategorijaLeka {
	private Long id;
	private String naziv;
	private String namena;
	private String opis;
	
	public KategorijaLeka() {
		super();
	}
	
	
	public KategorijaLeka(String naziv, String namena, String opis) {
		super();
		this.naziv = naziv;
		this.namena = namena;
		this.opis = opis;
	}
	public KategorijaLeka(Long id, String naziv, String namena, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.namena = namena;
		this.opis = opis;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getNamena() {
		return namena;
	}
	public void setNamena(String namena) {
		this.namena = namena;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}


	@Override
	public String toString() {
		return "KategorijaLeka [id=" + id + ", naziv=" + naziv + ", namena=" + namena + ", opis=" + opis + "]";
	}
	
	
	
}

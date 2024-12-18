package com.ftn.PrviMavenVebProjekat.model;

import java.time.LocalDateTime;

public class Projekcija {

	private Long id;
	private LocalDateTime datumIVreme;
	private Film film;
	private int sala;
	private String tipProjekcije;
	private double cenaKarte;
	
	public Projekcija() {
		super();
	}

	public Projekcija(LocalDateTime datumIVreme, Film film, int sala, String tipProjekcije, double cenaKarte) {
		super();
		this.datumIVreme = datumIVreme;
		this.film = film;
		this.sala = sala;
		this.tipProjekcije = tipProjekcije;
		this.cenaKarte = cenaKarte;
	}
	
	public Projekcija(Long id, LocalDateTime datumIVreme, Film film, int sala, String tipProjekcije, double cenaKarte) {
		super();
		this.id = id;
		this.datumIVreme = datumIVreme;
		this.film = film;
		this.sala = sala;
		this.tipProjekcije = tipProjekcije;
		this.cenaKarte = cenaKarte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public String getTipProjekcije() {
		return tipProjekcije;
	}

	public void setTipProjekcije(String tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	@Override
	public String toString() {
		return "Projekcija [id=" + id + ", datumIVreme=" + datumIVreme + ", film=" + film + ", sala=" + sala
				+ ", tipProjekcije=" + tipProjekcije + ", cenaKarte=" + cenaKarte + "]";
	}
}

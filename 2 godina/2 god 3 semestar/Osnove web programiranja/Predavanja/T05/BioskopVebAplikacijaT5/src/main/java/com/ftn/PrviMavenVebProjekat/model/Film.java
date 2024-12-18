package com.ftn.PrviMavenVebProjekat.model;

import java.io.Serializable;

public class Film implements Serializable{

	private Long id;
	private String naziv;
	private int trajanje;
	
	public Film() {
		super();
	}

	public Film(String naziv, int trajanje) {
		super();
		this.naziv = naziv;
		this.trajanje = trajanje;
	}
	public Film(Long id, String naziv, int trajanje) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.trajanje = trajanje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", naziv=" + naziv + ", trajanje=" + trajanje + "]";
	}
}

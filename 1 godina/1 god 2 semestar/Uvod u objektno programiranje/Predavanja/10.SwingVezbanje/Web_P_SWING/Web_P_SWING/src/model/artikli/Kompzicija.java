package model.artikli;

import util.StringUtils;

public class Kompzicija {
	
	private int redBr;
	private String naziv;
	private double trajanje;
	
	public Kompzicija(int redBr, String naziv, double trajanje) {
		super();
		this.redBr = redBr;
		this.naziv = naziv;
		this.trajanje = trajanje;
	}
	
	@Override
	public String toString() {
		return redBr + ". " + StringUtils.clean(naziv) + " traje " + trajanje + " minuta";
	}
	
	public int getRedBr() {
		return redBr;
	}
	public void setRedBr(int redBr) {
		this.redBr = redBr;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public double getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}
}

package artikli;

public class Kompozicija {

	private String naziv;
	private int trajanje;
	private boolean obrisana;
	
	public Kompozicija() {
		this.naziv = "";
		this.trajanje = 0;
		this.obrisana = false;
	}

	public Kompozicija(String naziv, int trajanje, boolean obrisana) {
		super();
		this.naziv = naziv;
		this.trajanje = trajanje;
		this.obrisana = obrisana;
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

	public boolean isObrisana() {
		return obrisana;
	}

	public void setObrisana(boolean obrisana) {
		this.obrisana = obrisana;
	}

	@Override
	public String toString() {
		return "Kompozicija [Naziv:" + naziv + ", Trajanje: " + trajanje + ", Obrisana = " + obrisana + "]";
	}
}
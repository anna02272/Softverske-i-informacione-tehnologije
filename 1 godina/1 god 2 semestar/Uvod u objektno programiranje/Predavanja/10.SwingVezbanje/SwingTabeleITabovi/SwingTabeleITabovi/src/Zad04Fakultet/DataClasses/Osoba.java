package Zad04Fakultet.DataClasses;

public abstract class Osoba {

	protected String JMBG;
	protected String ime;
	protected String prezime;
	
	protected boolean statusZapisa;
	
	public Osoba(String JMBG, String ime, String prezime) {
		super();
		this.JMBG = JMBG;
		this.ime = ime;
		this.prezime = prezime;
		statusZapisa = true;
	}

	public String toFile() {
		return JMBG + "|" + ime+ "|" + prezime+ "|" + statusZapisa ;
	}
	
	@Override
	public String toString() {
		return ime + " " + prezime + ", JMBG: " + JMBG;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public boolean isStatusZapisa() {
		return statusZapisa;
	}

	public void setStatusZapisa(boolean statusZapisa) {
		this.statusZapisa = statusZapisa;
	}
}

package osobe;

public class Prodavac {

	private String ime;
	private String prezime;
	private Pol pol;
	private String korisnickoIme;
	private String lozinka;
	private boolean obrisan;
	
	public Prodavac() {
		this.ime = "";
		this.prezime = "";
		this.pol = Pol.ZENSKI;
		this.korisnickoIme = "";
		this.lozinka = "";
		this.obrisan = false;
	}

	public Prodavac(String ime, String prezime, Pol pol, String korisnickoIme, String lozinka, boolean obrisan) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.obrisan = obrisan;
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

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	@Override
	public String toString() {
		return "PRODAVAC \nIme: " + ime +
						"\nPrezime: " + prezime +
						"\nPol: " + pol +
						"\nKorisnicko Ime: " + korisnickoIme + 
						"\nLozinka: " + lozinka +
						"\nObrisan: " + obrisan;
	}
	
}
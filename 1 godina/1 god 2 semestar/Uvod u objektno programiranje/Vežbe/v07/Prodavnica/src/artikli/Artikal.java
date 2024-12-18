package artikli;

public abstract class Artikal {

	protected String identifikacioniKod;
	protected double cena;
	protected String naziv;
	protected boolean obrisan;
	
	public Artikal() {
		this.identifikacioniKod = "";
		this.cena = 0;
		this.naziv = "";
		this.obrisan = false;
	}

	
	public Artikal(String identifikacioniKod, double cena, String naziv, boolean obrisan) {
		super();
		this.identifikacioniKod = identifikacioniKod;
		this.cena = cena;
		this.naziv = naziv;
		this.obrisan = obrisan;
	}


	public String getIdentifikacioniKod() {
		return identifikacioniKod;
	}

	public void setIdentifikacioniKod(String identifikacioniKod) {
		this.identifikacioniKod = identifikacioniKod;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}


	@Override
	public String toString() {
		return "\nIdentifikacioni Kod: " + identifikacioniKod
			 + "\nNaslov: " + naziv
			 + "\nCena: " + cena
			 + "\nObrisan: " + obrisan;
	}
}
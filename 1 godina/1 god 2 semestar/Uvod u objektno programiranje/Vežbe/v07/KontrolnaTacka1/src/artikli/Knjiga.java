package artikli;

public class Knjiga extends Artikal {

	private String autor;
	private int brojStrana;
	private boolean tvrdeKorice;
	
	public Knjiga() {
		super();
		this.autor = "";
		this.brojStrana = 0;
		this.tvrdeKorice = false;
	}
	
	public Knjiga(String identifikacioniKod, double cena, String naziv, boolean obrisan, String autor, int brojStrana,
			boolean tvrdeKorice) {
		super(identifikacioniKod, cena, naziv, obrisan);
		this.autor = autor;
		this.brojStrana = brojStrana;
		this.tvrdeKorice = tvrdeKorice;
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

	public boolean isTvrdeKorice() {
		return tvrdeKorice;
	}

	public void setTvrdeKorice(boolean tvrdeKorice) {
		this.tvrdeKorice = tvrdeKorice;
	}

	@Override
	public String toString() {
		return "KNJIGA " + super.toString() + 
				"\nAutor: " + this.autor +
				"\nBroj strana: " + this.brojStrana + 
				"\nTvrde korice: " + this.tvrdeKorice;
	}
}
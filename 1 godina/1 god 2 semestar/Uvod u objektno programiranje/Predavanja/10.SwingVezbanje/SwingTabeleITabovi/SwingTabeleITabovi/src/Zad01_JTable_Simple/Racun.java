package Zad01_JTable_Simple;

public class Racun {

	protected String 	indetifikator;
	protected String    nazivRacuna;
	protected boolean   aktivan;
	protected double 	stanjeRacuna;
	
	public Racun(String indetifikator, String nazivRacuna, boolean aktivan,
			double stanjeRacuna) {
		super();
		this.indetifikator = indetifikator;
		this.nazivRacuna = nazivRacuna;
		this.aktivan = aktivan;
		this.stanjeRacuna = stanjeRacuna;
	}

	public String getIndetifikator() {
		return indetifikator;
	}

	public void setIndetifikator(String indetifikator) {
		this.indetifikator = indetifikator;
	}

	public String getNazivRacuna() {
		return nazivRacuna;
	}

	public void setNazivRacuna(String nazivRacuna) {
		this.nazivRacuna = nazivRacuna;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public double getStanjeRacuna() {
		return stanjeRacuna;
	}

	public void setStanjeRacuna(double stanjeRacuna) {
		this.stanjeRacuna = stanjeRacuna;
	}

	public static int getBrojAtributaKlase(){
		return 4;
	}
}

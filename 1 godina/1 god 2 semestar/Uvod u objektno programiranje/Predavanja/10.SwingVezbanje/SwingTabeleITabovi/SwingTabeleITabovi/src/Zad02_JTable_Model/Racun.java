package Zad02_JTable_Model;

public class Racun {

	protected String 	indetifikator;
	protected String    nazivRacuna;
	protected boolean   aktivan;
	protected double 		stanjeRacuna;
	
	protected boolean statusZapisa;
	
	public Racun(String indetifikator, String nazivRacuna, boolean aktivan,
			double stanjeRacuna) {
		super();
		this.indetifikator = indetifikator;
		this.nazivRacuna = nazivRacuna;
		this.aktivan = aktivan;
		this.stanjeRacuna = stanjeRacuna;
		statusZapisa = true;
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

	public boolean isStatusZapisa() {
		return statusZapisa;
	}

	public void setStatusZapisa(boolean statusZapisa) {
		this.statusZapisa = statusZapisa;
	}

	public static int getBrojAtributaKlase(){
		return 4;
	}
}

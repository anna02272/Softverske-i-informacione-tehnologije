package Zad04Fakultet.DataClasses;


public class Hobi {

	protected String sifra;
	protected String naziv;
	
	protected boolean statusZapisa;
	
	public Hobi(String sifra, String naziv) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		statusZapisa = true;
	}
	//konstruktor sa jednim parametrom
	//konstruktor koji dobija tekstualnu reprezentaciju
	public Hobi(String tekst){
		String [] tokeni = tekst.split("\\|");
		//npr. 		"pl|Plivanje|true"
		//tokeni 	  0	1		2
		
		if(tokeni.length!=3){
			System.out.println("Greska pri ocitavanju hobia");
			System.out.println(tekst);
			System.exit(0);
		}
		
		this.sifra = tokeni[0];
		this.naziv = tokeni[1];
		this.statusZapisa = Boolean.parseBoolean(tokeni[2]);
	}
		
	public String toFile() {
		String retVal = null;
		retVal = sifra + "|" + naziv+ "|" + statusZapisa;
		return retVal;
	}
	
	@Override
	public String toString() {
		String retVal = "Hobi: " + naziv + " sa sifrom:"+sifra+ " je "+ ((statusZapisa==true)?"aktivan":"obrisan");
		return retVal;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public boolean isStatusZapisa() {
		return statusZapisa;
	}
	public void setStatusZapisa(boolean statusZapisa) {
		this.statusZapisa = statusZapisa;
	}
}

package Zad04Fakultet.DataClasses;

import Zad04Fakultet.Database.KategorijaFinanasiranjaDB;


public class KategorijaFinanasiranja {

	protected String sifra;
	protected String naziv;
	protected String opis;
	protected KategorijaFinanasiranja nadkategorija;
	
	protected boolean statusZapisa;

	public KategorijaFinanasiranja(String sifra, String naziv, String opis) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.opis = opis;
		nadkategorija = null;
		statusZapisa = true;
	}
	
	public KategorijaFinanasiranja(String sifra, String naziv, String opis,
			KategorijaFinanasiranja nadkategorija) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.opis = opis;
		this.nadkategorija = nadkategorija;
		statusZapisa = true;
	}

	//konstruktor sa jednim parametrom
	//konstruktor koji dobija tekstualnu reprezentaciju
	public KategorijaFinanasiranja(String tekst){
		String [] tokeni = tekst.split("\\|");
		//npr. 		"sifra|Naziv|Opis|Nadkategorija|true"
		//tokeni 		0	1		2		3		4
		
		if(tokeni.length!=5){
			System.out.println("Greska pri ocitavanju kategorije");
			System.out.println(tekst);
			System.exit(0);
		}
		
		sifra = tokeni[0];
		naziv = tokeni[1];
		opis = null;
		if(!tokeni[2].equals("null"))
			opis = tokeni[2];
		nadkategorija = null;
		if(!tokeni[3].equals("null"))
			nadkategorija = KategorijaFinanasiranjaDB.pronadjiKategorijuFinanasiranja(tokeni[3]);
		
		statusZapisa = Boolean.parseBoolean(tokeni[4]);
	}
	public String toFile() {
		String retVal = null;
		retVal = sifra + "|" + naziv+ "|" + Utility.vratiStringToFile(opis) + "|" + ((nadkategorija==null)?"null":nadkategorija.getSifra()) + "|" + statusZapisa;
		return retVal;
	}
	
	@Override
	public String toString() {
		String retVal = "Kategorija finansiranje: " + naziv + " sa sifrom:"+sifra+ " je "+ ((statusZapisa==true)?"aktivna":"obrisana")+
				" (" + opis+ ","+ ((nadkategorija==null)?"null":nadkategorija.getNaziv())+" )";
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public KategorijaFinanasiranja getNadkategorija() {
		return nadkategorija;
	}

	public void setNadkategorija(KategorijaFinanasiranja nadkategorija) {
		this.nadkategorija = nadkategorija;
	}

	public boolean isStatusZapisa() {
		return statusZapisa;
	}

	public void setStatusZapisa(boolean statusZapisa) {
		this.statusZapisa = statusZapisa;
	}
}

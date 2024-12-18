package Zad04Fakultet.DataClasses;

import java.util.ArrayList;

import Zad04Fakultet.Database.HobiDB;
import Zad04Fakultet.Database.KategorijaFinanasiranjaDB;

public class Student extends Osoba {

	protected String index;
	protected KategorijaFinanasiranja katFin;
	protected ArrayList<Hobi> hobiji;
	protected int godinaUpisa;
	
	public Student(String JMBG, String ime, String prezime, String index, KategorijaFinanasiranja katFin) {
		super(JMBG, ime, prezime);
		this.index = index;
		this.katFin = katFin;
		this.hobiji = new ArrayList<Hobi>();
	}

	//konstruktor sa jednim parametrom
	//konstruktor koji dobija tekstualnu reprezentaciju
	public Student(String tekst){
		super("", "", "");
		String [] tokeni = tekst.split("\\|");
		//npr. 		"0001|Petar|Petrovic|RS 1/2012|budz|pl;tr;|true"
		//tokeni 		0	1		2		3		4	5		6
		
		if(tokeni.length!=7){
			System.out.println("Greska pri ocitavanju studenta");
			System.out.println(tekst);
			System.exit(0);
		}
		
		this.JMBG = tokeni[0];
		this.ime = tokeni[1];
		this.prezime = tokeni[2];
		this.index = tokeni[3];
		
		katFin = null;
		if(!tokeni[4].equals("null"))
			katFin = KategorijaFinanasiranjaDB.pronadjiKategorijuFinanasiranja(tokeni[4]);
		
		this.hobiji = new ArrayList<Hobi>();
		String[] sifeHobija = tokeni[5].split(";");
		for (int i = 0; i < sifeHobija.length; i++) {
			if(HobiDB.pronadjiHobi(sifeHobija[i])!=null)
				hobiji.add(HobiDB.pronadjiHobi(sifeHobija[i]));
			else
				hobiji.add(new Hobi(sifeHobija[i], ""));
		}
		
		statusZapisa = Boolean.parseBoolean(tokeni[6]);
	}
		
	public String toFile() {
		String hobijiSt = "";
		for (Hobi el : hobiji) {
			hobijiSt+=el.getSifra()+";";
		}
		if(hobijiSt.equals(""))
			hobijiSt=";";
		
		String retVal = null;
		retVal = JMBG + "|" + ime+ "|" + prezime + "|" + index + "|" + katFin.getSifra() + "|" + hobijiSt + "|" +statusZapisa;
		return retVal;
	}
	
	@Override
	public String toString() {
		String retVal = super.toString();
		retVal = "Student: " + retVal + " sa indeksom:"+index+ " je "+ ((statusZapisa==true)?"aktivan":"obrisan")+" i finansira se "+katFin.getNaziv();
		return retVal;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public KategorijaFinanasiranja getKatFin() {
		return katFin;
	}

	public void setKatFin(KategorijaFinanasiranja katFin) {
		this.katFin = katFin;
	}

	public ArrayList<Hobi> getHobiji() {
		return hobiji;
	}

	public void setHobiji(ArrayList<Hobi> hobiji) {
		this.hobiji = hobiji;
	}

	public int getGodinaUpisa() {
		if(godinaUpisa==0){
			godinaUpisa = 2000+ (int) Math.round(Math.random()*10);
		}
		return godinaUpisa;
	}
}

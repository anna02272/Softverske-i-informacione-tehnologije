package Zad04Fakultet.DataClasses;


public class Profesor extends Osoba{
	
	protected String zvanje;
	protected double plata;
	protected String radnoMesto;
	
	public Profesor(String JMBG, String ime, String prezime, String zvanje,
			double plata, String radnoMesto) {
		super(JMBG, ime, prezime);
		this.zvanje = zvanje;
		this.plata = plata;
		this.radnoMesto = radnoMesto;
	}

	//konstruktor sa jednim parametrom
	//konstruktor koji dobija tekstualnu reprezentaciju
	public Profesor(String tekst){
		super("", "", "");
		
		String [] tokeni = tekst.split("\\|");
		//npr. 		"0002|Petar|Petrovic|docent|22000|predavac|true"
		//tokeni 		0	1		2	 3		4		5	 	6
		
		if(tokeni.length!=7 || !Utility.isDouble(tokeni[4])){
			System.out.println("Greska pri ocitavanju profesora");
			System.out.println(tekst);
			System.exit(0);
		}
		
		this.JMBG = tokeni[0];
		this.ime = tokeni[1];
		this.prezime = tokeni[2];
		this.zvanje = tokeni[3];
		this.plata = Double.parseDouble(tokeni[4]);
		this.radnoMesto = tokeni[5];
		
		statusZapisa = Boolean.parseBoolean(tokeni[6]);
	}
	
	public String toFile() {
		String retVal = JMBG + "|" + ime+ "|" + prezime + "|" + zvanje + "|" + plata + "|" + radnoMesto+ "|" +statusZapisa;
		return retVal;
	}
	
	@Override
	public String toString() {
		String retVal = super.toString();
		retVal = "Profesor " + retVal + ", sa zvanjem: " + zvanje + ", ima platu: " + plata + ", radi na poziciji: " + radnoMesto;
		return retVal;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getRadnoMesto() {
		return radnoMesto;
	}

	public void setRadnoMesto(String radnoMesto) {
		this.radnoMesto = radnoMesto;
	}
}

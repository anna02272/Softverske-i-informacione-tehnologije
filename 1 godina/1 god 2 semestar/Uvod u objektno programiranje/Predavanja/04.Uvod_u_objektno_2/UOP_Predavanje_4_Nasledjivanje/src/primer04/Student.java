package primer04;

import java.util.ArrayList;

//klasa
public class Student {

	private static int brojacID = 0;
	
	protected int id;
	protected String ime;
	protected String prezime;
	protected String grad;
	protected String indeks;
	//predmete koje pohađa student
	protected ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
	//sve ispitne prijave za studenta
	protected ArrayList<IspitnaPrijava> ispitnePrijave = new ArrayList<IspitnaPrijava>();
		
		
	/** KONSTRUKTORI ****/
	// konstruktor bez parametra
	public Student(){}
	
	//konstruktor sa vise parametara
	public Student(int id, String ime, String prezime, String grad, String indeks) {
		if(id==0){
			brojacID++;
			id = brojacID;
		}
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.indeks = indeks;
	}

	public Student(int id, String ime, String prezime, String grad, String indeks, ArrayList<Predmet> predmeti) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.indeks = indeks;
		this.predmeti = predmeti;
	}

	//konstruktor koji popunjava podatke na osnovu o�?itanog teksta iz fajla studenti.csv
	public Student(String tekst){
		String [] tokeni = tekst.split(",");
		//npr. 		1,E1 01/2011,SrÄ‘anov,Konstantin,Loznica
		//tokeni 	0		1		2		3			4
		
		if(tokeni.length!=5){
			System.out.println("Greska pri ocitavanju studenta "+tekst);
			//izlazak iz aplikacije
			System.exit(0);
		}
		
		id = Integer.parseInt(tokeni[0]);
		indeks = tokeni[1];
		prezime = tokeni[2];
		ime = tokeni[3];
		grad = tokeni[4];
		
		if (brojacID<id) {
			brojacID=id;
		}
	}
	//metode
	
	//metoda koja kreira tekstualnu reprezentaciju Studenta za fajl
	public String toFileRepresentation(){
		
		StringBuilder bild = new StringBuilder(); 
		bild.append(id +","+ indeks+","+prezime+","+ime+","+grad);
		return bild.toString();
	}
	
	@Override
	public String toString() {
		String ispis = "Student sa id " + id + " cije je ime i prezime " 
				+ ime + " " + prezime + " ima indeks " + indeks + " i zivi u gradu " + grad;
		return ispis;
	}
	
	public String toStringAllPredmet() {
		StringBuilder sb = new StringBuilder("Student sa id " + id + " cije je ime i prezime "
				+ ime + " " + prezime + " ima indeks " + indeks + " i zivi u gradu " + grad);
		
		if(predmeti != null){
			sb.append(" i pohađa predmete\n");
			for (int i = 0; i < predmeti.size(); i++) {
				sb.append("\t"+predmeti.get(i).toString()+"\n");
			}
		}
		return sb.toString();
	}
	
	public String toStringAllIspitnaPrijava() {
		StringBuilder sb = new StringBuilder("Student sa id " + id + " cije je ime i prezime " 
				+ ime + " " + prezime + " ima indeks " + indeks + " i zivi u gradu " + grad);
		
		if(predmeti != null){
			sb.append(" i ima ispitne prijave\n");
			for (int i = 0; i < ispitnePrijave.size(); i++) {
				sb.append("\t"+ispitnePrijave.get(i).toString()+"\n");
			}
		}
		
		return sb.toString();
	}
	
	public double izracunajProsek() {
		double retVal = 0;
		int brojacPolozenihPredmeta = 0;
		for (IspitnaPrijava ispitnaPrijava : ispitnePrijave) {
			if(ispitnaPrijava.sracunajOcenu()>5){
				retVal+=ispitnaPrijava.sracunajOcenu();
				brojacPolozenihPredmeta++;
			}
			
		}
		return retVal/brojacPolozenihPredmeta;
	}

	//dva objekta su ista ako imaju isti id
	public boolean isti(Student st) {
		boolean isti = false;
		if(id==st.id)
			isti = true;
		return isti;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	//set i get metode
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getIndeks() {
		return indeks;
	}

	public void setIndeks(String indeks) {
		this.indeks = indeks;
	}

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public ArrayList<IspitnaPrijava> getIspitnePrijave() {
		return ispitnePrijave;
	}

	public void setIspitnePrijave(ArrayList<IspitnaPrijava> ispitnePrijave) {
		this.ispitnePrijave = ispitnePrijave;
	}
	
}

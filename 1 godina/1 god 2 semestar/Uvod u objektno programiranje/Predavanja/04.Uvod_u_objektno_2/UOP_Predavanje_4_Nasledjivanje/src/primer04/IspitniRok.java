package primer04;

import java.util.ArrayList;

public class IspitniRok {

	private static int brojacID = 0;
	
	protected int id;
	protected String naziv;
	protected String pocetak;
	protected String kraj;
	//ispitne prijave u isputnom roku
	protected ArrayList<IspitnaPrijava> ispitnePrijave = new ArrayList<IspitnaPrijava>();

	public IspitniRok(int id, String naziv, String pocetak, String kraj) {
		if(id==0){
			brojacID++;
			id = brojacID;
		}
		this.id = id;
		this.naziv = naziv;
		this.pocetak = pocetak;
		this.kraj = kraj;
	}
	
	public IspitniRok(String tekst){
		String [] tokeni = tekst.split(",");
		//npr. 		1,Januarski,2015-01-15,2015-01-29
		//tokeni 	0		1		2		3		
		
		if(tokeni.length!=4){
			System.out.println("Greska pri ocitavanju ispitnog roka "+tekst);
			//izlazak iz aplikacije
			System.exit(0);
		}
		
		id = Integer.parseInt(tokeni[0]);
		naziv = tokeni[1];
		pocetak = tokeni[2];
		kraj = tokeni[3];
		
		if (brojacID<id) {
			brojacID=id;
		}
	}
	
	//metode
	
	//metoda koja kreira tekstualnu reprezentaciju Ispitnog roka za fajl
	public String toFileRepresentation(){
		StringBuilder bild = new StringBuilder(); 
		bild.append(id +","+ naziv+","+pocetak+","+kraj);
		return bild.toString();
	}
	
	@Override
	public String toString() {
		String ispis = "Ispitni rok sa id " + id + " �?iji je naziv " 
				+ naziv + " održava se " + pocetak + " do " + kraj;
		return ispis;
	}
	
	public String toStringAllIspitnaPrijava() {
		StringBuilder sb = new StringBuilder("Ispitni rok sa id " + id + "ciji je naziv " 
				+ naziv + " održava se " + pocetak + " do " + kraj);
		
		if(ispitnePrijave != null){
			sb.append(" i pohađa predmete\n");
			for (int i = 0; i < ispitnePrijave.size(); i++) {
				sb.append("\t"+ispitnePrijave.get(i).toString()+"\n");
			}
		}
		return sb.toString();
	}

	//dva objekta su ista ako imaju isti id

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IspitniRok other = (IspitniRok) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public ArrayList<IspitnaPrijava> getIspitnePrijave() {
		return ispitnePrijave;
	}

	public void setIspitnePrijave(ArrayList<IspitnaPrijava> ispitnePrijave) {
		this.ispitnePrijave = ispitnePrijave;
	}
}

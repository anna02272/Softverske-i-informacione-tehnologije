package primer04;

import java.util.ArrayList;
import java.util.Collections;



public class Test {
	
	public static ArrayList<Student> sviStudenti = new ArrayList<Student>();
	public static ArrayList<Predmet> sviPredmeti = new ArrayList<Predmet>();
	
	/*** ISPIS***/
	public static void ispisiSveStudente() {
		//ispis svih studenata
		for (int i = 0; i < sviStudenti.size(); i++) {
			System.out.println(sviStudenti.get(i).toStringAllPredmet());
		}
	}
	
	public static void ispisiPredmete(){
		for (Predmet pr : sviPredmeti) {
			System.out.println(pr.toString());
		}
	}

	/*** PRETRAGA***/
	public static Student pratragaStudenta(int id){
		Student obj = null;
		for (Student st : sviStudenti) {
			if(st.getId() == id){
				obj = st;
				break;
			}	
		}
		return obj;
	}
	
	public static Predmet pratragaPredmeta(int id){
		Predmet obj = null;
		for (Predmet pr : sviPredmeti) {
			if(pr.getId() == id){
				obj = pr;
				break;
			}	
		}
		return obj;
	}
	
	/*** UČITAVANJE***/
	public static void ucitajPodatkeStudent(){
		//Sadrzaj csv fajla koji cuva podatke o studentima
		String text ="1,E1 01/2011,Srđanov,Konstantin,Loznica\n"
				+ "2,E1 02/2012,Baki,Strahinja,Novi Sad\n"
				+ "3,E1 03/2013,Trajković,Nebojša,Inđija\n"
				+ "4,E2 01/2011,Sekulić,Miloš,Vršac\n"
				+ "5,E2 02/2012,Askin,Vuk,Novi Sad\n"
				+ "6,E2 03/2013,Klainić,Marko,Sombor\n"
				+ "7,E2 04/2011,Marko,Panić,Zrenjanin";
		String [] sviRedovi = text.split("\n");
		for (int i = 0; i < sviRedovi.length; i++) {
			sviStudenti.add(new Student(sviRedovi[i]));
		}
	}
	
	public static void ucitajPodatkePredmet(){
		//Sadrzaj csv fajla koji cuva podatke o predmetima
		String text ="1,Matematika\n"
				+ "2,Fizika\n"
				+ "3,Elektrotehnika\n"
				+ "4,Informatika";
		String [] sviRedovi = text.split("\n");
		for (int i = 0; i < sviRedovi.length; i++) {
			sviPredmeti.add(new Predmet(sviRedovi[i]));
		}
	}
	
	public static void ucitajPodatkePohadja(){
		//Sadrzaj csv fajla koji cuva podatke o pohađa
		//Uparivanje objekata se ostvaruje na osnovu identifikatora ID
		String text =
				//Srđanov Konstantin pohađa Matematiku, Fiziku i Elektrotehniku
				"1,1\n"
				+ "1,2\n"
				+ "1,3\n"
				//Baki Strahinja pohađa Fiziku
				+ "2,2\n"
				//Trajković Nebojša pohađa Matematiku
				+ "3,1\n"
				//Sekulić Miloš pohađa Matematiku,Elektrotehniku i Informatiku
				+ "4,1\n"
				+ "4,3\n"
				+ "4,4\n"
				//Askin Vuk pohađa Matematiku i Fiziku
				+ "5,1\n"
				+ "5,2\n"
				//Klainić Marko pohađa Elektrotehniku
				+ "6,3";
				//Marko Panić ne pohađa ništa, STA ĆEMO SA NJIM????
				//PA NIŠTA :)
		
		String [] sviRedovi = text.split("\n");
		for (int i = 0; i < sviRedovi.length; i++) {
			String [] pohadjaPodaciTekst = sviRedovi[i].split(",");
			int idStudenta = Integer.parseInt(pohadjaPodaciTekst[0]);
			int idPredmeta = Integer.parseInt(pohadjaPodaciTekst[1]);
			Student st = pratragaStudenta(idStudenta);
			Predmet pr = pratragaPredmeta(idPredmeta);
			if(st!=null && pr!=null){
				st.getPredmeti().add(pr);
				pr.getStudenti().add(st);
			}
		}
	}
	public static void main(String[] args) {

		ucitajPodatkeStudent();
		ucitajPodatkePredmet();
		ucitajPodatkePohadja();
		
		ispisiSveStudente();
		System.out.println("\nSortiraj i ispisi studente po imenu ASC\n");
		Collections.sort(sviStudenti, new StudentNameComparator(-1));
		ispisiSveStudente();
		
		System.out.println("\nSortiraj i ispisi studente po broj predmeta koje slusa ASC\n");
		Collections.sort(sviStudenti, new StudentBrojPredmetaKojeSlusaComparator(-1));
		ispisiSveStudente();
	}
}

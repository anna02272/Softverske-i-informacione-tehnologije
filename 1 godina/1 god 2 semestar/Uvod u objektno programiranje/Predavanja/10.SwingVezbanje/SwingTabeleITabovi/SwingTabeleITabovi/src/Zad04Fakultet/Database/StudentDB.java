package Zad04Fakultet.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import Zad04Fakultet.DataClasses.Student;


public class StudentDB {

	public static boolean ocintan = false;
	public static ArrayList<Student> listaStudenta =  new ArrayList<Student>();

	public static Student pronadjiStudenta(String sifra){
		Student retVal = null;
		for (int i = 0; i < listaStudenta.size(); i++) {
			Student el = listaStudenta.get(i);
			if (el.getIndex().equals(sifra)) {
				retVal = el;
				break;
			}
		}
		return retVal;
	}
	
	public static void pisiUFajl(){
		try {
	    	String putanja = vratiRelativnuPutanjuDoFajla("studenti.txt"); // u folderu Direktorijum6 se nalazi studenti.txt
	    	//provera da li postoji, kreiranje / brisanje datoteke
		    File obrisiKreirajDatoteka = new File(putanja);
		    if (!obrisiKreirajDatoteka.exists())
		    	obrisiKreirajDatoteka.createNewFile();

		    PrintWriter out = new PrintWriter(new FileWriter(putanja));

		    for(Student st : listaStudenta){
				out.println(st.toFile());
			}
		    
	        // Isprazni bafere.
	    	// zatvaranje pisaca i dealokacija zauzetih resursa 
	        out.flush();
	        out.close();
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }
	}
	
	public static void citajIzFajla(){
		if (ocintan) {
			return;
		}
		
		 try {
			 	listaStudenta.clear();
		    	String putanja = vratiRelativnuPutanjuDoFajla("studenti.txt"); // u folderu Direktorijum6 se nalazi studenti.txt
		    	//provera da li postoji, kreiranje / brisanje datoteke
			    File obrisiKreirajDatoteka = new File(putanja);
			    if (!obrisiKreirajDatoteka.exists()){
			    	System.out.println("Fajl sa podacima ne postoji");
			    	return;
			    }

			    BufferedReader in = new BufferedReader(new FileReader(putanja));
				String s;
				while ((s = in.readLine()) != null) {
					Student st = new Student(s);
				   listaStudenta.add(st);
				}
				ocintan = true;
		        // zatvaranje citaca i dealokacija zauzetih resursa
		    	in.close();  
		      } catch (Exception ex) {
		        ex.printStackTrace();
		      }
	}
	
	public static String vratiRelativnuPutanjuDoFajla(String fileName){
		String separatorPutanje = System.getProperty("file.separator");
		String relativnaPutanja = "." + separatorPutanje + "resource"+ separatorPutanje + "data1"+ separatorPutanje + fileName;
		return relativnaPutanja;
	}

}

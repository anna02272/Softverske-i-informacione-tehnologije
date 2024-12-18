package Zad04Fakultet.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import Zad04Fakultet.DataClasses.Profesor;

public class ProfesorDB {

	public static boolean ocintan = false;
	public static ArrayList<Profesor> listaProfesora =  new ArrayList<Profesor>();

	public static void pisiUFajl(){
		try {
	    	String putanja = vratiRelativnuPutanjuDoFajla("profesori.txt"); // u folderu Direktorijum6 se nalazi profesori.txt
	    	//provera da li postoji, kreiranje / brisanje datoteke
		    File obrisiKreirajDatoteka = new File(putanja);
		    if (!obrisiKreirajDatoteka.exists())
		    	obrisiKreirajDatoteka.createNewFile();

		    PrintWriter out = new PrintWriter(new FileWriter(putanja));

		    for(Profesor pr : listaProfesora){
				out.println(pr.toFile());
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
			 	listaProfesora.clear();
		    	String putanja = vratiRelativnuPutanjuDoFajla("profesori.txt"); // u folderu Direktorijum6 se nalazi profesori.txt
		    	//provera da li postoji, kreiranje / brisanje datoteke
			    File obrisiKreirajDatoteka = new File(putanja);
			    if (!obrisiKreirajDatoteka.exists()){
			    	System.out.println("Fajl sa podacima ne postoji");
			    	return;
			    }

			    BufferedReader in = new BufferedReader(new FileReader(putanja));
				String s;
				while ((s = in.readLine()) != null) {
					Profesor pr = new Profesor(s);
				   listaProfesora.add(pr);
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

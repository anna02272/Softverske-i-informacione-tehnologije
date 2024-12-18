package Zad04Fakultet.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import Zad04Fakultet.DataClasses.Hobi;

public class HobiDB {
	public static boolean ocintan = false;
	public static ArrayList<Hobi> lista =  new ArrayList<Hobi>();

	public static Hobi pronadjiHobi(String sifra){
		Hobi retVal = null;
		for (int i = 0; i < lista.size(); i++) {
			Hobi el = lista.get(i);
			if (el.getSifra().equals(sifra)) {
				retVal = el;
				break;
			}
		}
		return retVal;
	}
	public static void pisiUFajl(){
		try {
	    	String putanja = vratiRelativnuPutanjuDoFajla("hobi.txt"); // u folderu Direktorijum6 se nalazi studenti.txt
	    	//provera da li postoji, kreiranje / brisanje datoteke
		    File obrisiKreirajDatoteka = new File(putanja);
		    if (!obrisiKreirajDatoteka.exists())
		    	obrisiKreirajDatoteka.createNewFile();
	
		    PrintWriter out = new PrintWriter(new FileWriter(putanja));
	
		    for(Hobi el : lista){
				out.println(el.toFile());
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
			 	lista.clear();
		    	String putanja = vratiRelativnuPutanjuDoFajla("hobi.txt"); // u folderu Direktorijum6 se nalazi studenti.txt
		    	//provera da li postoji, kreiranje / brisanje datoteke
			    File obrisiKreirajDatoteka = new File(putanja);
			    if (!obrisiKreirajDatoteka.exists()){
			    	System.out.println("Fajl sa podacima ne postoji");
			    	return;
			    }
	
			    BufferedReader in = new BufferedReader(new FileReader(putanja));
				String s;
				while ((s = in.readLine()) != null) {
					Hobi st = new Hobi(s);
				   lista.add(st);
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

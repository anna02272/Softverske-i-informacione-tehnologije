package Zad04Fakultet.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import Zad04Fakultet.DataClasses.KategorijaFinanasiranja;


public class KategorijaFinanasiranjaDB {

	public static boolean ocintan = false;
	public static ArrayList<KategorijaFinanasiranja> listaKategorijaFinanasiranja =  new ArrayList<KategorijaFinanasiranja>();

	public static KategorijaFinanasiranja pronadjiKategorijuFinanasiranja(String sifra){
		KategorijaFinanasiranja retVal = null;
		for (int i = 0; i < listaKategorijaFinanasiranja.size(); i++) {
			KategorijaFinanasiranja el = listaKategorijaFinanasiranja.get(i);
			if (el.getSifra().equals(sifra)) {
				retVal = el;
				break;
			}
		}
		return retVal;
	}
	
	public static void pisiUFajl(){
		try {
	    	String putanja = vratiRelativnuPutanjuDoFajla("kategorijeFinansiranja.txt");
	    	//provera da li postoji, kreiranje / brisanje datoteke
		    File obrisiKreirajDatoteka = new File(putanja);
		    if (!obrisiKreirajDatoteka.exists())
		    	obrisiKreirajDatoteka.createNewFile();

		    PrintWriter out = new PrintWriter(new FileWriter(putanja));

		    for(KategorijaFinanasiranja el : listaKategorijaFinanasiranja){
				out.println(el.toFile());
			}
		    
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
			 listaKategorijaFinanasiranja.clear();
		    	String putanja = vratiRelativnuPutanjuDoFajla("kategorijeFinansiranja.txt"); 
		    	//provera da li postoji, kreiranje / brisanje datoteke
			    File obrisiKreirajDatoteka = new File(putanja);
			    if (!obrisiKreirajDatoteka.exists()){
			    	System.out.println("Fajl sa podacima ne postoji");
			    	return;
			    }

			    BufferedReader in = new BufferedReader(new FileReader(putanja));
				String s;
				while ((s = in.readLine()) != null) {
					KategorijaFinanasiranja el = new KategorijaFinanasiranja(s);
					listaKategorijaFinanasiranja.add(el);
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

package Zad04Fakultet.DataClasses;

import java.util.ArrayList;
import java.util.Collections;


public class Utility {

	public static boolean isInteger(String s){
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isDouble(String s){
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static String vratiStringToFile(String arg){
		String retVal = (arg==null || arg.equals(""))?"null":arg;
		return retVal;
	}
	
	public static void sortOsobaJMBG(ArrayList lista, int pravac){
		Collections.sort(lista, new SorterOsobaJMBG(pravac));
	}
	
	public static void sortOsobaIme(ArrayList lista, int pravac){
		Collections.sort(lista, new SorterOsobaIme(pravac));
	}
	
	public static void sortOsobaPrezime(ArrayList lista, int pravac){
		Collections.sort(lista, new SorterOsobaPrezime(pravac));
	}
	
	public static void sortStudentIndex(ArrayList<Student> lista, int pravac){
		Collections.sort(lista, new SorterStudentIndex(pravac));
	}
	
	public static void sortStudentGodinaUpisa(ArrayList<Student> lista, int pravac){
		Collections.sort(lista, new SorterStudentGodinaUpisa(pravac));
	}
	
	public static void searchOsobaByStatus(ArrayList temp, boolean status){
		for (int i = temp.size()-1; i >= 0; i--) {
			if(temp.get(i) instanceof Osoba){
				Osoba el = (Osoba)temp.get(i);
				if(!el.isStatusZapisa()==status)
					temp.remove(i);
			}
		}
	}
	
	public static void searchOsobaByJMBG(ArrayList temp, String str){
		if(str==null || str.trim().equals(""))
			return;
		for (int i = temp.size()-1; i >= 0; i--) {
			if(temp.get(i) instanceof Osoba){
				Osoba el = (Osoba)temp.get(i);
				if(!el.getJMBG().equals(str))
					temp.remove(i);
			}
		}
	}
	
	public static void searchOsobaByImeIliPrezime(ArrayList temp, String str){
		if(str==null || str.trim().equals(""))
			return;
		for (int i = temp.size()-1; i >= 0; i--) {
			if(temp.get(i) instanceof Osoba){
				Osoba el = (Osoba)temp.get(i);
				if(!el.getIme().startsWith(str) && !el.getPrezime().startsWith(str))
					temp.remove(i);
			}
		}
	}
	
	public static void searchStudentByIndex(ArrayList temp, String str){
		if(str==null || str.trim().equals(""))
			return;
		for (int i = temp.size()-1; i >= 0; i--) {
			if(temp.get(i) instanceof Student){
				Student el = (Student)temp.get(i);
				if(!el.getIndex().equals(str))
					temp.remove(i);
			}
		}
	}
	
	public static void searchStudentByTipFinansiranja(ArrayList temp, String str){
		if(str==null || str.trim().equals(""))
			return;
		for (int i = temp.size()-1; i >= 0; i--) {
			if(temp.get(i) instanceof Student){
				Student el = (Student)temp.get(i);
				if(!el.getKatFin().getNaziv().startsWith(str))
					temp.remove(i);
			}
		}
	}
	
	private static void searchStudentByGodinaUpisaOd(ArrayList temp, String str){
		if(str==null || str.trim().equals("") || !Utility.isInteger(str))
			return;
		int int1 = Integer.parseInt(str);
		for (int i = temp.size()-1; i >= 0; i--) {
			if(temp.get(i) instanceof Student){
				Student el = (Student)temp.get(i);
				if(!(int1<=el.getGodinaUpisa()))
					temp.remove(i);
			}
		}
	}
	
	private static void searchStudentByGodinaUpisaDo(ArrayList temp, String str){
		if(str==null || str.trim().equals("") || !Utility.isInteger(str))
			return;
		int int1 = Integer.parseInt(str);
		for (int i = temp.size()-1; i >= 0; i--) {
			if(temp.get(i) instanceof Student){
				Student el = (Student)temp.get(i);
				if(!(int1>=el.getGodinaUpisa()))
					temp.remove(i);
			}
		}
	}
	
	public static void searchStudentByGodinaUpisaOdDo(ArrayList temp, String str1, String str2){
		searchStudentByGodinaUpisaOd(temp, str1);
		searchStudentByGodinaUpisaDo(temp, str2);
	}
}

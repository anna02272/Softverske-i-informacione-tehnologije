package Zad04Fakultet.DataClasses;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class SorterOsobaIme implements Comparator{

	int direction = 1;
	
	public SorterOsobaIme(int direction) {
		if(direction!=1 && direction!=-1){
			direction = 1;
		}
		this.direction = direction;
	}


	public int compare(Object o1, Object o2) {
		int retVal = 0;
		String st1 = null;
		String st2 =null;
		
		if(o1!= null && o1 instanceof Osoba){
			st1 = ((Osoba) o1).getIme();
		}
		if(o2!= null && o2 instanceof Osoba){
			st2 = ((Osoba) o2).getIme();
		}
		
		if(st1!=null && st2!=null){
			retVal = st1.compareTo(st2);
		}
		return retVal * direction;
	}
}

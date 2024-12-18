package Zad04Fakultet.DataClasses;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class SorterStudentGodinaUpisa implements Comparator{

	int direction = 1;
	
	public SorterStudentGodinaUpisa(int direction) {
		if(direction!=1 && direction!=-1){
			direction = 1;
		}
		this.direction = direction;
	}


	public int compare(Object o1, Object o2) {
		int retVal = 0;
		Integer int1 = 0;
		Integer int2 = 0;
		
		if(o1!= null && o2!= null && o1 instanceof Student  && o2 instanceof Student){
			int1 = ((Student) o1).getGodinaUpisa();
			int2 = ((Student) o2).getGodinaUpisa();
		}
		if(int1!=null && int2!=null){
			retVal = int1.compareTo(int2);
		}
		return retVal * direction;
	}
}

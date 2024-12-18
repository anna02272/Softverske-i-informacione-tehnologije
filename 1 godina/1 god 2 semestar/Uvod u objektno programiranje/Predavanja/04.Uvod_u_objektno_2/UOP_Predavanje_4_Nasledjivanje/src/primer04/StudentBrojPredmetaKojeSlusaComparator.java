package primer04;

import java.util.Comparator;

public class StudentBrojPredmetaKojeSlusaComparator implements Comparator<Student>{

	int direction = 1;
	
	public StudentBrojPredmetaKojeSlusaComparator(int direction) {
		if(direction!=1 && direction!=-1){
			direction = 1;
		}
		this.direction = direction;
	}

	@Override
	public int compare(Student ob1, Student ob2) {
		int retVal = 0;
		if(ob1!= null && ob2!=null){
			if(ob1.izracunajProsek()>ob2.izracunajProsek())
				return 1;
			else if (ob1.izracunajProsek()<ob2.izracunajProsek())
				return -1;
			else
				return 0;
		}
		return retVal * direction;
	}
}
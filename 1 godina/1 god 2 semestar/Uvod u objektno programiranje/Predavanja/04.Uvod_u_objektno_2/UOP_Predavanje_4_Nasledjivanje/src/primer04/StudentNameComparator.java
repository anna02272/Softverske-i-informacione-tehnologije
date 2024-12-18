package primer04;

import java.util.Comparator;

public class StudentNameComparator implements Comparator<Student>{

	int direction = 1;
	
	public StudentNameComparator(int direction) {
		if(direction!=1 && direction!=-1){
			direction = 1;
		}
		this.direction = direction;
	}

	@Override
	public int compare(Student ob1, Student ob2) {
		int retVal = 0;
		if(ob1!= null && ob2!=null){
			retVal = ob1.getIme().compareTo(ob2.getIme());
		}
		return retVal * direction;
	}
}
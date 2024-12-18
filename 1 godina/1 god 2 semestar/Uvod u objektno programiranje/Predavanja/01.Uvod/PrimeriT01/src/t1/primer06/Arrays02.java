package t1.primer06;

public class Arrays02 {

	public static void main(String[] args) {
		int [] A = { 1 , 2 , 3 , 4 ,  5};	//deklaracija, alokacija i inicijalizacija
		
		//prikazi sumu niza A
		int proizvod = 1;
		for (int i = 0; i < A.length; i++){
			proizvod *= A[i]; // suma = suma + niz[i];
		}
		
		System.out.println("Proizvod niza A je:" + proizvod + "\n");
		
		//TODO Pronaci maksimalni element niza 10, 4, 6, 11, 15, 2
	}
}

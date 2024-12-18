package t1.primer06;

public class Arrays03 {

	public static void main(String[] args) {
		
		System.out.println(args[0] + " "+args[1]);
		
		//definisanje visedimenzionalnih nizova
		int [] [] C  = new int [2] [2];
		
		// popunjavanje vrednosti matrice
		/*
		 * 0 1 2
		 * 1 2 3
		 * 2 3 4
		 * 3 4 5
		 */
		for (int i = 0; i < C.length ; i++ )
			for (int j = 0; j < C[i].length ; j++ )
				C[i][j] = i+j;
		
		//ispis clanova matrice C
		System.out.println("Ispis matrice C");
		for (int i = 0; i < C.length ; i++ ) {
			for (int j = 0; j < C[i].length ; j++ ){
				System.out.print("\tC [" + i +"][" + j + "] = " + C[i][j]);
			}
			System.out.println();
		}	

		System.out.println("\nRad sa studentske slu\u017Ebe");
		
		String [] studenti = { "Pera", "Mika", "Laza" };
		
		//skraceni oblik, koje su dimenzije niza?
		int [] [] oceneStudenta  = { 
				{ 10, 7 , 8} , 
				{ 6 } , 
				{ 7, 10, 6, 8, 10} 
		};
		
		for (int i = 0; i < oceneStudenta.length; i++) {
			System.out.println("Student " +studenti[i] + " ima ocene ");
			for (int j = 0; j < oceneStudenta[i].length; j++) {
				System.out.print("\t"+oceneStudenta[i][j]);
			}
			System.out.println("\n");
		}
	}
}

package primer00;



public class Test {

	public static void main(String[] args) {
		
		StatickeAtributiIMetode.indexCounter = 25;
		System.out.println("AAA");
		//pozivanje konstruktora bez parametra
		StatickeAtributiIMetode ob1 = new StatickeAtributiIMetode();
//		StatickeAtributiIMetode ob2 = new StatickeAtributiIMetode();

		///////////// rad sa static atributima
		//moze i preko objekta, ali eclipse daje warning
		StatickeAtributiIMetode.indexCounter = 25;
		System.out.println(ob1.indexCounter);
		
		//ne mora postojati objekat da bi se pristupilo static atributu
		StatickeAtributiIMetode.indexCounter = 10;
		System.out.println(ob1.indexCounter);
		
		//staticka metoda
		StatickeAtributiIMetode.statickaMetoda();
		
		//static final atribut se moze procitati, ali se ne moze menjati
		System.out.println(StatickeAtributiIMetode.INDEX_LETTER);
//		StatickeAtributiIMetode.INDEX_LETTER = "a";
		
		//slicna je situacija i sa static metodama
		System.out.println("Zavrsen rad sa static atributima i metodama");
		System.out.println("-------------------------------------------");
	}
	
}

package primer05;

public class Test {
	public class Vozilo {
		public void pokreni(){
			System.out.println("Vozilo je upaljeno");
		}
	}
	public class Automobil extends Vozilo {
		@Override
		public void pokreni(){
			System.out.println("Automobil je upaljen");
		}
	}
	void ispisNesto(int a){
		System.out.println("Ispis parametar tipa int");
	}
	void ispisNesto(double d){
		System.out.println("Ispis parametar tipa double");
	}
	void ispisNesto(Vozilo v){
		System.out.println("Ispis parametar Vozilo");
	}
	void ispisNesto(Automobil a){
		System.out.println("Ispis parametar Automobil");
	}
	public void staticBindingTest() {
		System.out.println("Poziv konketne metode zavisi od tipa parametra");
		ispisNesto(4); //Ispis parametar tipa int
		ispisNesto(5.0); //Ispis parametar tipa double
		ispisNesto(new Vozilo()); //Ispis parametar tipa Vozilo
		ispisNesto(new Automobil());//Ispis parametar tipa Automobil
		Vozilo v = new Automobil();
		ispisNesto(v);//Ispis parametar tipa Vozilo
	}
	public void dynamicBindingTest() {
		System.out.println("Poziv konketne metode zavisi od objekta koji poziva metodu");
		Vozilo v = new Automobil();
		v.pokreni(); //Automobil je upaljen
	}
	public static void main(String[] args) {
		Test t = new Test();
		System.out.println("------------------------------");
		t.staticBindingTest();
		System.out.println("------------------------------");
		t.dynamicBindingTest();
	}
}

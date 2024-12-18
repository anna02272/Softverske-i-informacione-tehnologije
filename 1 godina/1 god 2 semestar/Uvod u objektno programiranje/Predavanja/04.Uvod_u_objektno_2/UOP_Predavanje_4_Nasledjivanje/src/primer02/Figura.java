package primer02;

public abstract class Figura {

	protected double obim;
	protected double povrsina;
	
	public abstract double izracunajPovrsinu();
	public abstract double izracunajObim();
	
	@Override
	public String toString() {
		String ispis = "Figura ima obim " + izracunajObim() + " i povrsinu " 
				+ izracunajPovrsinu();
		return ispis;
	}
}

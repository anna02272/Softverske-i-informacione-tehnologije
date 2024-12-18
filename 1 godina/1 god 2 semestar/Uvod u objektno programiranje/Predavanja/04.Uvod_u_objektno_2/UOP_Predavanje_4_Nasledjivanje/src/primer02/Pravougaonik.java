package primer02;

import primer01.Tacka2D;

public class Pravougaonik extends Figura {

	protected Tacka2D A,B;
	
	public Pravougaonik(Tacka2D a, Tacka2D b) {
		A = a;
		B = b;
	}

	@Override
	public String toString() {
		String ispis = "Figura pravougaonik tacka A=" + A.getX()+","+ A.getY() 
			+ " i B=" + B.getX()+","+ B.getY() 
			+ " ima obim " + izracunajObim() + " i povrsinu " + izracunajPovrsinu();
		return ispis;
	}
	
	@Override
	public double izracunajPovrsinu() {
		double stranicaA = Math.abs(A.getX()-B.getX());
		double stranicaB = Math.abs(A.getY()-B.getY());
		povrsina = stranicaA*stranicaB;
		return povrsina;
	}

	@Override
	public double izracunajObim() {
		double stranicaA = Math.abs(A.getX()-B.getX());
		double stranicaB = Math.abs(A.getY()-B.getY());
		obim = 2*stranicaA+2*stranicaB;
		return obim;
	}

	public Tacka2D getA() {
		return A;
	}

	public void setA(Tacka2D a) {
		A = a;
	}

	public Tacka2D getB() {
		return B;
	}

	public void setB(Tacka2D b) {
		B = b;
	}
}

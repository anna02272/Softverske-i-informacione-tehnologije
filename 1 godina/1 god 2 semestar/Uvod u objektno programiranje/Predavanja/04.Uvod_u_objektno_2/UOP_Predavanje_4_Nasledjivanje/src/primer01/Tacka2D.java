package primer01;

public class Tacka2D {

	//kod nasledjivanja bitno je kako se pravima pristupa 
	//definisu atributi i metode osnovne klase
	protected double x;
	protected double y;
	
//	public Tacka2D(){}
	
	public Tacka2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void ispisNaEkran(){
		System.out.println("Ispis koordinata tacke, x osa " 
				+ x + " y osa" + y);
	}
	

	@Override
	public String toString() {
		return "Ispis koordinata tacke, x osa " 
				+ x + " y osa" + y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}

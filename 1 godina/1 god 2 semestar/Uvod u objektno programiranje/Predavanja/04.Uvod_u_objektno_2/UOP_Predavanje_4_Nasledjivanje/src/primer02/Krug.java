package primer02;

import primer01.Tacka2D;

public class Krug extends Figura{

	protected Tacka2D centar;
	protected double radius;
	
	public Krug(Tacka2D centar, double radius) {
		super();
		this.centar = centar;
		this.radius = radius;
	}
	
	//odredjuje da li tacka A pripada krugu
	public boolean tackaPripadaKrugu(double xT, double yT){
		double rastojanje = Math.sqrt(Math.pow(xT - centar.getX(), 2) 
				+ Math.pow(yT - centar.getY(),2));
		boolean pripada = false;
		if (rastojanje <= radius ) {
			pripada = true;
		}
		return pripada;
	}
	
	@Override
	public double izracunajPovrsinu(){
		povrsina = Math.pow(radius, 2)*Math.PI;
		return povrsina;
	}
	
	@Override
	public double izracunajObim(){
		obim = 2 * radius * Math.PI;
		return obim;
	}
	
	@Override
	public String toString() {
		String ispis = "Figura Krug ciji je centar=" + centar.getX()+","+ centar.getY() 
			+ " i radius="+radius+ " ima obim " + izracunajObim() + " i povrsinu " 
				+ izracunajPovrsinu();
		return ispis;
	}

	public Tacka2D getCentar() {
		return centar;
	}

	public void setCentar(Tacka2D centar) {
		this.centar = centar;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}

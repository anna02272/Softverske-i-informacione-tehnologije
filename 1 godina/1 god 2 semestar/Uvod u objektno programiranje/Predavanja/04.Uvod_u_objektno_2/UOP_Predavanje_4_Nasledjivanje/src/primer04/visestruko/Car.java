package primer04.visestruko;

public class Car implements Vehicle, FourWheeler {

	public void print() {
		System.out.println("I am a four wheeler car vehicle!");
	}

	public static void main(String[] args) {
		Car car = new Car();
		car.print();
	}
}

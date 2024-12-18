package vezba1.entities;

public class Manifestation {
	
	private String name;
	private String place;
	private int capacity;
	private double ticketPrice;
	
	public Manifestation() {}

	public Manifestation(String name, String place, int capacity, double ticketPrice) {
		super();
		this.name = name;
		this.place = place;
		this.capacity = capacity;
		this.ticketPrice = ticketPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
}

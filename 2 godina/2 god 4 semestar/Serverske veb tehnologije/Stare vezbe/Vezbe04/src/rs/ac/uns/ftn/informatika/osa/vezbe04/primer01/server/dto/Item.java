package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {

	private static final long serialVersionUID = -3115544041316085130L;
	
	private int number;
	private String name;
	private BigDecimal price;
	
	public Item() {
		
	}

	public Item(int number, String name, BigDecimal price) {
		this.number = number;
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}

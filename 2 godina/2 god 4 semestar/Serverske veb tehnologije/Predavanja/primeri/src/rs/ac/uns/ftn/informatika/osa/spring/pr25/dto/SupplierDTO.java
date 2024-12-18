package rs.ac.uns.ftn.informatika.osa.spring.pr25.dto;

import java.io.Serializable;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.Supplier;

public class SupplierDTO implements Serializable {

	private Integer id;
	
	private String name;
	
	private String address;
	
	/**
	 * 
	 */
	public SupplierDTO() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param address
	 */
	public SupplierDTO(Integer id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public SupplierDTO(Supplier supplier){
		this(supplier.getId(), supplier.getName(), supplier.getAddress());
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

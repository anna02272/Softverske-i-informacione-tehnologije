package rs.ac.uns.ftn.informatika.osa.spring.pr25.dto;

import java.io.Serializable;
import java.util.Date;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.PurchaseOrder;

public class PurchaseOrderDTO implements Serializable {
	
	private Integer id;
	
	private Date date;
	
	private UserDTO user;
	
	/**
	 * 
	 */
	public PurchaseOrderDTO() {
		super();
	}

	/**
	 * @param id
	 * @param date
	 * @param user
	 */
	public PurchaseOrderDTO(Integer id, Date date, UserDTO user) {
		super();
		this.id = id;
		this.date = date;
		this.user = user;
	}
	
	public PurchaseOrderDTO(PurchaseOrder purchaseOrder){
		this(purchaseOrder.getId(), purchaseOrder.getDate(), new UserDTO(purchaseOrder.getUser()));
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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	
}

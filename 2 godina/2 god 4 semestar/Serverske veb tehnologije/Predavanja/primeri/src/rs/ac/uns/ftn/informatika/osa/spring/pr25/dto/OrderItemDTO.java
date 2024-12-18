package rs.ac.uns.ftn.informatika.osa.spring.pr25.dto;

import java.io.Serializable;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.OrderItem;

public class OrderItemDTO implements Serializable {

	private Integer id;
	
	private int quantity;
	
	private ProductDTO product;
	
	/**
	 * 
	 */
	public OrderItemDTO() {
		super();
	}

	/**
	 * @param id
	 * @param quantity
	 * @param product
	 */
	public OrderItemDTO(Integer id, int quantity, ProductDTO product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
	}

	public OrderItemDTO(OrderItem orderItem){
		this(orderItem.getId(), orderItem.getQuantity(), new ProductDTO(orderItem.getProduct()));
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
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the product
	 */
	public ProductDTO getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	
	
	
}

package rs.ac.uns.ftn.informatika.osa.spring.pr25.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.Product;

public class ProductDTO implements Serializable {

	private Integer id;
	
	private String name;
	
	private String vendor;
	
	private String description;
	
	private BigDecimal price;
	
	private CategoryDTO category;
	
	private SupplierDTO supplier;
	
	/**
	 * 
	 */
	public ProductDTO() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param vendor
	 * @param description
	 * @param price
	 * @param category
	 * @param supplier
	 */
	public ProductDTO(Integer id, String name, String vendor,
			String description, BigDecimal price, CategoryDTO category,
			SupplierDTO supplier) {
		super();
		this.id = id;
		this.name = name;
		this.vendor = vendor;
		this.description = description;
		this.price = price;
		this.category = category;
		this.supplier = supplier;
	}

	public ProductDTO(Product product){
		this(product.getId(), product.getName(), product.getVendor(),
				product.getDescription(), product.getPrice(), new CategoryDTO(product.getCategory()),
				new SupplierDTO(product.getSupplier()));
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
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the category
	 */
	public CategoryDTO getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	/**
	 * @return the supplier
	 */
	public SupplierDTO getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(SupplierDTO supplier) {
		this.supplier = supplier;
	}
	
	
}

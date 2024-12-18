package rs.ac.uns.ftn.informatika.osa.spring.pr25.dto;

import java.io.Serializable;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.Category;

public class CategoryDTO implements Serializable{

	private Integer id;
	
	private String name;
	
	private String description;
	
	private CategoryDTO parent;
	
	/**
	 * 
	 */
	public CategoryDTO() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param parent
	 */
	public CategoryDTO(Integer id, String name, String description, CategoryDTO parent) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.parent = parent;
	}
	
	public CategoryDTO(Category category){
		this(category.getId(), category.getName(), category.getDescription(), (category.getParent() != null && category.getParent().getId() != null)?new CategoryDTO(category.getParent()):new CategoryDTO());
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

	public CategoryDTO getParent() {
		return parent;
	}

	public void setParent(CategoryDTO parent) {
		this.parent = parent;
	}
	
	
	
	
}

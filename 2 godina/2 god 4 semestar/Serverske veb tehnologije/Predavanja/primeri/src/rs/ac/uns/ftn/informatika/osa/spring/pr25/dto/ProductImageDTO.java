package rs.ac.uns.ftn.informatika.osa.spring.pr25.dto;

import java.io.Serializable;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.ProductImage;

public class ProductImageDTO implements Serializable {
	
	private Integer id;
	
	private String title;
	
	private int width;
	
	private int height;
	
	private String contentType;
	
	private byte[] imageDate;
	
	/**
	 * 
	 */
	public ProductImageDTO() {
		super();
	}
	

	/**
	 * @param id
	 * @param title
	 * @param width
	 * @param height
	 * @param contentType
	 * @param imageDate
	 */
	public ProductImageDTO(Integer id, String title, int width, int height,
			String contentType, byte[] imageDate) {
		super();
		this.id = id;
		this.title = title;
		this.width = width;
		this.height = height;
		this.contentType = contentType;
		this.imageDate = imageDate;
	}

	public ProductImageDTO(ProductImage productImage){
		this(productImage.getId(), productImage.getTitle(), productImage.getWidth(),
				productImage.getHeight(), productImage.getContentType(), productImage.getImageData());
		
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the imageDate
	 */
	public byte[] getImageDate() {
		return imageDate;
	}

	/**
	 * @param imageDate the imageDate to set
	 */
	public void setImageDate(byte[] imageDate) {
		this.imageDate = imageDate;
	}
	
	
	
}

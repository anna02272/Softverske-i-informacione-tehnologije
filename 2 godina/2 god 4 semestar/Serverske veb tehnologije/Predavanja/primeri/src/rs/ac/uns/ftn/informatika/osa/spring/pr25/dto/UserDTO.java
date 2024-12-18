package rs.ac.uns.ftn.informatika.osa.spring.pr25.dto;

import java.io.Serializable;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.User;

public class UserDTO implements Serializable {
	
	private Integer id;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String email;
	
	private boolean receiveNews;

	
	
	/**
	 * 
	 */
	public UserDTO() {
		super();
	}
	

	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param email
	 * @param receiveNews
	 */
	public UserDTO(Integer id, String username, String password,
			String firstName, String lastName, String address, String email,
			boolean receiveNews) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.receiveNews = receiveNews;
	}

	public UserDTO(User user){
		this(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), 
				user.getLastName(), user.getAddress(), user.getEmail(), user.isReceiveNews());
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the receiveNews
	 */
	public boolean isReceiveNews() {
		return receiveNews;
	}

	/**
	 * @param receiveNews the receiveNews to set
	 */
	public void setReceiveNews(boolean receiveNews) {
		this.receiveNews = receiveNews;
	}
	
	

}

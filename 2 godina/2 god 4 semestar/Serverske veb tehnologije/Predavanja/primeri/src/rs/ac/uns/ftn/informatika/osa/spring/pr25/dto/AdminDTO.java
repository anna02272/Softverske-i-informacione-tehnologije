package rs.ac.uns.ftn.informatika.osa.spring.pr25.dto;

import java.io.Serializable;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.Admin;

public class AdminDTO implements Serializable {

	private Integer id;
	
	private String username;
	
	private String password;

	/**
	 * 
	 */
	public AdminDTO() {
		super();
	}

	/**
	 * @param id
	 * @param username
	 * @param password
	 */
	public AdminDTO(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public AdminDTO(Admin admin){
		this(admin.getId(), admin.getUsername(), admin.getPassword());
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
	
	
}

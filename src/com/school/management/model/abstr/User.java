package com.school.management.model.abstr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 5, max = 10)
	@Pattern(regexp = "^\\w{5,10}$")
	private String username;
	
	@Transient
	@NotBlank
	@Size(min = 5, max = 10)
	@Pattern(regexp = "^\\w{5,10}$")
	private String rawPassword;
	
	@Column(name="password")
	private String encodedPassword;
	
	private boolean enabled;
	private String authority;
	
	@NotBlank
	@Size(min = 3, max = 20)
	@Pattern(regexp = "^[A-Z][a-z]{4,20}$")
	private String firstName;
	
	@NotBlank
	@Size(min = 3, max = 20)
	@Pattern(regexp = "^[A-Z][a-z]{4,20}$")
	private String lastName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRawPassword() {
		return rawPassword;
	}
	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}
	public String getEncodedPassword() {
		return encodedPassword;
	}
	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

package com.laboratorysystem.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name= "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{4,29}$", message = "User Name is Invalid")
	private String userName;
	
	@Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Enter a valid password")
	private String password;
	
	@NotNull
	@Email(message = "Email format is Invalid")
	private String emailId;
	
	public User() {
		super();
	}

//	public User(long id, String userName, String password, String emailId) {
//		super();
//		this.id = id;
//		this.userName = userName;
//		this.password = password;
//		this.emailId = emailId;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", emailId=" + emailId + "]";
//	}
}

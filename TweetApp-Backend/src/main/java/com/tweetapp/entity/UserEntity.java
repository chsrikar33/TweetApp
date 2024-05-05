package com.tweetapp.entity;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {
	@Id
	private String email;
	private String lastName;
	private String gender;
	private LocalDate date;
	private String firstName;
	private String password;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	public UserEntity() {
		super();
	}

	public UserEntity(String email, String lastName, String gender, LocalDate date, String firstName,
			String password) {
		super();
		this.email = email;
		this.lastName = lastName;
		this.gender = gender;
		this.date = date;
		this.firstName = firstName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserEntity [email=" + email + ", lastName=" + lastName + ", gender=" + gender + ", date=" + date
				+ ", firstName=" + firstName + ", password=" + password + "]";
	}
	
	



}

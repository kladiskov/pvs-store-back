package com.pvstechlabs.app.data.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "USER_EMAIL")
	private String emailId;

	@Column(name = "USER_MOBILE")
	private String phoneNumber;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Credential credential;
	
	public User() {
		
	}

	private User(UserBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.emailId = builder.emailId;
		this.phoneNumber = builder.phoneNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public static class UserBuilder {
		private String firstName;
		private String lastName;
		private String emailId;
		private String phoneNumber;

		public UserBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public UserBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public UserBuilder setEmailId(String emailId) {
			this.emailId = emailId;
			return this;
		}

		public UserBuilder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public User build() {
			return new User(this);
		}

	}

}

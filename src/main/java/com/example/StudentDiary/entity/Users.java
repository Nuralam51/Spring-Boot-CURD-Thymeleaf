package com.example.StudentDiary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty(message="Username is required")
	@Column(name="username")
	private String username;
	
	@NotEmpty(message="Password is required")
	@Column(name="password")
	private String password;
	
	@NotEmpty(message="Email is required")
	@Email(message="Please enter valid email address")
	@Column(name="email")
	private String email;
	
	@Column(name="roles")
	private String roles = "ROLE_USER";
	
	@Column(name="enabled")
	private String enabled = "1";
	
	public Users() {
		
	}

	public Users(int id, @NotEmpty(message = "Username is required") String username,
			@NotEmpty(message = "Password is required") String password,
			@NotEmpty(message = "Email is required") @Email(message = "Please enter valid email address") String email,
			String roles, String enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + ", email=" + email + ", roles=" + roles
				+ ", enabled=" + enabled + "]";
	}
	
}

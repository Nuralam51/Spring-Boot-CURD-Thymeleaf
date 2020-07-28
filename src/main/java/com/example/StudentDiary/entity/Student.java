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
@Table(name="student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	
	@NotEmpty(message = "First name is required")
	@Column(name="first_Name")
	public String firstName;

	@NotEmpty(message = "Last name is required")
	@Column(name="last_Name")
	public String lastName;

	@NotEmpty(message = "Email is required")
	@Email(message="Please enter the valid email address")
	@Column(name="email")
	public String email;

	@NotEmpty(message = "ID number is required")
	@Column(name="roll")
	public String roll;
	
	public Student() {
		
	}

	public Student(int id, String firstName, String lastName, String email, String roll) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roll = roll;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", roll=" + roll + "]";
	}
	
}

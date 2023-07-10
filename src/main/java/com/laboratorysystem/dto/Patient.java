package com.laboratorysystem.dto;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Size(min=2, max=20, message="Name should be Valid")
	//@Pattern(regexp="^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$",message = "Invalid Name")
	private String patientName;
	
	@NotNull(message = "Date is Required")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Past(message = "Select a Valid Date")
	private LocalDate dob;
	
	@NotBlank(message = "Gender is Required")
	@Pattern(regexp = "Male|Female", message = "Either Male or Female is allowed")
	private String gender;
	
	@NotNull
	@Pattern(regexp = "^\\d{10}$", message = "Only numbers are allowed in Mobile number")
	@Size(min = 10, max = 10, message = "Mobile number shlud have 10 numbers")
	private String mobileNo;
	
	@NotNull
	@Email(message = "Email format is Invalid")
	private String emailId;
	
	@NotNull
	private String address;
	
	@NotNull
	@Min(value = 18, message = "Minimum age should be 18")
	@Max(value = 60, message = "Maximum age should be 60")
	private int age;
	
//	@ManyToOne(cascade = {CascadeType.ALL})
//	private long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "doctor_id", referencedColumnName = "id")
	private Doctor doctor;
	
	public Patient() {
		super();
	}

	public Patient(long id, String patientName, LocalDate dob, String gender, String mobileNo, String emailId,
			String address, int age, Doctor doctor) {
		super();
		this.id = id;
		this.patientName = patientName;
		this.dob = dob;
		this.gender = gender;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.address = address;
		this.age = age;
		this.doctor = doctor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

//	@Override
//	public String toString() {
//		return "Patient [id=" + id + ", patientName=" + patientName + ", dob=" + dob + ", gender=" + gender
//				+ ", mobileNo=" + mobileNo + ", emailId=" + emailId + ", address=" + address + ", age=" + age + "]";
//	}
}

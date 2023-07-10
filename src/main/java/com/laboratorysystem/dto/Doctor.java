package com.laboratorysystem.dto;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Size(min=2, max=20, message="Name should be Valid")
	//@Pattern(regexp="^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$",message = "Invalid Name")
	private String doctorName;
	
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
	
	private String address;

	
	@NotNull
	@Min(value = 18, message = "Minimum age should be 18")
	@Max(value = 60, message = "Maximum age should be 60")
	private int age;
	
	private String hospitalName;
	private String specialization;
	
	public Doctor() {
		super();
	}
	
	public Doctor( long id, String doctorName, LocalDate dob, String gender, String mobileNo, String emailId,
			String address, int age, String hospitalName, String specialization) {
		super();
		this.id = id;
		this.doctorName = doctorName;
		this.dob = dob;
		this.gender = gender;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.address = address;
		this.age = age;
		this.hospitalName = hospitalName;
		this.specialization = specialization;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
//	
//	@Override
//	public String toString() {
//		return "Doctor [id=" + id + ", doctorName=" + doctorName + ", dob=" + dob + ", gender=" + gender + ", mobileNo="
//				+ mobileNo + ", emailId=" + emailId + ", address=" + address + ", age=" + age + ", hospitalName="
//				+ hospitalName + ", specialization=" + specialization + "]";
//	}
	
}

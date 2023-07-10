package com.laboratorysystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.laboratorysystem.dao.PatientRepository;
import com.laboratorysystem.dto.Doctor;
import com.laboratorysystem.dto.Patient;
import com.laboratorysystem.exceptions.ResourceNotFoundException;

@SpringBootTest
class PatientServiceTest {

	@Autowired
	PatientService patientService;
	
	@MockBean
	PatientRepository patientRepo;
	
	@Test
	void testGetPatient() {

		Patient p1 = new Patient();
		p1.setId(1);
		p1.setPatientName("Rushikesh"); 
		p1.setDob(LocalDate.of(1999, 9, 7));
		p1.setGender("Male");
		p1.setMobileNo("8482927915");
		p1.setEmailId("rushidhamanage.7@gmail.com");
		p1.setAddress("Sangli");
		p1.setAge(23);
		
		Patient p2 = new Patient();
		p2.setId(2);
		p2.setPatientName("Vaibhav");
		p2.setDob(LocalDate.of(2000, 12, 11));
		p2.setGender("Male");
		p2.setMobileNo("8208428378");
		p2.setEmailId("vaibhavdhamanage@gmail.com");
		p2.setAge(21);
		
		List<Patient> patientList = new ArrayList<>();
		patientList.add(p1);
		patientList.add(p2);
		
		Mockito.when(patientRepo.findAll()).thenReturn(patientList);
		
		assertThat(patientService.getPatient()).isEqualTo(patientList);
	}
	
	@Test
	public void testAddPatient() throws ResourceNotFoundException
	{
		Patient p1 = new Patient();
		p1.setId(1);
		p1.setPatientName("Rushikesh");
		p1.setDob(LocalDate.of(1999, 9, 7));
		p1.setGender("Male");
		p1.setMobileNo("8482927915");
		p1.setEmailId("rushidhamanage.7@gmail.com");
		p1.setAddress("Sangli");
		p1.setAge(23);
		
		when(patientRepo.save(any(Patient.class))).thenReturn(p1);
		assertNotNull(patientService.addPatient(p1));
	}
	
	
	@Test
	void testDuplicateAddedPatient() throws ResourceNotFoundException{
		
		Patient p1 = new Patient();
		p1.setId(1);
		p1.setPatientName("Rushikesh");
		p1.setDob(LocalDate.of(1999, 9, 7));
		p1.setGender("Male");
		p1.setMobileNo("8482927915");
		p1.setEmailId("rushidhamanage.7@gmail.com");
		p1.setAddress("Sangli");
		p1.setAge(23);
		
		Optional<Patient> p2 = Optional.of(new Patient());
		Mockito.when(patientRepo.findByEmailId(p1.getEmailId())).thenReturn(p2);
		Mockito.when(patientRepo.save(p1)).thenReturn(p1);
		assertThat(patientService.addPatient(p1)).hasToString("Patient Already Exists with this EmailID!");
	}
	
	@Test
	void testGetPatientByName() throws ResourceNotFoundException{
		
		Patient p1 = new Patient();
		p1.setId(1);
		p1.setPatientName("Rushikesh");
		p1.setDob(LocalDate.of(1999, 9, 7));
		p1.setGender("Male");
		p1.setMobileNo("8482927915");
		p1.setEmailId("rushidhamanage.7@gmail.com");
		p1.setAddress("Sangli");
		p1.setAge(23);
		
		List<Patient> p2 = new ArrayList<>();
		p2.add(p1);
		
		Mockito.when(patientRepo.findByPatientName("Rushikesh")).thenReturn(p2);
		assertThat(patientService.getPatientByName("Rushikesh")).isEqualTo(p2);
	}
	
	@Test
	void testUpdatePatient() throws ResourceNotFoundException {

		Patient p1 = new Patient();
		p1.setId(1);
		p1.setPatientName("Rushikesh");
		p1.setDob(LocalDate.of(1999, 9, 7));
		p1.setGender("Male");
		p1.setMobileNo("8482927915");
		p1.setEmailId("rushidhamanage.7@gmail.com");
		p1.setAddress("Sangli");
		p1.setAge(23);
		p1.setDoctor(new Doctor());
		
		Optional<Patient> p2 = Optional.of(p1);
		
		Mockito.when(patientRepo.findById((long) 1)).thenReturn(p2);
		
		Mockito.when(patientRepo.save(p1)).thenReturn(p1);
		p1.setPatientName("Rushikesh");
		p1.setMobileNo("8482927915");
		
		assertThat(patientService.updatePatient(p1)).isEqualTo(p1);
	}
	
	@Test
	void testGetPatientById() throws ResourceNotFoundException {

		Patient p1 = new Patient();
		p1.setId(1);
		p1.setPatientName("Rushikesh");
		p1.setDob(LocalDate.of(1999, 9, 7));
		p1.setGender("Male");
		p1.setMobileNo("8482927915");
		p1.setEmailId("rushidhamanage.7@gmail.com");
		p1.setAddress("Sangli");
		p1.setAge(23);
		
		Optional<Patient> p2=Optional.of(p1);
		
		Mockito.when(patientRepo.findById((long) 1)).thenReturn(p2);
		
		assertThat(patientService.getPatientById(1)).isEqualTo(p1);
		
	}
	
	@Test
	void testDeletePatientById() {

		Patient p1 = new Patient();
		p1.setId(1);
		p1.setPatientName("Rushikesh");
		p1.setDob(LocalDate.of(1999, 9, 7));
		p1.setGender("Male");
		p1.setMobileNo("8482927915");
		p1.setEmailId("rushidhamanage.7@gmail.com");
		p1.setAddress("Sangli");
		p1.setAge(23);
		
		Optional<Patient> p2=Optional.of(p1);
		
		Mockito.when(patientRepo.findById((long) 1)).thenReturn(p2);
		Mockito.when(patientRepo.existsById(p1.getId())).thenReturn(false);
		   assertFalse(patientRepo.existsById(p1.getId()));
	}

}

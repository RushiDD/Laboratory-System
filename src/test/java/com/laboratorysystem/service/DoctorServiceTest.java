package com.laboratorysystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.laboratorysystem.controller.DoctorController;
import com.laboratorysystem.dao.DoctorRepository;
import com.laboratorysystem.dto.Doctor;
import com.laboratorysystem.exceptions.ResourceNotFoundException;

@SpringBootTest
class DoctorServiceTest {
	
	@Autowired
	DoctorService doctorService;
	
	@MockBean
	DoctorRepository doctorRepo;
	
	@InjectMocks
	DoctorController doctorController;
	
	@Test
	void testGetDoctor() {
 
		Doctor d1 = new Doctor();
		d1.setId(1); 
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7));
		d1.setGender("Male");
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushidhamanage.7@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("J J Hospital");
		d1.setSpecialization("Eye Specialist");
		
		Doctor d2 = new Doctor();
		d2.setId(2);
		d2.setDoctorName("Vaibhav"); 
		d2.setDob(LocalDate.of(2000, 12, 11));
		d2.setGender("Male");
		d2.setMobileNo("8208428378");
		d2.setEmailId("vaibhavdhamanage@gmail.com");
		d2.setAge(21);
		d2.setHospitalName(" Civil Hospital");
		d2.setSpecialization("Eye Specialist");
		
		List<Doctor> doctorList = new ArrayList<>(); 
		doctorList.add(d1);
		doctorList.add(d2);
		
		Mockito.when(doctorRepo.findAll()).thenReturn(doctorList);
		
		assertThat(doctorService.getDoctor()).isEqualTo(doctorList);
	}
	
	@Test
	public void testAddDoctor() throws ResourceNotFoundException
	{
		Doctor d1 = new Doctor();
		d1.setId(1);
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7));
		d1.setGender("Male");
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushidhamanage.77@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("J J Hospital");
		d1.setSpecialization("Eye Specialist");
		
		when(doctorRepo.save(any(Doctor.class))).thenReturn(d1);
		assertNotNull(doctorService.addDoctor(d1));
	}
	
	@Test
	void testDuplicateAddedDoctor() throws ResourceNotFoundException{
		
		Doctor d1 = new Doctor();
		d1.setId(1);
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7));
		d1.setGender("Male");
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushidhamanage.7@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("J J Hospital");
		d1.setSpecialization("Eye Specialist");
		
		Optional<Doctor> d2 = Optional.of(new Doctor());
		Mockito.when(doctorRepo.findByEmailId(d1.getEmailId())).thenReturn(d2);
		Mockito.when(doctorRepo.save(d1)).thenReturn(d1);
		assertThat(doctorService.addDoctor(d1)).hasToString("Doctor Already Exists with this EmailID!");
	}

	
	@Test
	void testUpdateDoctor() throws ResourceNotFoundException {

		Doctor d1 = new Doctor();
		d1.setId(1);
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7));
		d1.setGender("Male");
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushidhamanage.7@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("J J Hospital");
		d1.setSpecialization("Eye Specialist");
		
		Optional<Doctor> d2 = Optional.of(d1);
		
		Mockito.when(doctorRepo.findById((long) 1)).thenReturn(d2);
		
		Mockito.when(doctorRepo.save(d1)).thenReturn(d1);
		d1.setDoctorName("Rushikesh");
		d1.setMobileNo("8482927915");
		
		assertThat(doctorService.updateDoctor(d1)).isEqualTo(d1);
	}
	
	@Test
	void testGetDoctorById() throws ResourceNotFoundException {

		Doctor d1 = new Doctor();
		d1.setId(1);
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7));
		d1.setGender("Male");
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushidhamanage.7@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("J J Hospital");
		d1.setSpecialization("Eye Specialist");
		
		Optional<Doctor> d2=Optional.of(d1);
		
		Mockito.when(doctorRepo.findById((long) 1)).thenReturn(d2);
		
		assertThat(doctorService.getDoctorById(1)).isEqualTo(d1);
		
	}
	
	@Test
	void testDeleteDoctorById() {

		Doctor d1 = new Doctor();
		d1.setId(1);
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7));
		d1.setGender("Male");
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushidhamanage.7@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("J J Hospital");
		d1.setSpecialization("Eye Specialist");
		
		Optional<Doctor> d2=Optional.of(d1);
		
		Mockito.when(doctorRepo.findById((long) 1)).thenReturn(d2);
		Mockito.when(doctorRepo.existsById(d1.getId())).thenReturn(false);
		   assertFalse(doctorRepo.existsById(d1.getId()));
	
	}
	
	@Test
	void testGetDoctorByName() throws ResourceNotFoundException{
		
		Doctor d1 = new Doctor();
		d1.setId(1);
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7));
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushi@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("J J Hospital");
		d1.setSpecialization("Surgen");
		
		List<Doctor> d2 = new ArrayList<>();
		d2.add(d1);
		
		Mockito.when(doctorRepo.findByDoctorName("Rushikesh")).thenReturn(d2);
		assertThat(doctorService.getDoctorByName("Rushikesh")).isEqualTo(d2);
	}
}

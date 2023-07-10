package com.laboratorysystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laboratorysystem.dto.Doctor;
import com.laboratorysystem.exceptions.ResourceNotFoundException;
import com.laboratorysystem.service.DoctorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	@GetMapping("/doctors")
	public List<Doctor> getDoctor() 
	{
		List<Doctor> doctor = doctorService.getDoctor();
		return doctor;
	}
	
	@PostMapping("/doctors")
	public Object addDoctor(@Valid @RequestBody Doctor doctor) throws Throwable
	{
		return doctorService.addDoctor(doctor);
	}
	
	@PutMapping("/doctors/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable long id, @Valid @RequestBody Doctor doctor) throws Throwable
	{
		Doctor d = doctorService.getDoctorById(id);
		
		d.setDoctorName(doctor.getDoctorName());
		d.setDob(doctor.getDob());
		d.setGender(doctor.getGender());
		d.setMobileNo(doctor.getMobileNo());
		d.setEmailId(doctor.getEmailId());
		d.setAddress(doctor.getAddress());
		d.setAge(doctor.getAge());
		d.setHospitalName(doctor.getHospitalName());
		d.setSpecialization(doctor.getSpecialization());
		
		Doctor doc = doctorService.updateDoctor(d);
		
		return ResponseEntity.ok(doc);
	}
	
	@DeleteMapping(path="/doctors/{id}")
	public String deleteDoctorById(@Valid @PathVariable long id) throws Throwable
	{
		doctorService.deleteDoctorById(id);
		
		return "Deleted";
	}
	
	@GetMapping("/doctors/{id}")
	public Doctor getDoctorById(@Valid @PathVariable Long id)throws Throwable
	{
		return doctorService.getDoctorById(id);
	}
	
	@GetMapping("/doctors/doctorName")
	public ResponseEntity<Object> getDoctorByName(@RequestParam String doctorName) throws Throwable
	{
		try {
			Object doc = doctorService.getDoctorByName(doctorName);
			return ResponseEntity.ok(doc);
		}
		catch(ResourceNotFoundException e){
			return ResponseEntity.ok("Doctor Not Found!");
		}
	}
}


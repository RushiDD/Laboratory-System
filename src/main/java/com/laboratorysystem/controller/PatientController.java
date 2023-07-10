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

import com.laboratorysystem.dto.Patient;
import com.laboratorysystem.exceptions.ResourceNotFoundException;
import com.laboratorysystem.service.PatientService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@GetMapping("/patients")
	public List<Patient> getPatient() 
	{
		List<Patient> patient = patientService.getPatient();
		return patient;
	}
	
	@PostMapping("/patients")
	public Object addPatient(@Valid @RequestBody Patient doctor) throws Throwable
	{
		return patientService.addPatient(doctor);
	}
	
	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable long id, @Valid @RequestBody Patient patient) throws Throwable
	{
		Patient p = patientService.getPatientById(id);
		
		p.setPatientName(patient.getPatientName());
		p.setDob(patient.getDob());
		p.setGender(patient.getGender());
		p.setMobileNo(patient.getMobileNo());
		p.setEmailId(patient.getEmailId());
		p.setAddress(patient.getAddress());
		p.setAge(patient.getAge());
		p.setDoctor(patient.getDoctor());
		
		Patient doc = patientService.updatePatient(p);
		
		return ResponseEntity.ok(doc);
	}
	
	@DeleteMapping(path="/patients/{id}")
	public String deletePatientById(@Valid @PathVariable long id) throws Throwable
	{
		patientService.deletePatientById(id);
		
		return "Deleted";
	}
	
	@GetMapping("/patients/{id}")
	public Patient getPatientById(@Valid @PathVariable Long id)throws Throwable
	{
		return patientService.getPatientById(id);
	}
	
	@GetMapping("/patients/patientName")
	public ResponseEntity<Object> getPatientByName(@RequestParam String patientName) throws Throwable
	{
		try {
			Object doc = patientService.getPatientByName(patientName);
			return ResponseEntity.ok(doc);
		}
		catch(ResourceNotFoundException e){
			return ResponseEntity.ok("Patient Not Found!");
		}
		
	}

}

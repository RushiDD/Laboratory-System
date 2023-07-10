package com.laboratorysystem.service;

import java.util.List;

import com.laboratorysystem.dto.Patient;
import com.laboratorysystem.exceptions.ResourceNotFoundException;

public interface PatientService {
	
	List<Patient> getPatient();

	Object addPatient(Patient patient) throws ResourceNotFoundException;

	Patient updatePatient(Patient patient);

	Patient deletePatientById(long id) throws ResourceNotFoundException;

	Patient getPatientById(long id) throws ResourceNotFoundException;

	Object getPatientByName(String patientName) throws ResourceNotFoundException;

}

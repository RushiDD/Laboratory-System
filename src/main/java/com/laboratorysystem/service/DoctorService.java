package com.laboratorysystem.service;

import java.util.List;

import com.laboratorysystem.dto.Doctor;
import com.laboratorysystem.exceptions.ResourceNotFoundException;

public interface DoctorService {

	List<Doctor> getDoctor();

	public Object addDoctor(Doctor doctor) throws ResourceNotFoundException;

	Doctor updateDoctor(Doctor doctor);

	Doctor deleteDoctorById(long id) throws ResourceNotFoundException;

	Doctor getDoctorById(long id) throws ResourceNotFoundException;

	Object getDoctorByName(String doctorName) throws ResourceNotFoundException;
	
	
}

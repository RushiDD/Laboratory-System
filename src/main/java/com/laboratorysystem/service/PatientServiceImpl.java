package com.laboratorysystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratorysystem.dao.PatientRepository;
import com.laboratorysystem.dto.Patient;
import com.laboratorysystem.exceptions.ResourceNotFoundException;

@Service
public class PatientServiceImpl implements PatientService{
	
	@Autowired
	PatientRepository patientRepo;
	
	@Override
	public List<Patient> getPatient()   
	{  
		return patientRepo.findAll();
	} 
	
	@Override
	public Object addPatient(Patient patient) throws ResourceNotFoundException
	{
		Optional<Patient> existingPatient = patientRepo.findByEmailId(patient.getEmailId());
		if(existingPatient.isEmpty())
		{
			return patientRepo.save(patient);
		}
		else
		{
			return "Patient Already Exists with this EmailID!";
		}
	}

	@Override
	public Patient updatePatient(Patient patient) 
	{
		return patientRepo.save(patient);
	}

	@Override
	public Patient getPatientById(long id) throws ResourceNotFoundException
	{
		Optional<Patient> result = patientRepo.findById(id);
		if (result.isPresent())
		{
			return result.get();
		}
		else
		{
			throw new ResourceNotFoundException("Patient does not exists");
		}
	}
	
	@Override
	public Patient deletePatientById(long id) throws ResourceNotFoundException
	{
		Optional<Patient> result=patientRepo.findById(id);
		if(result.isPresent())
		{
			patientRepo.deleteById(id);
			return result.get();
		}
		else
		{
			throw new ResourceNotFoundException("Patient does not exists");
		}
	}

	@Override
	public List<Patient> getPatientByName(String patientName) throws ResourceNotFoundException
	{
		List<Patient> result = patientRepo.findByPatientName(patientName);
		if(((List<Patient>) result).isEmpty())
		{
			throw new ResourceNotFoundException("Patient does not exists");
		}
		else
		{
			return result;
		}
	}
}

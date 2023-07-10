package com.laboratorysystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratorysystem.dao.DoctorRepository;
import com.laboratorysystem.dto.Doctor;
import com.laboratorysystem.exceptions.ResourceNotFoundException;

@Service
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	private DoctorRepository doctorRepo;
	
	@Override
	public List<Doctor> getDoctor()   
	{  
		return doctorRepo.findAll();
	}  
	
	@Override
	public Object addDoctor(Doctor doctor) throws ResourceNotFoundException
	{
		Optional<Doctor> existingDoctor = doctorRepo.findByEmailId(doctor.getEmailId());
		if(existingDoctor.isEmpty())
		{
			return doctorRepo.save(doctor);
		}
		else
		{
			return "Doctor Already Exists with this EmailID!";
		}
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) 
	{
		return doctorRepo.save(doctor);
	}

	@Override
	public Doctor getDoctorById(long id) throws ResourceNotFoundException
	{
		Optional<Doctor> result = doctorRepo.findById(id);
		if (result.isPresent())
		{
			return result.get();
		}
		else
		{
			throw new ResourceNotFoundException("Doctor does not exists");
		}
	}
	
	@Override
	public Doctor deleteDoctorById(long id) throws ResourceNotFoundException
	{
		Optional<Doctor> result=doctorRepo.findById(id);
		if(result.isPresent())
		{
			doctorRepo.deleteById(id);
			return result.get();
		}
		else
		{
			throw new ResourceNotFoundException("Doctor does not exists");
		}
	}

	@Override
	public List<Doctor> getDoctorByName(String doctorName) throws ResourceNotFoundException
	{
		List<Doctor> result = doctorRepo.findByDoctorName(doctorName);
		if(((List<Doctor>) result).isEmpty())
		{
			throw new ResourceNotFoundException("Doctor does not exists");
		}
		else
		{
			return result;
		}
	}
}



package com.laboratorysystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratorysystem.dto.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	Optional<Doctor> save(long doctorId);
	
	Optional<Doctor> findByEmailId(String email);

	List<Doctor> findByDoctorName(String doctorName);
}

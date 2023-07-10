package com.laboratorysystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.laboratorysystem.dto.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

	List<Patient> findAll();

	Optional<Patient> findByEmailId(String emailId);

	List<Patient> findByPatientName(String patientName);

}

package com.laboratorysystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laboratorysystem.dto.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("select u from User u WHERE u.emailId=?1 AND u.password=?2")
	Optional<User> findByEmailId(String emailId, String password);

	Optional<User> findByEmailId(String emailId);
}

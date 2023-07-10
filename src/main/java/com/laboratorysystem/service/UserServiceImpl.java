package com.laboratorysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laboratorysystem.dao.UserRepository;
import com.laboratorysystem.dto.User;
import com.laboratorysystem.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public Object addUser(User user) throws ResourceNotFoundException{
		
		User existingUser = userRepo.findByEmailId(user.getEmailId()).orElse(null);
		if(existingUser == null)
		{
			return userRepo.save(user);
		}
		else
		{
			return "User Already Exists with this EmailId!";
		}
	}
	
	@Override
	public User login(String emailId, String password) throws ResourceNotFoundException
	{
		User user = userRepo.findByEmailId(emailId, password)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User!"));
		return user;
	}
}
 
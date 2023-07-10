package com.laboratorysystem.service;

import com.laboratorysystem.dto.User;
import com.laboratorysystem.exceptions.ResourceNotFoundException;

public interface UserService {

	Object addUser(User user) throws ResourceNotFoundException;

	User login(String emailId, String password) throws ResourceNotFoundException;

}

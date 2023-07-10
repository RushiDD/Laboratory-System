package com.laboratorysystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laboratorysystem.dto.User;
import com.laboratorysystem.exceptions.ResourceNotFoundException;
import com.laboratorysystem.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signup")
	public Object addUser(@Valid @RequestBody User user) throws Throwable
	{
		return userService.addUser(user);
	}

	@GetMapping("/login")
	public ResponseEntity<Object> login(@RequestParam String emailId, String password) throws Throwable
	{
		try {
			User user = userService.login(emailId, password);
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}
		catch(ResourceNotFoundException e) {
			return ResponseEntity.ok().body(e.getMessage());
		}
	}
}
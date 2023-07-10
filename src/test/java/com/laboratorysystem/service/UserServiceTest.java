package com.laboratorysystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.laboratorysystem.dao.UserRepository;
import com.laboratorysystem.dto.User;
import com.laboratorysystem.exceptions.ResourceNotFoundException;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@MockBean
	UserRepository userRepo;

//	@Test
//	void testLogin() throws ResourceNotFoundException {
//		User u = new User();
//		u.setId(1);
//		u.setUserName("Rushikesh"); 
//		u.setPassword("Rushi@123");
//		u.setEmailId("rushi@gmail.com");
//		
//		//Optional<User> u1 = Optional.of(u);
//		
//		Mockito.when(userRepo.save(u)).thenReturn(u);
//		assertThat(userService.addUser(u)).isEqualTo(u);
//		
//		Optional<User> u1 = Optional.of(u);
//					
//		Mockito.when(userRepo.findByEmailId(u.getEmailId())).thenReturn(u1);
//		assertThat(userService.login(u.getEmailId(), u.getPassword())).isEqualTo(u);
//	}

	@Test 
	public void testAddUser() throws ResourceNotFoundException
	{
		User u = new User();
		u.setId(1);
		u.setUserName("Rushikesh"); 
		u.setPassword("Rushi@123");
		u.setEmailId("rushi@gmail.com");
		
		Mockito.when(userRepo.save(any(User.class))).thenReturn(u);
		assertThat(userService.addUser(u)).isEqualTo(u);
	}
}

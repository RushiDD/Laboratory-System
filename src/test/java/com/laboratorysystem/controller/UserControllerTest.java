package com.laboratorysystem.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.laboratorysystem.dto.User;
import com.laboratorysystem.exceptions.ResourceNotFoundException;
import com.laboratorysystem.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest{
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	@Test
	public void testAddUser() throws Throwable {

		RequestBuilder request = MockMvcRequestBuilders  
				.post("/api/v1/users/signup")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"userName\":\"Rushikesh\",\"password\":\"Rushi@123\",\"emailId\":\"rushi@gmail.com\"}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andReturn();
	}
	
	@Test
	public void loginTest() throws Throwable {
		
		User u = new User();
		u.setId(1);
		u.setUserName("Rushikesh");
		u.setPassword("Rushi@123");
		u.setEmailId("rushi@gmail.com");
		
		Optional<User> u1 = Optional.of(u);
		
		when(userService.login(u.getEmailId(), u.getPassword())).thenReturn(u);
		
		RequestBuilder request = MockMvcRequestBuilders  
				.get("/api/v1/users/login?emailId=rushi@gmail.com&password=Rushi@123")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andReturn();
	}
	
	@Test
	public void testExceptionThrownWhenLogin() throws Throwable {
			User u = new User();
				u.setId(1);
				u.setUserName("Rushikesh");
				u.setPassword("Rushi@123"); 
				u.setEmailId("rushi@gmail.com");
	
				User u2=new User();
	
				Optional<User> u1=Optional.of(u2);
				ResourceNotFoundException e=new ResourceNotFoundException("");
				Mockito.when(userService.login(u.getEmailId(), u.getPassword())).thenThrow(e);
				
				RequestBuilder request = MockMvcRequestBuilders  
						.get("/api/v1/users/login?emailId=rushi@gmail.com&password=Rushi@123")
						.accept(MediaType.APPLICATION_JSON);
				
				MvcResult result = mockMvc.perform(request)
						.andExpect(status().isOk()) 
						.andReturn();
	}
	
}
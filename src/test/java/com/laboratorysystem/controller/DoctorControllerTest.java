package com.laboratorysystem.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratorysystem.dto.Doctor;
import com.laboratorysystem.service.DoctorService;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	DoctorService doctorService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void getDoctorTest() throws Throwable {

		Doctor d1 = new Doctor();
		d1.setId(42); 
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7));
		d1.setGender("Male");
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushi@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("Civil Hospital");
		d1.setSpecialization("Surgen");

		Doctor d2 = new Doctor();
		d2.setId(47);
		d2.setDoctorName("Rushikesh"); 
		d2.setDob(LocalDate.of(2002, 01, 15));
		d2.setGender("Male");
		d2.setMobileNo("8482927915");
		d2.setEmailId("rushidhamanageq12@gmail.com");
		d2.setAddress("Sangli");
		d2.setAge(22);
		d2.setHospitalName("Magdum Hospital");
		d2.setSpecialization("Dentist");

		List<Doctor> doctorList = new ArrayList<>();
		doctorList.add(d1);
		doctorList.add(d2);

		when(doctorService.getDoctor()).thenReturn(doctorList);

		RequestBuilder request = MockMvcRequestBuilders  
				.get("/api/v1/doctors")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(content().json("[{\"id\":42,\"doctorName\":\"Rushikesh\",\"dob\":\"1999-09-07\",\"gender\":\"Male\",\"mobileNo\":\"8482927915\",\"emailId\":\"rushi@gmail.com\",\"address\":\"Sangli\",\"age\":23,\"hospitalName\":\"Civil Hospital\",\"specialization\":\"Surgen\"},{\"id\":47,\"doctorName\":\"Rushikesh\",\"dob\":\"2002-01-15\",\"gender\":\"Male\",\"mobileNo\":\"8482927915\",\"emailId\":\"rushidhamanageq12@gmail.com\",\"address\":\"Sangli\",\"age\":22,\"hospitalName\":\"Magdum Hospital\",\"specialization\":\"Dentist\"}]"))
				.andReturn();
	}

	@Test 
	public void addDoctorTest() throws Throwable {

		Doctor d1 = new Doctor();
		d1.setId(42); 
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7));
		d1.setGender("Male");
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushi@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("Civil Hospital");
		d1.setSpecialization("Surgen");

		when(doctorService.addDoctor(d1)).thenReturn(d1);

		RequestBuilder request = MockMvcRequestBuilders  
				.post("/api/v1/doctors")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":42,\"doctorName\":\"Rushikesh\",\"dob\":\"1999-09-07\",\"gender\":\"Male\",\"mobileNo\":\"8482927915\",\"emailId\":\"rushi@gmail.com\",\"address\":\"Sangli\",\"age\":23,\"hospitalName\":\"Civil Hospital\",\"specialization\":\"Surgen\"}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andReturn();
	}

	@Test
	void deleteDoctorTest() throws Throwable {
		mockMvc.perform(delete("/api/v1/doctors/1"))
		.andExpect(status().isOk());

	}

	@Test
	public void getDoctorByIdTest() throws Throwable {

		Doctor d1 = new Doctor();
		d1.setId(42); 
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7)); 
		d1.setGender("Male");
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushi@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("Civil Hospital");
		d1.setSpecialization("Surgen");


		when(doctorService.getDoctorById(d1.getId())).thenReturn(d1);
		mockMvc.perform(get("/api/v1/doctors/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(d1)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void updateDoctorTest() throws Throwable {
		long id = 1L;

		Doctor doctor = new Doctor(id, "Rushikesh", LocalDate.of(1999, 9, 7), "Male", "8482927915", "rushi@gmail.com",
				"Sangli", 23, "Civil Hospital", "Surgen");
		Doctor updatedDoctor = new Doctor(id, "RushikeshD", LocalDate.of(1999, 9, 7), "Male", "8482927915", "rushidd@gmail.com",
				"Sangli", 23, "Civil Hospital", "Surgen");

		when(doctorService.getDoctorById(id)).thenReturn(doctor);

		mockMvc.perform(put("/api/v1/doctors/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedDoctor)))
		.andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();

	}
	
	@Test
	public void getDoctorByName1Test() throws Throwable {

		Doctor d1 = new Doctor();
		d1.setId(42); 
		d1.setDoctorName("Rushikesh");
		d1.setDob(LocalDate.of(1999, 9, 7)); 
		d1.setGender("Male");
		d1.setMobileNo("8482927915");
		d1.setEmailId("rushi@gmail.com");
		d1.setAddress("Sangli");
		d1.setAge(23);
		d1.setHospitalName("Civil Hospital");
		d1.setSpecialization("Surgen");

		Optional<Doctor> d2 = Optional.of(d1);

		when(doctorService.getDoctorByName(d1.getDoctorName())).thenReturn(d2);
		mockMvc.perform(get("/api/v1/doctors?doctorName=Rushikesh")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(d1)))
				.andExpect(MockMvcResultMatchers.status().isOk());
				//.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void getDoctorByNameTest() throws Throwable {
		Doctor doctor = new Doctor();
		String doctorName = "Rushikesh";
		
		Optional<Doctor> doctor1 = Optional.of(doctor);

		when(doctorService.getDoctorByName(doctorName)).thenReturn(doctor1);

		mockMvc.perform(get(String.format("/api/v1/doctors?doctorName=Rushikesh"))
				.contentType(MediaType.APPLICATION_JSON)
				.queryParam("doctorName", doctorName))
		.andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) throws JsonProcessingException{
		return new ObjectMapper().writeValueAsString(obj);

	}
}

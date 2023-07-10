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
import com.laboratorysystem.dto.Patient;
import com.laboratorysystem.service.PatientService;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	PatientService patientService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void getPatientTest() throws Throwable {

		Patient p1 = new Patient();
		p1.setId(1);
		p1.setPatientName("Rushikesh"); 
		p1.setDob(LocalDate.of(1999, 9, 7));
		p1.setGender("Male");
		p1.setMobileNo("8482927915");
		p1.setEmailId("rushidhamanage.7@gmail.com");
		p1.setAddress("Sangli");
		p1.setAge(23);

		Patient p2 = new Patient();
		p2.setId(2);
		p2.setPatientName("Vaibhav");
		p2.setDob(LocalDate.of(2000, 12, 11));
		p2.setGender("Male");
		p2.setMobileNo("8208428378");
		p2.setEmailId("vaibhavdhamanage@gmail.com");
		p1.setAddress("Sangli");
		p2.setAge(21);

		List<Patient> patientList = new ArrayList<>();
		patientList.add(p1);
		patientList.add(p2);

		when(patientService.getPatient()).thenReturn(patientList);

		RequestBuilder request = MockMvcRequestBuilders  
				.get("/api/v1/patients")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andExpect(content().json("[{\"id\":1,\"patientName\":\"Rushikesh\",\"dob\":\"1999-09-07\",\"gender\":\"Male\",\"mobileNo\":\"8482927915\",\"emailId\":\"rushidhamanage.7@gmail.com\",\"address\":\"Sangli\",\"age\":23},{\"id\":2,\"patientName\":\"Vaibhav\",\"dob\":\"2000-12-11\",\"gender\":\"Male\",\"mobileNo\":\"8208428378\",\"emailId\":\"vaibhavdhamanage@gmail.com\",\"age\":21}]"))
				.andReturn();
	}

	@Test
	public void addPatientTest() throws Throwable {

		RequestBuilder request = MockMvcRequestBuilders  
				.post("/api/v1/patients")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"patientName\":\"Rushikesh\",\"dob\":\"1999-09-07\",\"gender\":\"Male\",\"mobileNo\":\"8482927915\",\"emailId\":\"rushidhamanage.7@gmail.com\",\"address\":\"Sangli\",\"age\":23}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk()) 
				.andReturn();
	}

	@Test
	void deletePatientTest() throws Throwable {
		mockMvc.perform(delete("/api/v1/patients/1"))
		.andExpect(status().isOk());

	}

	@Test
	public void getPatientByIdTest() throws Throwable {

		Patient p1 = new Patient();
		p1.setId(1);
		p1.setPatientName("Rushikesh"); 
		p1.setDob(LocalDate.of(1999, 9, 7));
		p1.setGender("Male");
		p1.setMobileNo("8482927915");
		p1.setEmailId("rushidhamanage.7@gmail.com");
		p1.setAddress("Sangli");
		p1.setAge(23);


		when(patientService.getPatientById(p1.getId())).thenReturn(p1);
		mockMvc.perform(get("/api/v1/patients/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(p1)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print()); 
	}

	@Test
	void updatePatientTest() throws Throwable {
		long id = 1L;

		Patient patient = new Patient(id, "Rushikesh", LocalDate.of(1999, 9, 7), "Male", "8482927915", "rushi@gmail.com",
				"Sangli", 23, new Doctor());
		Patient updatedPatient = new Patient(id, "RushikeshD", LocalDate.of(1999, 9, 7), "Male", "8482927915", "rushidd@gmail.com",
				"Sangli", 23, new Doctor());

		when(patientService.getPatientById(id)).thenReturn(patient);

		mockMvc.perform(put("/api/v1/patients/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedPatient)))
				.andExpect(status().isOk());
				//.andReturn().getResponse().getContentAsString();

	}
	
	@Test
    void getPatientByNameTest() throws Throwable {
		Patient patient = new Patient();
        String patientName = "Rushikesh";
        Optional<Patient> patient1 = Optional.of(patient);

        when(patientService.getPatientByName(patientName)).thenReturn(patient1);

        mockMvc.perform(get(String.format("/api/v1/patients?patientName=Rushikesh"))
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("patientName", patientName))
                .andExpect(status().isOk());

    }

	public static String asJsonString(final Object obj) throws JsonProcessingException{
			return new ObjectMapper().writeValueAsString(obj);
		
	}

}

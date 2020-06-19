package edu.zut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.zut.model.User;
import edu.zut.repository.UserRepository;

@SpringBootTest(classes = AwirMvcApplication.class)
@WebAppConfiguration
public class RestUserControllerTest {

	private MockMvc mvc;
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	UserRepository repository;
	
	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void getAllUsersTest() throws Exception {
		String uri = "/rest";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = result.getResponse().getContentAsString();
		User[] users = mapFromJson(content, User[].class);
		assertTrue(users.length > 0);
	}
	
	@Test
	public void getSpecifiedUserTest() throws Exception {
		String uri = "/rest/2";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = result.getResponse().getContentAsString();
		User user = mapFromJson(content, User.class);
		
		assertEquals("Maria", user.getFirstName());
		assertEquals("Kowalska", user.getLastName());
		assertEquals("maria.kowalska@wp.pl", user.getEmail());
	}

	@Test
	public void addUserTest() throws Exception {
		String uri = "/rest";
		User user = new User(1l, "John", "Smith", "j.smith@hotmail.com");
		String input = mapToJson(user);
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input)).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		String uri = "/rest/1";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	protected String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(object);
	}
	
	protected <T> T mapFromJson(String json, Class<T> c) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.readValue(json, c);
	}
	
}

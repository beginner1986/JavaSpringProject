package edu.zut;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(value = "admin")
	@Test
	public void listUsersPageAuthorisedAccessTest() throws Exception {
		mockMvc.perform(get("/user/listUsers"))
			.andExpect(status().isOk())
			.andExpect(view().name("listUsers"));
	}

	@Test
	public void listUsersPageUnauthorisedAccessDeniedTest() throws Exception {
		mockMvc.perform(get("/user/listUsers"))
			.andExpect(status().is3xxRedirection());
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void addUserPageAuthorisedAccessTest() throws Exception {
		mockMvc.perform(get("/user/addUserPage"))
			.andExpect(status().isOk())
			.andExpect(view().name("addUser"));
	}
	
	@Test
	public void addUserPageUnauthorisedAccessDeniedTest() throws Exception {
		mockMvc.perform(get("/user/addUserPage"))
			.andExpect(status().is3xxRedirection());
	}

}

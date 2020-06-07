package edu.zut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.zut.model.User;
import edu.zut.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;
	
	@Test
	public void addUserToRepoTest() {
		User addedUser = new User(0, "John", "Smith", "j.smith@gmail.com");
		repository.save(addedUser);
		
		User foundUser = repository.findByFirstName("John").get(0);
		
		assertNotNull(foundUser);
		assertEquals(addedUser.getFirstName(), foundUser.getFirstName());
		assertEquals(addedUser.getLastName(), foundUser.getLastName());
		assertEquals(addedUser.getEmail(), foundUser.getEmail());
	}

}

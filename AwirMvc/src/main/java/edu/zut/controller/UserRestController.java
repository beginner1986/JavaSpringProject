package edu.zut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.zut.model.User;
import edu.zut.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/rest")
public class UserRestController {

	@Autowired
	UserRepository repository;
	
	@GetMapping("/users")
	List<User> getAllUsers() {
		
		log.info("REST: Listowanie użutkowników.");
		
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	User getUser(@PathVariable Long id) {
		
		log.info("REST: Wyświtlanie pojedynczego użutkownika.");
		
		return repository.findById(id).get();
	}
	
	@PostMapping("/{id}")
	User addUser(@RequestBody User user) {
		
		log.info("REST: Zapisywanie pojedynczego użytkownika.");
		
		return repository.save(user);
	}
	
	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable Long id) {
		
		log.info("REST: Usuwanie użytkownika.");
		
		repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	User editUser(@RequestBody User newUser, @PathVariable Long id) {
		return repository.findById(id)
				.map(user -> {
					user.setFirstName(newUser.getFirstName());
					user.setLastName(newUser.getLastName());
					user.setEmail(newUser.getEmail());
					
					return repository.save(user);
				})
				.orElseGet(() -> {
					newUser.setId(id);
					return repository.save(newUser);
				});
					
	}
}

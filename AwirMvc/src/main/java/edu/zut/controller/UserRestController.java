package edu.zut.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@GetMapping(produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUsers() {
		
		log.info("REST: Listowanie użutkowników.");
		
		return repository.findAll();
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		
		log.info("REST: Wyświtlanie pojedynczego użutkownika.");
		
		Optional<User> user = repository.findById(id);
		
		if(user.isPresent())
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User user) {
		
		log.info("REST: Zapisywanie pojedynczego użytkownika.");
		
		return repository.save(user);
	}
	
	@DeleteMapping(path = "/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void deleteUser(@PathVariable Long id) {
		
		log.info("REST: Usuwanie użytkownika.");
		
		if(repository.findById(id).isPresent())	{
			repository.deleteById(id);
		}
		
	}
	
	@PutMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<User> replaceUser(@PathVariable Long id, @RequestBody User putUser) {
		
		log.info("REST: Zamiana użytkownika");
		
		Optional<User> optional = repository.findById(id);
		if(!optional.isPresent())
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
		if(optional.get().equals(putUser))
			return new ResponseEntity<>(putUser, HttpStatus.NOT_MODIFIED);
		
		repository.save(putUser);
		return new ResponseEntity<>(putUser, HttpStatus.OK);
	}
	
	@PatchMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User patchUser) {
		
		log.info("REST: Edycja użytkownika");
		
		Optional<User> optional = repository.findById(id);
		if(!optional.isPresent())
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
		if(optional.get().equals(patchUser))
			return new ResponseEntity<>(patchUser, HttpStatus.NOT_MODIFIED);
		 
		User newUser = optional.get();
		
		if(patchUser.getFirstName() != null)
			newUser.setFirstName(patchUser.getFirstName());
		
		if(patchUser.getLastName() != null)
			newUser.setLastName(patchUser.getLastName());
		
		if(patchUser.getEmail() != null)
			newUser.setEmail(patchUser.getEmail());

		repository.save(newUser);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

}

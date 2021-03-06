package edu.zut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.zut.model.User;
import edu.zut.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository repository;
	
	@RequestMapping(value="/listUsers", method=RequestMethod.GET)
    public String listUsers(Model model) {
		log.info("WWW: Listowanie użytkowników");

		List<User> users = this.repository.findAll();

        model.addAttribute("users", users);

        return "listUsers";
    }
	
    @RequestMapping(value="/addUserPage", method=RequestMethod.GET)
    public String addUserPage() {
    	log.info("WWW: Wyświetlono stronę dodawania nowego użytkownika");
    	
        return "addUser";
    }
    
    @RequestMapping(value="/addUser", method=RequestMethod.POST)
    public String addUser(User user) {
    	log.info("WWW: Dodawanie użytkownika do bazy");
    	
        repository.save(user);
        return "redirect:/user/listUsers";
    }
    
    @RequestMapping(value="/deleteUser/{id}", method=RequestMethod.GET)
    public String deleteBuyer(@PathVariable("id") Long id, Model model){
    	
        User user = repository.findById(id).get();
        
        log.info("UWWW: suwanie użytkownika: " + user);
        
        repository.delete(user);
        model.addAttribute("users", repository.findAll());
        
        return "redirect:/user/listUsers";
    }
}

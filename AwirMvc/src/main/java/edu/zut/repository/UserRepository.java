package edu.zut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.zut.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByFirstName(String userName);
}

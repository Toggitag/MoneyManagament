package com.rk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	 	
		User findByUsername(String userName);
		
	 	Boolean existsByUsername(String userName);
	    
	 	Boolean existsByEmail(String email);
	 	
	 	Boolean existsBycontactno(Integer contactno);

}
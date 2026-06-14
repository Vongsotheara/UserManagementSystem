package com.CRUDOperation.usermanangementsystem.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUDOperation.usermanangementsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	//boolean existsByEmail(String email);
	//boolean existsByUsername(String username);
	
	//only check email for non deleted users
	boolean existsByEmailAndDeletedFalse(String email);
	
	// only check username for non deleted users
	boolean existsByUsernameAndDeletedFalse(String username);
	
	//only find user if not deleted
	Optional<User> findByIdAndDeletedFalse(Long id);
	
	// only return users that are not deleted
	Page<User> findByDeletedFalse(Pageable pageable);
	
	//for string security
	Optional<User> findByUsernameAndDeletedFalse(String username);

}

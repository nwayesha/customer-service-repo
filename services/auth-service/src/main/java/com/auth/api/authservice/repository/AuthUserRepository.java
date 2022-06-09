package com.auth.api.authservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auth.api.authservice.entity.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {

	@Query("SELECT c FROM AuthUser c WHERE c.email = ?1")
	Optional<AuthUser> findByEmail(String email);
	
	
	@Query("SELECT c FROM AuthUser c WHERE c.token = ?1")
	Optional<AuthUser> findByToken(String token);
	
	
	/*Optional<Customer> findByEmailAndName(String email, String name);*/

}

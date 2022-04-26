package com.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.entity.Country;

public interface CountryRepository extends JpaRepository<Country, String> {

	@Query("SELECT c FROM Country c WHERE c.id = ?1")
	Optional<Country> findCountryByCountryId(String id);
	
	@Query("SELECT c FROM Country c WHERE c.name like ?1%")
	List<Country> findCountryStartwithCountryName(String countryName);
	
	@Query("SELECT c FROM Country c WHERE c.name like %?1%")
	List<Country> findCountryByAnyCountryName(String countryName);
}

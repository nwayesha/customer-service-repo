package com.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Country;
import com.api.repository.CountryRepository;

@Service
public class CountryService {
	
	final static Logger logger = LoggerFactory.getLogger(CountryService.class);
	
	@Autowired
	private CountryRepository countryRepository;

	public List<Country> getAllCountryList() {
		List<Country> countryList = countryRepository.findAll();
		if (!countryList.isEmpty()) {
			logger.info("getting all customer details --");
			return countryList;
		}
		return null;
	}

	public Optional <Country> getCountryByCountryId(String countryId) {
		Optional<Country> country = countryRepository.findCountryByCountryId(countryId);
		return country;
	}

	public void saveCountry(Country country) {
		logger.info("CountryService: saveCountry: getId(): "+ country.getId()  + "  getName:"+country.getName());
		if(null != country.getId() && null != country.getName()) {			
			country.setId(country.getId());
			country.setName(country.getName());
			countryRepository.save(country);
			logger.info("saving the country --- ");
		}
		
	}

	public Country updateCountry(Country country) {
		countryRepository.save(country);
		return country;
	}

	public void deleteCountry(String countryId) {
		if(null != countryId && "" != countryId) {
			logger.info("deleting the country --- ");
			countryRepository.deleteById(countryId);
		}
		
	}

	public List<Country> findCountryStartwithCountryName(String country_name) {
		List<Country> countryList = countryRepository.findCountryStartwithCountryName(country_name);
		if (!countryList.isEmpty()) {
			logger.info("getting country by country name --");
			return countryList;
		}
		return null;
	}
	
	public List<Country> findCountryByAnyCountryName(String country_name) {
		List<Country> countryList = countryRepository.findCountryByAnyCountryName(country_name);
		if (!countryList.isEmpty()) {
			logger.info("getting country by country name --");
			return countryList;
		}
		return null;
	}

}

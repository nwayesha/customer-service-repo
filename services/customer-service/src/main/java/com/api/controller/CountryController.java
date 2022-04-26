package com.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.Country;
import com.api.service.CountryService;

import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("country")
public class CountryController {

	final static Logger logger = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;

	@ApiOperation(value = "Search all countries")
	@GetMapping("/allCountries")
	public @ResponseBody ResponseEntity<List<Country>> getAllCountryList() {
		List<Country> countryList = null;
		try {
			countryList = countryService.getAllCountryList();
		} catch (Exception e) {
			logger.error("exception in getting all country data -- ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok().body(countryList);
	}

	@ApiOperation(value = "Search country using country id")
	@GetMapping("/find/id/{country_id}")
	public @ResponseBody ResponseEntity<Optional<Country>> getCountryByCountryId(@PathVariable String country_id) {
		try {
			logger.info("finding customer details by id" + country_id);
			Optional<Country> country = countryService.getCountryByCountryId(country_id);
			if (country == null) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok().body(country);
		} catch (Exception e) {
			logger.error("exception in getting all customer details -- ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search country using first letters of country name")
	@GetMapping("/find/name/startwith/{country_name}")
	public @ResponseBody ResponseEntity<List<Country>> getCountryByCountryName(@PathVariable String country_name) {
		try {
			logger.info("finding customer details by first letters   -" + country_name);
			List<Country> countryListByName = countryService.findCountryStartwithCountryName(country_name);
			if (countryListByName == null) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok().body(countryListByName);
		} catch (Exception e) {
			logger.error("exception in getting all customer details -- ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search country using any letters of country name")
	@GetMapping("/find/name/startwithAny/{country_name}")
	public @ResponseBody ResponseEntity<List<Country>> getCountryByAnyCountryName(@PathVariable String country_name) {
		try {
			logger.info("finding customer details by any letters  -" + country_name);
			List<Country> countryListByName = countryService.findCountryByAnyCountryName(country_name);
			if (countryListByName == null) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok().body(countryListByName);
		} catch (Exception e) {
			logger.error("exception in getting all customer details -- ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create New Country")
	@RequestMapping(value = "/Save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> saveCountry(@RequestBody Country country, HttpServletResponse response) {
		try {
			countryService.saveCountry(country);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok("Success");
	}

	@ApiOperation(value = "Update Country")
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> updateCCountry(@RequestBody Country country, HttpServletResponse response) {
		try {
			country = countryService.updateCountry(country);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(country);
	}

	@ApiOperation(value = "Delete Country")
	@DeleteMapping("/find/id/{delete_country_id}")
	public ResponseEntity<?> deleteCountry(@PathVariable String delete_country_id) {
		try {
			countryService.deleteCountry(delete_country_id);
			;

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok("Success");
	}

}

package com.api.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.api.BaseIT;
import com.api.entity.Customer;


public class CustomerControllerTest extends BaseIT {
	/*	
	@Test(groups = "regression")
	public void contextLoads() {
		Assert.assertNotNull(getWebController(), "Controller is null");
	}

	@Test(groups = "regression")
	public void create() throws IOException, ParseException {
		String addURI = "http://localhost:8080/customer/Save";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");
		// headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");

		logger.info("Add URL :" + addURI);

		Customer customer = new Customer();
		customer.setAddress("address test");
		customer.setEmail("email test");
		customer.setTelephone("telephone test");
		customer.setName("name test");
		customer.setId(50);

		HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);
		ResponseEntity<String> response = getRestTemplate().postForEntity(addURI, entity, String.class);
		String responseObject = response.getBody();

		// Get responseObject from the Response object
		System.out.println("responseObject is :" + responseObject.toString());

		Assert.assertEquals("Success", responseObject);

		// Check if the status code is 201
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test(groups = "regression", expectedExceptions= { HttpMessageNotWritableException.class, NullPointerException.class })
	public void create1() throws IOException, ParseException {
		String addURI = "http://localhost:8080/customer/Save";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");
		// headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");

		logger.info("Add URL :" + addURI);

		Customer customer = new Customer();
		customer.setAddress("address test");
		customer.setEmail("email test");
		customer.setTelephone("telephone test");
		customer.setName("name test");
		//customer.setId(50);//exception due to this

		HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);
		ResponseEntity<String> response = getRestTemplate().postForEntity(addURI, entity, String.class);
		String responseObject = response.getBody();

		// Get responseObject from the Response object
		System.out.println("responseObject is :" + responseObject.toString());

		Assert.assertEquals("Success", responseObject);

		// Check if the status code is 201
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test(groups = "regression1")
	public void test1() {
		Assert.assertEquals("OK", "OK");
	}

	@AfterTest(groups = "regression")
	public void afterTest() {
		logger.info("Clean up after test execution");
		logger.info("Creating RestTemplate object as Null");
		setRestTemplate(new RestTemplate());
	}*/
}
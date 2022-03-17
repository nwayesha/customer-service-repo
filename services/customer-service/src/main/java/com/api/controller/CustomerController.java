package com.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.customerservice.CustomerServiceImpl;
import com.api.entity.Customer;

import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("customer")
public class CustomerController {

	final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@GetMapping("/customerDetails")
	public List<Customer> getCustomerDetails() {
		List<Customer> costomerDetails = customerServiceImpl.getCustomerDetails();
		return costomerDetails;
	}

	@GetMapping("/find/id/{customer_id}")
	public Customer getCustomerDetailsbyId(@PathVariable String customer_id) {
		logger.info("finding customer details by id" + customer_id);
		return customerServiceImpl.getCustomerDetailsbyId(customer_id);
	}

	
	@GetMapping("/find/id/{customer_id}/{regoin_id}")
	public Customer getCustomerDetailsbyId(@PathVariable String customer_id, @PathVariable String regoin_id) {
		logger.info("finding customer details by id" + customer_id);
		return customerServiceImpl.getCustomerDetailsbyId(customer_id);
	}
	
	
	@ApiOperation(value = "Update Customer")
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> updateCustomerDetails(@RequestBody Customer customer, HttpServletResponse response) {
		try {
			customer = customerServiceImpl.updateCustomerDetails(customer);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return ResponseEntity.ok(customer);	
	}

	@ApiOperation(value = "Create New Customer")
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> deleteCustomerDetails(@RequestBody Customer customer, HttpServletResponse response) {
		try {
			customerServiceImpl.saveCustomerDetails(customer);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return ResponseEntity.ok("Success");				
	}
	
	

	@GetMapping("/serviceCheck")
	@ResponseBody
	public String serviceCheck() {
		JSONObject json = new JSONObject();
		json.appendField("status", "success");

		return json.toJSONString();
	}
}

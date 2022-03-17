package com.api.customerservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.controller.CustomerController;
import com.api.entity.Customer;
import com.api.repository.CustomerRepository;

import net.minidev.json.JSONObject;

@Service
public class CustomerServiceImpl {
	final static Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getCustomerDetails() {
		// TODO Auto-generated method stub
		ArrayList<Customer> customerAray = new ArrayList <Customer>();
//		customerAray.add(new Customer(1,"Ayesha","Hammarbyvagen 60","nwayesha@gmail.com","0727869558"));
//		customerAray.add(new Customer(2,"Thusutha","Hammarbyvagen 61","idthusitha@gmail.com","0727869462"));
//		customerAray.add(new Customer(3,"Sandeli","Hammarbyvagen 62","sandeli@gmail.com","0727869000"));
//		customerAray.add(new Customer(4,"Shaneli","Hammarbyvagen 63","shaneli@gmail.com","0727868000"));
//		customerAray.add(new Customer(5,"Amindu","Hammarbyvagen 64","amindu@gmail.com","0727867000"));
		return customerAray;
	}

	public Customer getCustomerDetailsbyId(String customer_id) {
		// TODO Auto-generated method stub
//		if(null != customer_id && "1".equals(customer_id)) {
//			logger.info("get Customer details by id"+customer_id);
//			return new Customer(1,"Ayesha","Hammarbyvagen 60","nwayesha@gmail.com","0727869558");
//		}else if(null != customer_id && "2".equals(customer_id)) {
//			logger.info("get Customer details by id"+customer_id);
//			return new Customer(2,"Thusutha","Hammarbyvagen 61","idthusitha@gmail.com","0727869462");
//		}else if(null != customer_id && "3".equals(customer_id)) {
//			logger.info("get Customer details by id"+customer_id);
//			return new Customer(3,"Sandeli","Hammarbyvagen 62","sandeli@gmail.com","0727869000");
//		}else if(null != customer_id && "4".equals(customer_id)) {
//			logger.info("get Customer details by id"+customer_id);
//			return new Customer(4,"Shaneli","Hammarbyvagen 63","shaneli@gmail.com","0727868000");
//		}else if(null != customer_id && "5".equals(customer_id)) {
//			logger.info("get Customer details by id"+customer_id);
//			return new Customer(5,"Amindu","Hammarbyvagen 64","amindu@gmail.com","0727867000");
//		}else {
//			logger.info("get Customer details by id"+customer_id);
//			return null;
//		}
		return null;
	}

	public Customer updateCustomerDetails(Customer customer) {
		customerRepository.save(customer);
		return customer;		
	}

	public Customer saveCustomerDetails(Customer customer) {
		//logger.info("saving the customer"+customer.getId());
		customerRepository.save(customer);		
		return customer;
	}

	public void deleteCustomerDetails(String customer_id) {
		Customer customer = new Customer();
		customer.setId(Integer.parseInt(customer_id));
		customerRepository.delete(customer);;
	
	}

}

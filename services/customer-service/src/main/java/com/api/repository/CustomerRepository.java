package com.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}

package com.techfully.interview.service;

import java.util.List;

import com.techfully.interview.entity.Customer;

public interface CustomerService {

	public Customer getCustomer(int id);
	
	public List<Customer> findAll();
	
	public void saveCustomer(Customer customer);
	
	public void deleteCustomer(int id);
	
	public void deleteAll();
}

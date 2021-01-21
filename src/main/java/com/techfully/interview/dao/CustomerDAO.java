package com.techfully.interview.dao;

import java.util.List;

import com.techfully.interview.entity.Customer;

public interface CustomerDAO {

	public Customer getCustomer(int id);
	
	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer customer);
	
	public void deleteCustomer(int id);
	
	public void deleteAll();
}

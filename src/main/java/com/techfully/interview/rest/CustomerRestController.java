package com.techfully.interview.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techfully.interview.entity.Customer;
import com.techfully.interview.service.CustomerService;

@RestController
public class CustomerRestController {
	
	// defining the dependency
	private CustomerService customerService;
	
	// contructor injection of dependency - customerService
	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}

	// to get all the customers
	@GetMapping("/api/customers")
	public List<Customer> getCustomers() {
		
		return customerService.findAll();
	}
	
	// to get a particular customer using id
	@GetMapping("/api/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		return customerService.getCustomer(customerId);
	}
	
	// to add a new customer
	@PostMapping("/api/customers/")
	public void addCustomer(@RequestBody Customer theCustomer) {
		
		// to make sure the customer is a new customer.
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
	}
	
	// to update the existing customer
	@PutMapping("/api/customers/")
	public void updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
	}
	
	// deleting all the customers
	@DeleteMapping("/api/customers/")
	public void deleteAll() {
		
		customerService.deleteAll();
	}
	
	// to delete a particular customer using the id
	@DeleteMapping("/api/customers/{customerId}")
	public void deleteCustomer(@PathVariable int customerId) {
		
		customerService.deleteCustomer(customerId);
	}
}

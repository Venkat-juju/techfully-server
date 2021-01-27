package com.techfully.interview.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
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
	@CrossOrigin(origins= "http://localhost:3000")
	@GetMapping("/api/customers")
	public List<Customer> getCustomers() {
		
		return customerService.findAll();
	}
	
	// to get a particular customer using id
	@CrossOrigin(origins= "http://localhost:3000")
	@GetMapping("/api/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerService.getCustomer(customerId);
		
		if (theCustomer == null) {
			throw new CustomerNotFoundException("There is no customer with id: " + customerId);
		}
		
		return theCustomer;
	}
	
	// to add a new customer
	@PostMapping("/api/customers")
	public String addCustomer(@RequestBody Customer theCustomer) {
		
		System.out.println("inside the post method");
		// to make sure the customer is a new customer.
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return "SUCCESS";
	}
	
	// to update the existing customer and return the updated customer
	@CrossOrigin(origins= "http://localhost:3000")
	@PutMapping("/api/customers")
	public String updateCustomer(@RequestBody Customer theCustomer) {
		
		Customer cust = customerService.getCustomer(theCustomer.getId());
		
		if (cust == null) {
			throw new CustomerNotFoundException("There is no customer with id: " + theCustomer.getId());
		}
		
		customerService.saveCustomer(theCustomer);
		
		return "SUCCESS";
	}
	
	// deleting all the customers
	@CrossOrigin(origins= "http://localhost:3000")
	@DeleteMapping("/api/customers")
	public String deleteAll() {
		
		customerService.deleteAll();
		
		return "SUCCESS";
	}
	
	// to delete a particular customer using the id
	@CrossOrigin(origins= "http://localhost:3000")
	@DeleteMapping("/api/customers/{customerId}")
	public Customer deleteCustomer(@PathVariable int customerId) {
		
		Customer customer = customerService.getCustomer(customerId);
		
		if (customer == null)
			throw new CustomerNotFoundException("There is no customer with id: " + customerId);
		
		customerService.deleteCustomer(customerId);
		
		return customer;
	}
}

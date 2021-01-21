package com.techfully.interview.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.techfully.interview.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	private EntityManager entityManager;

	public CustomerDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Customer getCustomer(int id) {

		Session session = entityManager.unwrap(Session.class);
		
		Customer customer = session.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {

		Session session = entityManager.unwrap(Session.class);
		
		Query<Customer> query = session.createQuery("from Customer", Customer.class);
		
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = entityManager.unwrap(Session.class);
		
		Customer customer = getCustomer(id);
		
		session.delete(customer);
	}

	@Override
	public void deleteAll() {

		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("delete from Customer");
		
		query.executeUpdate();
	}

}
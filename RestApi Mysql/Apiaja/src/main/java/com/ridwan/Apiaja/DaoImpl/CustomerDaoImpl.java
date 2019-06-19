/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.DaoImpl;

import com.ridwan.Apiaja.Dao.CustomerDao;
import com.ridwan.Apiaja.Error.errorException;
import com.ridwan.Apiaja.Model.Customer;
import com.ridwan.Apiaja.Repository.CustomerRepository;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Muhammad Ridwan
 */

public class CustomerDaoImpl extends BaseImpl implements CustomerDao{
    
	
	@Autowired
	private CustomerRepository repository;
	

	@Override
	public Customer getById(int id) throws errorException {

		return repository.findOne(id);
	}

	@Override
	public Customer save(Customer customer) throws errorException {

		return repository.save(customer);
	}

	@Override
	public void delete(Customer customer) throws errorException {

		repository.delete(customer);
	}

	@Override
	public List<Customer> getList() throws errorException {
	
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = critB.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root);
		
		TypedQuery<Customer> q = em.createQuery(query);
		
		return q.getResultList();
		
		
	}
	
    
}

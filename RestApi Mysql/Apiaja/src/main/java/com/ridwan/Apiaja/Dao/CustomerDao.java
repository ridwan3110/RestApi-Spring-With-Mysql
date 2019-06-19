/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.Dao;

import com.ridwan.Apiaja.Error.errorException;
import com.ridwan.Apiaja.Model.Customer;
import java.util.List;

/**
 *
 * @author Muhammad Ridwan
 */
public interface CustomerDao {
    Customer getById(int id) throws errorException;
	Customer save(Customer customer) throws errorException;
	void delete(Customer customer) throws errorException;
	
	List<Customer> getList() throws errorException;
	
	
}

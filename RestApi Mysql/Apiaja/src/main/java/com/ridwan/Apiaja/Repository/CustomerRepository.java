/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.Repository;

import com.ridwan.Apiaja.Model.Customer;
import java.io.Serializable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Muhammad Ridwan
 */																			
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
    
}

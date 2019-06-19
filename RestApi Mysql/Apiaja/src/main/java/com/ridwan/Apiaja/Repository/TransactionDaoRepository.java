/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.Repository;

import com.ridwan.Apiaja.Model.Transaction;
import java.io.Serializable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Muhammad Ridwan
 */
public interface TransactionDaoRepository extends PagingAndSortingRepository<Transaction, Integer>{
    
}

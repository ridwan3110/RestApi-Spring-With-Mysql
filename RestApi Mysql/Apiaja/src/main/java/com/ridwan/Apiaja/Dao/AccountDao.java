/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.Dao;

import com.ridwan.Apiaja.Model.Account;
import com.ridwan.Apiaja.Model.Customer;
import java.util.List;

/**
 *
 * @author Muhammad Ridwan
 */
public interface AccountDao {
    Account getById(int id) throws Exception;
    Account save (Account account) throws Exception;
    void delete (Account account) throws Exception;
    Account getById(Customer checkCustomer) throws Exception;
    List<Account> getList() throws Exception;
    List<Account> getListByCustomer(Customer customer) throws Exception;
}

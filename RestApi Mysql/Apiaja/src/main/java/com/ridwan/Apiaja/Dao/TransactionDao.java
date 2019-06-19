/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.Dao;

import com.ridwan.Apiaja.Model.Transaction;
import java.util.List;

/**
 *
 * @author Muhammad Ridwan
 */
public interface TransactionDao {
    Transaction getById(int id) throws Exception;
    Transaction save (Transaction transaction) throws Exception;
    void delete (Transaction transaction) throws Exception;
    List<Transaction> getList();
}

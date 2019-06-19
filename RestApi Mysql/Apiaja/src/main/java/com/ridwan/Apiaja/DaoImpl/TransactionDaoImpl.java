/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.DaoImpl;

import com.ridwan.Apiaja.Dao.TransactionDao;
import com.ridwan.Apiaja.Model.Transaction;
import com.ridwan.Apiaja.Repository.TransactionDaoRepository;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Muhammad Ridwan
 */
public class TransactionDaoImpl extends BaseImpl implements TransactionDao{
    
    @Autowired
    private TransactionDaoRepository repository;

    @Override
    public Transaction getById(int id) throws Exception {
    return repository.findOne(id);
    }

    @Override
    public Transaction save(Transaction transaction) throws Exception {
    return repository.save(transaction);
    }

    @Override
    public void delete(Transaction transaction) throws Exception {
    repository.delete(transaction);
    }

    @Override
    public List<Transaction> getList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
        Root<Transaction> root = query.from(Transaction.class);
        query.select(root);
        TypedQuery<Transaction>  q = em.createQuery(query);
        return q.getResultList();
    }
    
}

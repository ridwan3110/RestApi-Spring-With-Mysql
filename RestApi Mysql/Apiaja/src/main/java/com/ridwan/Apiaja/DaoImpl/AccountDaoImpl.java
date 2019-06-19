/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.DaoImpl;

import com.ridwan.Apiaja.Dao.AccountDao;
import com.ridwan.Apiaja.Dao.CustomerDao;
import com.ridwan.Apiaja.Model.Account;
import com.ridwan.Apiaja.Model.Customer;
import com.ridwan.Apiaja.Repository.AccountDaoRepository;
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
public class AccountDaoImpl extends BaseImpl implements AccountDao{
    
    
    @Autowired
    private AccountDaoRepository repository;

    @Override
    public Account getById(int id) throws Exception {
    return repository.findOne(id);
    }

    @Override
    public Account save(Account account) throws Exception {
    return repository.save(account);
    }

    @Override
    public void delete(Account account) throws Exception {
    repository.delete(account);
    }

    @Override
    public Account getById(Customer checkCustomer) throws Exception {
        return null;
    }

    @Override
    public List<Account> getList() throws Exception {
    CriteriaBuilder  cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        query.select(root);
        TypedQuery<Account> a = em.createQuery(query);
        return  a.getResultList();
    }

    @Override
    public List<Account> getListByCustomer(Customer customer) throws Exception {
    return null;
    }
    
    
    
}

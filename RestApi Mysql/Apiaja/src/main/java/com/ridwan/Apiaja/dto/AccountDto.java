/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.dto;

import com.ridwan.Apiaja.Model.Customer;
import java.util.Date;

/**
 *
 * @author Muhammad Ridwan
 */
public class AccountDto {
    
    private int id;
    private Date opendate;
   private int balance;
   private Customer customer;

    public AccountDto() {}

    public AccountDto(int id, Date opendate, int balance) {
        this.id = id;
        this.opendate = opendate;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
   
   
    
}

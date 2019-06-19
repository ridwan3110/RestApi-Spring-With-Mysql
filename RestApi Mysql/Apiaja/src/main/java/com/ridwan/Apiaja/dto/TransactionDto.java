/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.dto;

import com.ridwan.Apiaja.Model.Account;

/**
 *
 * @author Muhammad Ridwan
 */
public class TransactionDto {
    
    private int id;
    private String type;
    private int amount;
    private String amountsign;
    private Account account;

    public TransactionDto() {
    }

    public TransactionDto(int id, String type, int amount, String amountsign) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.amountsign = amountsign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAmountsign() {
        return amountsign;
    }

    public void setAmountsign(String amountsign) {
        this.amountsign = amountsign;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
}

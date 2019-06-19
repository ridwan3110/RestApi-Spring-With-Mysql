/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.dto;

/**
 *
 * @author Muhammad Ridwan
 */
public class CommonResponse<T> {
    
    private String status;
    private String message;
    private T values;

    public CommonResponse() {
        this.status="00";
        this.status="Success";
    }
    
    
    public CommonResponse(T data) {
        this();
        this.values = data;
    }
    
    
    public CommonResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.values = data;
    }
    
    

    public CommonResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
    
    
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getValues() {
        return values;
    }

    public void setValues(T values) {
        this.values = values;
    }
    
    
    
}

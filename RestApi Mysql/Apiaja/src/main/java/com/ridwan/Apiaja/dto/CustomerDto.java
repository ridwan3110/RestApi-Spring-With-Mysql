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
public class CustomerDto {
    
 
	private int id;
	private String username;
	private String password;
	private String firsname;
	private String lastname;
	private String birthdate;
	private String phonetype;
	private String phonenumber;
	
	
	public CustomerDto() {}
	public CustomerDto(int id, String firsname, String lastName, String birthdate, String username, String password, String phonenumber, String phonetype) {
		this.id = id;
		this.firsname = firsname;
		this.lastname = lastName;
		this.birthdate = birthdate;
		this.username = username;
		this.password = password;
		this.phonetype = phonetype;
		this.phonenumber = phonenumber;

	}
        
        
        
        
        
        
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirsname() {
		return firsname;
	}
	public void setFirsname(String firsname) {
		this.firsname = firsname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getPhonetype() {
		return phonetype;
	}
	public void setPhonetype(String phonetype) {
		this.phonetype = phonetype;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	
    
}

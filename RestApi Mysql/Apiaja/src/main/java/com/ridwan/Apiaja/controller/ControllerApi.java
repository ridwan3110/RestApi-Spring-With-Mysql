/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.controller;

import com.ridwan.Apiaja.Dao.AccountDao;
import com.ridwan.Apiaja.Dao.CustomerDao;
import com.ridwan.Apiaja.Dao.TransactionDao;
import com.ridwan.Apiaja.Error.errorException;
import com.ridwan.Apiaja.Model.Account;
import com.ridwan.Apiaja.Model.Customer;
import com.ridwan.Apiaja.dto.AccountDto;
import com.ridwan.Apiaja.dto.CommonResponse;
import com.ridwan.Apiaja.dto.CustomerDto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Muhammad Ridwan
 */
@RestController
@RequestMapping("/Customer")
@SuppressWarnings("rawtypes")
public class ControllerApi {
	
	private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ControllerApi.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ModelMapper modelmapper; 

	
	@GetMapping(value="/getbyid/{id}")
	public CommonResponse getById(@PathVariable("id") String id) throws errorException {
		LOGGER.info("id : {}", id);
		try {
			Customer customer = customerDao.getById(Integer.parseInt(id));
			
                        if (customer==null){
                         return new CommonResponse("15", "customer tidak ditemukan");   
                        }
                        
			return new CommonResponse<CustomerDto>(modelmapper.map(customer, CustomerDto.class));
		} catch (errorException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		return new CommonResponse("06", e.getMessage());
		}
	}
	
	@GetMapping(value="/{id}/account")
	public CommonResponse getByAccount(@PathVariable("id") String id) throws errorException {
		LOGGER.info("id : {}", id);
		try {
			Account account = accountDao.getById(Integer.parseInt(id));
			
			return new CommonResponse<AccountDto>(modelmapper.map(account, AccountDto.class));
		} catch (errorException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
        
        
        
	
	
	
	
	
	@PostMapping(value="")
	public CommonResponse insert(@RequestBody CustomerDto customerDto) throws errorException {
		try {
			Customer customer = modelmapper.map(customerDto, Customer.class);
			customer.setId(0);
			customer =  customerDao.save(customer);
			
			return new CommonResponse<CustomerDto>(modelmapper.map(customer, CustomerDto.class));
		} catch (errorException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@DeleteMapping(value="/{customer}")
	public CommonResponse delete(@PathVariable("customer") Integer id) throws errorException {
		try {
			Customer checkCustomer = customerDao.getById(id);
			if(checkCustomer == null) {
				return new CommonResponse("06", "customer tidak ditemukan");
			}
			customerDao.delete(checkCustomer);
			return new CommonResponse();
		} catch (errorException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	
	
	@PutMapping(value="")
	public CommonResponse update(@RequestBody CustomerDto customer) throws errorException {
		try {
			Customer checkCustomer = customerDao.getById(customer.getId());
			if(checkCustomer == null) {
				return new CommonResponse("14", "customer tidak ditemukan");
			}
			
			if(customer.getUsername()!=null) {
				checkCustomer.setUsername(customer.getUsername());
			}
			if(customer.getPassword()!=null) {
				checkCustomer.setPassword(customer.getPassword());
			}
			if(customer.getFirsname()!=null) {
				checkCustomer.setFirsname(customer.getFirsname());
			}
			if(customer.getLastname()!=null) {
				checkCustomer.setLastname(customer.getLastname());
			}
			if(customer.getBirthdate()!=null) {
				checkCustomer.setBirthdate(customer.getBirthdate());
			}
			if(customer.getPhonetype()!=null) {
				checkCustomer.setPhonetype(customer.getPhonetype());
			}
			if(customer.getPhonenumber()!=null) {
				checkCustomer.setPhonenumber(customer.getPhonenumber());
			}
			
			checkCustomer =  customerDao.save(checkCustomer);
			
			return new CommonResponse<CustomerDto>(modelmapper.map(checkCustomer, CustomerDto.class));
		} catch (errorException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	
	
	

	@GetMapping(value="/getlist")
	public CommonResponse getList(@RequestParam(name="customer", defaultValue="") String customer) throws errorException{
		try {
			LOGGER.info("customer get list, params : {}", customer);
			List<Customer> customers = customerDao.getList();
                        
                        
		
			return new CommonResponse<List<CustomerDto>>(customers.stream().map(cust -> modelmapper.map(cust, CustomerDto.class)).collect(Collectors.toList()));
		} catch (errorException e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

    
}

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
import com.ridwan.Apiaja.Model.Transaction;
import com.ridwan.Apiaja.dto.AccountDto;
import com.ridwan.Apiaja.dto.CommonResponse;
import com.ridwan.Apiaja.dto.TransactionDto;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@RequestMapping("/Account")
@SuppressWarnings("rawtypes")
public class AccountApi {
    
    @Autowired
    private CustomerDao customerDao;
    
    @Autowired
    private AccountDao  accountDao;
    
    @Autowired
    private TransactionDao  transactionDao;
    
    @Autowired
    private ModelMapper modelMapper;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountApi.class);
    
    
    @GetMapping(value="/{id}/transaksi")
	public CommonResponse getByIdtrans(@PathVariable("id") String id) throws errorException {
		LOGGER.info("id : {}", id);
		try {
			Transaction transaction = transactionDao.getById(Integer.parseInt(id));
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
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
    
    @GetMapping(value="/{id}")
    public CommonResponse getById (@PathVariable("id") String id) throws errorException{
    LOGGER.info("id : {}", id);
        try {
            Account account = accountDao.getById(Integer.parseInt(id));
            return new CommonResponse<AccountDto>(modelMapper.map(account,AccountDto.class));
            
        }catch(errorException e){
            LOGGER.error(e.getMessage());
            return new CommonResponse("01", e.getMessage());
        }
        
        catch (NumberFormatException e){
            LOGGER.error(e.getMessage());
            return new CommonResponse("06", "Parameter Harus Angka");
        }
        
        catch (Exception ex) {
           LOGGER.error(ex.getMessage());
           return new CommonResponse("06",ex.getMessage());
        }
        
}
    
    
    
    @PostMapping(value="")
    public CommonResponse insert (@RequestBody AccountDto accountDto) throws errorException{
        
        try {
            Account account = modelMapper.map(accountDto, Account.class);
        account.setId(0);
            account = accountDao.save(account);
            return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
        }catch (errorException ex) {
           LOGGER.error(ex.getMessage());
           return new CommonResponse("01", ex.getMessage());
            
        }
        
        catch (Exception ex) {
           LOGGER.error(ex.getMessage());
           return new CommonResponse("06", ex.getMessage());
            
        }
        
    }
    
    
    
    @DeleteMapping(value="/{id}")
    public CommonResponse delete (@PathVariable("id") Integer id) throws errorException{
        try {
            Account account = accountDao.getById(id);
            if (account==null){
                return new CommonResponse("100", "Acount Tidak ditemukan");
            }
            accountDao.delete(account);
            return new CommonResponse();
            
        }catch (errorException ex) {
           return new CommonResponse("01", ex.getMessage());
        } 
        
        catch (Exception ex) {
           LOGGER.error(ex.getMessage());
           return new CommonResponse("06", ex.getMessage());
        }
    }
    
    
    
    @PutMapping(value="")
    public CommonResponse update (@RequestBody AccountDto accountDto) throws errorException{
        try {
            Account updatebyId = accountDao.getById(accountDto.getId());
            
            if (accountDto==null){
                return new CommonResponse("01", "Acount dengan id tersebut tidak terdaftar");
            }
            
            if (updatebyId.getOpendate()!=null){
                updatebyId.setOpendate(accountDto.getOpendate());
            }
            
            if (updatebyId.getBalance()!=0){
                updatebyId.setBalance(accountDto.getBalance());
            }
            if (updatebyId.getCustomer()!=null){
                updatebyId.setCustomer(accountDto.getCustomer());
            }
            
            updatebyId = accountDao.save(updatebyId);
            
            return new CommonResponse<AccountDto>(modelMapper.map(updatebyId, AccountDto.class));
        } catch (errorException ex) {
        LOGGER.error(ex.getMessage());
        return new CommonResponse("01", ex.getMessage());
        }
        catch (Exception ex) {
        LOGGER.error(ex.getMessage());
        return new CommonResponse("06", ex.getMessage());
        }
    }
    
    
    @GetMapping(value="/getlist")
    public CommonResponse getlist (@RequestParam(name="account", defaultValue = "") String account) throws errorException{
       
        try {
             LOGGER.info("Account get list, param : {}", account);
            List<Account> accounts = accountDao.getList();
            return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc -> modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
            
        } catch (errorException ex) {
           
            throw ex;
        }
        catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new CommonResponse("06", ex.getMessage());
        }
    }
    
    
    @GetMapping(value = "/accounts")
    public CommonResponse getALL (@RequestParam(name="customer", defaultValue = "") String customer) throws errorException{
        LOGGER.info("Account get list, param :{}", customer);
        
            try {
                 if (!StringUtils.isEmpty(customer)){
                Customer checkCustomer = customerDao.getById(Integer.parseInt(customer));
                if (checkCustomer==null){
                    throw new errorException("Customer Not Found");
                }
                List<Account> accounts = accountDao.getListByCustomer(checkCustomer);
                return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc->modelMapper.map
                    (acc, AccountDto.class)).collect(Collectors.toList()));
            }else {        
                     List<Account> accounts = accountDao.getList();
                     return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc->modelMapper.map
                     (acc, AccountDto.class)).collect(Collectors.toList()));
                 }
            }catch (errorException ex) {  
                throw  ex;
            }catch (NumberFormatException ex) {  
                LOGGER.error(ex.getMessage());
                return new CommonResponse("01", ex.getMessage());
            }
            catch (Exception ex) {  
                LOGGER.error(ex.getMessage());
                return new CommonResponse("06", ex.getMessage());
            }  
         
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.controller;

import com.ridwan.Apiaja.Dao.AccountDao;
import com.ridwan.Apiaja.Dao.TransactionDao;
import com.ridwan.Apiaja.Error.errorException;
import com.ridwan.Apiaja.Model.Transaction;
import com.ridwan.Apiaja.dto.CommonResponse;
import com.ridwan.Apiaja.dto.TransactionDto;
import java.util.List;
import java.util.logging.Level;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/Transaksi")
@SuppressWarnings("rawtypes")
public class TransactionApi {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionApi.class);
    
    @Autowired
    private TransactionDao transactionDao;
    
    @Autowired
    private AccountDao accountDao;
    
    @Autowired
    private ModelMapper modelMapper;
    
    
    @GetMapping(value="/{id}")
    public CommonResponse getById (@PathVariable("id") String id) throws errorException{
        LOGGER.info("id : {}", id);
        
        try {
            Transaction byId = transactionDao.getById(Integer.parseInt(id));
            return new CommonResponse<TransactionDto>(modelMapper.map(byId,TransactionDto.class));
        }catch (errorException ex) {
            LOGGER.error(ex.getMessage());
            return new CommonResponse("01", ex.getMessage());
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
            return new CommonResponse("06", "Parameter Harus Angka");
        }catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new CommonResponse("06", ex.getMessage());
        }
    }
    
    
    @PostMapping(value="")
    public CommonResponse insert (@RequestBody TransactionDto transactionDto) throws errorException{
        try {
            Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
            transaction.setId(0);
            transaction = transactionDao.save(transaction);
            
            return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
        }catch (errorException ex) {
            LOGGER.error(ex.getMessage());
            return new CommonResponse("01", ex.getMessage());
        } 
        catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new CommonResponse("06", ex.getMessage());
        }
        
    }
    
    
    
    @DeleteMapping(value="/{transaction}")
    public CommonResponse delete (@PathVariable("transaction") Integer Id) throws errorException{
        try {
            Transaction byId = transactionDao.getById(Id);
            if (byId==null){
                return new CommonResponse("14", "Transaksi tidak ada");
            }
            transactionDao.delete(byId);
            return new CommonResponse();
            
        }catch (errorException ex) {
         LOGGER.error(ex.getMessage());
         return new CommonResponse("01",ex.getMessage());
        } 
        catch (Exception ex) {
         LOGGER.error(ex.getMessage());
         return new CommonResponse("06",ex.getMessage());
        }
    }
    
    
    @PutMapping(value="")
    public CommonResponse update(@RequestBody TransactionDto transactionDto) throws errorException{
        try {
            Transaction byId = transactionDao.getById(transactionDto.getId());
            if (byId==null){
                return new CommonResponse("14", "Transaksi tidak ditemukan");
            }
            
            if (byId.getType()!=null){
                byId.setType(transactionDto.getType());
            }
            if (byId.getAmount()!=0){
                byId.setAmount(transactionDto.getAmount());
            }
            if (byId.getAmountsign()!=null){
                byId.setAmountsign(transactionDto.getAmountsign());
            }
            
            byId = transactionDao.save(byId);
            return new CommonResponse<TransactionDto>(modelMapper.map(byId, TransactionDto.class));
        } catch (errorException ex) {
        LOGGER.info(ex.getMessage());
        return new CommonResponse("01", ex.getMessage());
        }
        catch (Exception ex) {
        LOGGER.info(ex.getMessage());
        return new CommonResponse("06", ex.getMessage());
        }
    }
     
	 @GetMapping(value="/getlist")
	 public CommonResponse getList(@RequestParam(name="transaction", defaultValue="") String transaction) throws errorException{
			try {
				LOGGER.info("transaction get list, params : {}", transaction);
			
				return new CommonResponse<List<Transaction>>(transactionDao.getList());
			} catch(NumberFormatException e) {
				return new CommonResponse("01", e.getMessage());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				return new CommonResponse("06", e.getMessage());
			}
		}
 
	
 
	
}

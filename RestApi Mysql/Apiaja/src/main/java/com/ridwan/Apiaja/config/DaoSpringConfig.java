/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja.config;

import com.ridwan.Apiaja.Dao.AccountDao;
import com.ridwan.Apiaja.Dao.CustomerDao;
import com.ridwan.Apiaja.Dao.TransactionDao;
import com.ridwan.Apiaja.DaoImpl.AccountDaoImpl;
import com.ridwan.Apiaja.DaoImpl.CustomerDaoImpl;
import com.ridwan.Apiaja.DaoImpl.TransactionDaoImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Muhammad Ridwan
 */
@Configuration
public class DaoSpringConfig {
   @Bean
	public CustomerDao customerDao() {
		return new CustomerDaoImpl();
	}
	@Bean
	public AccountDao accountDao() {
		return new AccountDaoImpl();
	}
	@Bean
	public TransactionDao transaction() {
		return new TransactionDaoImpl();
		
	}
	
	
	@Bean
    public WebMvcConfigurerAdapter corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
                .allowedHeaders("*");
            }
        };
    }
	
	
	 @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }

}

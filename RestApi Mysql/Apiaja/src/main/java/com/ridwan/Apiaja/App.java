/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ridwan.Apiaja;

import com.ridwan.Apiaja.config.DaoSpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Muhammad Ridwan
 */

@Configuration
@SpringBootApplication
@Import({DaoSpringConfig.class})
@ComponentScan({"com.ridwan.Apiaja.controller"})
public class App {
	public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

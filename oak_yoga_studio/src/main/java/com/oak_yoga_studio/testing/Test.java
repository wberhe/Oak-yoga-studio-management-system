/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.testing;

import com.oak_yoga_studio.dao.CustomerDAO;
import com.oak_yoga_studio.domain.Credential;
import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author Weldu
 */
public class Test {

	public static void main(String[] args) {
		
//		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("springconfig.xml");
                ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
		CustomerDAO u =context.getBean("customerDAO",CustomerDAO.class);
		// create 2 users;
                Credential c= new Credential();
                c.setRole("ROLE_ADMIN");
                c.setUserName("senai");
                c.setPassword("senai222");
                
                
                
		User customer= new Customer();
                customer.setFirstName("Senai");
                customer.setEmail("Addagish");
                customer.setEmail("senai@adagish.com");
                customer.setCredential(c);
                
                //u.addCustomer(customer);
		
		
		
	}
	

}


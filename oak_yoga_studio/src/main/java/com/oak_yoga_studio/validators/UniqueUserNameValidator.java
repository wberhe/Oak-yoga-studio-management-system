/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.validators;


import com.oak_yoga_studio.service.ICustomerService;
import com.oak_yoga_studio.validators.annotations.UniqueUserName;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Weldino
 * 
 * In-order to register with us you need to have a unique username
 * Its just our rule...I have a hard time to design this,,Thanks Ahamad for helping
 * 
 */
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    @Autowired
    private  ICustomerService customerService;
    @Override
    public void initialize(UniqueUserName constraintAnnotation) {
    }

    public UniqueUserNameValidator() {
//        Thread.dumpStack();
    }
    

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("Unique:"+ value+":"+ customerService);
      
        boolean result= !customerService.checkUserName(value);
        System.out.println("result: " + result);
        return result;
    }

}

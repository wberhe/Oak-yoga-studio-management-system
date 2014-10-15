package com.oak_yoga_studio.service;

import org.hibernate.validator.constraints.CreditCardNumber;

/**
 *
 * @author Somayeh
 */
public interface ICreditCartService {
    
    public boolean checkValidityOfCreditCard(CreditCardNumber cnum) ;
 
    
}

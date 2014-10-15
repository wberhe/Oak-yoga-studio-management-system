package com.oak_yoga_studio.service.impl;

import com.oak_yoga_studio.service.ICreditCartService;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Somayeh
 */
public class CreditCartServiceImpl implements ICreditCartService {

    public CreditCartServiceImpl() {
    }

    
    @Transactional(propagation = Propagation.REQUIRED)
    
    @Override
    public boolean checkValidityOfCreditCard(CreditCardNumber cnum) {
        
        boolean valid = false;
        //TODO
        return valid;
            
        }
}

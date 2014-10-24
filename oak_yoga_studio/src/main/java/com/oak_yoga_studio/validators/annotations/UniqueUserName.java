/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author Weldu
 */
    
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = com.oak_yoga_studio.validators.UniqueUserNameValidator.class)
@Documented
public @interface UniqueUserName
{
    
    String message() ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    
}


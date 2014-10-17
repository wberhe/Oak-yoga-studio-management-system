/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.validators;


import com.oak_yoga_studio.domain.Credential;
import com.oak_yoga_studio.validators.annotations.FieldMatch;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Weldino
 * 
 * This class validates the match of the password with the confirm password 
 * Note the confirm password is not persisted in the database(transient)
 */

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Credential> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Credential value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = value.getPassword();
            final Object secondObj = value.getConfirmPassword();
//            System.out.println("Tesying:"+(firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj)));
            boolean fieldsMatch = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
            if (false == fieldsMatch) {
                String errorMessage = "Fields do not match"; //change to be property driven

                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(errorMessage).addNode(secondFieldName).addConstraintViolation();

                return false;
            }
            return fieldsMatch;
        } catch (final Exception ignore) {
            // ignore
        }
        return true;
    }
}

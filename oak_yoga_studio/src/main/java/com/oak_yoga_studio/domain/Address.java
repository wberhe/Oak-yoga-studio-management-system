/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Weldu
 */
@Entity
public class Address {
    
    @Id
    @GeneratedValue
    private int id;
    
    @NotBlank
    private String street;
    
    private String state;
    
    private String city;
    
    private String creditCardNumber;
    
    private String ownerNameOnCreditCard;
    
    @Pattern(regexp = "\\d{5}", message = "Zip code must be numeric and either 5 characters.")
    private String zipCode;

    public Address() {
    }

    public Address(String street, String state, String city, String creditCardNumber, String ownerNameOnCreditCard, String zipCode) {
        this.street = street;
        this.state = state;
        this.city = city;
        this.creditCardNumber = creditCardNumber;
        this.ownerNameOnCreditCard = ownerNameOnCreditCard;
        this.zipCode = zipCode;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getOwnerNameOnCreditCard() {
        return ownerNameOnCreditCard;
    }

    public void setOwnerNameOnCreditCard(String ownerNameOnCreditCard) {
        this.ownerNameOnCreditCard = ownerNameOnCreditCard;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.street != null ? this.street.hashCode() : 0);
        hash = 79 * hash + (this.state != null ? this.state.hashCode() : 0);
        hash = 79 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 79 * hash + (this.creditCardNumber != null ? this.creditCardNumber.hashCode() : 0);
        hash = 79 * hash + (this.ownerNameOnCreditCard != null ? this.ownerNameOnCreditCard.hashCode() : 0);
        hash = 79 * hash + (this.zipCode != null ? this.zipCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street)) {
            return false;
        }
        if ((this.state == null) ? (other.state != null) : !this.state.equals(other.state)) {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city)) {
            return false;
        }
        if ((this.creditCardNumber == null) ? (other.creditCardNumber != null) : !this.creditCardNumber.equals(other.creditCardNumber)) {
            return false;
        }
        if ((this.ownerNameOnCreditCard == null) ? (other.ownerNameOnCreditCard != null) : !this.ownerNameOnCreditCard.equals(other.ownerNameOnCreditCard)) {
            return false;
        }
        if ((this.zipCode == null) ? (other.zipCode != null) : !this.zipCode.equals(other.zipCode)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
}

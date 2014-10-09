/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Weldino
 */
@Entity
public class Customer extends User{
    
    @Id
    @GeneratedValue
    private int id;
    
    
    
    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Faculty advisor;
    
    private List<Enrollment> enrollment;

    private List<Waiver> waivers;
    
    private List<Order> orders;
    
    private ShoppingCart shoppingCart;
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
}

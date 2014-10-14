/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oak_yoga_studio.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Weldu
 */
@Entity
public class Customer extends User {

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Faculty advisor;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Waiver> waivers;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;
    

    public Faculty getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Faculty advisor) {
        this.advisor = advisor;
        
    }
    
    //Add Enrollment
    public void addEnrollment(Enrollment enrollment)
    {
        this.enrollments.add(enrollment);
       enrollment.setCustomer(this);
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
    
    //Add waiver
    public void addWaiver(Waiver waiver)
    {
        this.waivers.add(waiver);
        waiver.setCustomer(this);
    }

    public List<Waiver> getWaivers() {
        return waivers;
    }

    public void setWaivers(List<Waiver> waivers) {
        this.waivers = waivers;
    }
    
    //Add Order
    public void addOrder(Order order)
    {
        this.orders.add(order);
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
        if(shoppingCart.getCustomer()==null){
            shoppingCart.setCustomer(this);
        }
    }

}

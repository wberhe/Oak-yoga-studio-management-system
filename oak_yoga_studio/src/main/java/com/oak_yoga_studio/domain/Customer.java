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
 * @author Weldino
 */
@Entity
public class Customer extends User {


    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Faculty advisor;

    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Waiver> waivers;

    @OneToMany(mappedBy = "customer")
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

    public List<Enrollment> getEnrollment() {
        return enrollments;
    }

    public void setEnrollment(List<Enrollment> enrollment) {
        this.enrollments = enrollment;
    }

    public List<Waiver> getWaivers() {
        return waivers;
    }

    public void setWaivers(List<Waiver> waivers) {
        this.waivers = waivers;
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
    }

}

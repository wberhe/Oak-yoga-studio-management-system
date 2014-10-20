package com.oak_yoga_studio.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Somayeh
 */
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ShoppingCartItem> shoppingCartItems;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public ShoppingCart() {
    }

    public ShoppingCart(List<ShoppingCartItem> shoppingCartItems, Customer customer) {
        this.shoppingCartItems = shoppingCartItems;
        this.customer = customer;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //add and remove items from cart
//    public void addItem(ShoppingCartItem item)
//            
//    {
//        this.shoppingCartItems.add(item);
//        item.setShoppingCart(this);
//    }
//    
//    public void removeItem(ShoppingCartItem item)
//    {
//        item.setShoppingCart(null);
//        this.shoppingCartItems.remove(item);
//    }
    /**
     * add item
     * @param item 
     */
    public void addShoppingCartItem(ShoppingCartItem item){
        this.shoppingCartItems.add(item);
        item.setShoppingCart(this);
    }
    /**
     * remove item
     * @return 
     */
    public void removeShoppingCartItem(ShoppingCartItem item){
        if(item.getShoppingCart()!=null){
            item.setShoppingCart(null);
        }
        this.shoppingCartItems.remove(item);
    }
    
    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.shoppingCartItems != null ? this.shoppingCartItems.hashCode() : 0);
        hash = 73 * hash + (this.customer != null ? this.customer.hashCode() : 0);
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
        final ShoppingCart other = (ShoppingCart) obj;
        if (this.shoppingCartItems != other.shoppingCartItems && (this.shoppingCartItems == null || !this.shoppingCartItems.equals(other.shoppingCartItems))) {
            return false;
        }
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        return true;
    }
  
    
}

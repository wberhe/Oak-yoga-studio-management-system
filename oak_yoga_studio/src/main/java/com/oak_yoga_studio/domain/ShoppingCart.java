package com.oak_yoga_studio.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<ShoppingCartItem> shoppingCartItems;

    @OneToOne(mappedBy = "shoppingCart")
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
    public void addItem(ShoppingCartItem item)
            
    {
        this.shoppingCartItems.add(item);
        item.setShoppingCart(this);
    }
    
    public void removeItem(ShoppingCartItem item)
    {
        item.setShoppingCart(null);
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

}

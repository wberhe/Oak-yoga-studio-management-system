
package com.oak_yoga_studio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Somayeh
 */
@Entity
public class ShoppingCartItem {

    @Id
    @GeneratedValue
    private int id;
    
    private int quantity;
    
    @ManyToOne
    @JoinColumn(name="shoppingCart_id")
    private ShoppingCart shoppingCart;
    
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
    
    
    public ShoppingCartItem(int quantity, ShoppingCart shoppingCart, Product product) {
        this.quantity = quantity;
        this.shoppingCart = shoppingCart;
        this.product = product;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.quantity;
        hash = 37 * hash + (this.shoppingCart != null ? this.shoppingCart.hashCode() : 0);
        hash = 37 * hash + (this.product != null ? this.product.hashCode() : 0);
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
        final ShoppingCartItem other = (ShoppingCartItem) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.shoppingCart != other.shoppingCart && (this.shoppingCart == null || !this.shoppingCart.equals(other.shoppingCart))) {
            return false;
        }
        if (this.product != other.product && (this.product == null || !this.product.equals(other.product))) {
            return false;
        }
        return true;
    }
    
    

}

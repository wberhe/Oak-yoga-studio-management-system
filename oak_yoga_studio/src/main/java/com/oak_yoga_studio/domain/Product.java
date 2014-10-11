package com.oak_yoga_studio.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;


/**
 *
 * @author Somayeh
 */
@Entity
public class Product {
    
    @Id
    @GeneratedValue
    private int id;
        
    @NotBlank
    @SafeHtml
    private String name;
    
    @Min(0)
    private double price;
     
    @Column(name="image",columnDefinition="longblob")
    private byte[] image;
    
    @Min(1)
    private int quantity;
    
    @NotBlank
    private String status;

    
    @OneToMany(mappedBy = "product")
    private List<ShoppingCartItem> shoppingCartItems;
        
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }
    
    
}

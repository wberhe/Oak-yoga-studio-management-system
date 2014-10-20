package com.oak_yoga_studio.domain;

import java.util.Arrays;
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
     
    @Column(name="poductImage",columnDefinition="longblob")
    private byte[] image;
    
    @Min(1)
    private int quantity;
    
    
    private String status;

    
    @OneToMany(mappedBy = "product")
    private List<ShoppingCartItem> shoppingCartItems;

    public Product() {
    }
    

    public Product(String name, double price, byte[] image, int quantity, String status, List<ShoppingCartItem> shoppingCartItems) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.status = status;
        this.shoppingCartItems = shoppingCartItems;
    }
        
    
    
        
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 11 * hash + Arrays.hashCode(this.image);
        hash = 11 * hash + this.quantity;
        hash = 11 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 11 * hash + (this.shoppingCartItems != null ? this.shoppingCartItems.hashCode() : 0);
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
        final Product other = (Product) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Arrays.equals(this.image, other.image)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if (this.shoppingCartItems != other.shoppingCartItems && (this.shoppingCartItems == null || !this.shoppingCartItems.equals(other.shoppingCartItems))) {
            return false;
        }
        return true;
    }
    
    
}

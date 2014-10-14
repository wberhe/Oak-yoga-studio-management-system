/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

/**
 *
 * @author Weldu
 */
@Entity
public class Credential {
    
    @Id
    @GeneratedValue
    private int id;
    
    private boolean blocked;
    
    @NotBlank
    private String role;
    
    @NotBlank
    @SafeHtml
    //@UniqueUserName(message = "Username must be unique")
    private String userName;
    
    @NotBlank    
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")
    private String password;
    
    @Transient
    private String confirmPassword;
    
    
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public Credential() {
    }

    public Credential(boolean blocked, String role, String userName, String password, String confirmPassword, User user) {
        this.blocked = blocked;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.user = user;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.role != null ? this.role.hashCode() : 0);
        hash = 17 * hash + (this.userName != null ? this.userName.hashCode() : 0);
        hash = 17 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 17 * hash + (this.confirmPassword != null ? this.confirmPassword.hashCode() : 0);
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
        final Credential other = (Credential) obj;
        if ((this.role == null) ? (other.role != null) : !this.role.equals(other.role)) {
            return false;
        }
        if ((this.userName == null) ? (other.userName != null) : !this.userName.equals(other.userName)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.confirmPassword == null) ? (other.confirmPassword != null) : !this.confirmPassword.equals(other.confirmPassword)) {
            return false;
        }
        return true;
    }
    
    
    
}

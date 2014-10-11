/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.domain;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Weldino
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    
    @Id
    @GeneratedValue
    private int id;
    
    @NotBlank
    @SafeHtml
    private String firstName;
    
    @NotBlank
    @SafeHtml
    private String lastName;
    
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @NotBlank
    @Email
    private String email;
    
    @Column(name="profilepic",columnDefinition="longblob")
    private byte[] profilePicture;
    
    @OneToMany
    @JoinColumn(name="user_id")
    private List<Address> address;
    
   
    
    @OneToOne(mappedBy = "user")
    private Credential credential;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
        if(credential.getUser()==null)
        {
            credential.setUser(this);
        }
    }
    
    public void addAddress(Address add)
    {
        getAddress().add(add);
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 71 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 71 * hash + (this.dateOfBirth != null ? this.dateOfBirth.hashCode() : 0);
        hash = 71 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 71 * hash + Arrays.hashCode(this.profilePicture);
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
        final User other = (User) obj;
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            return false;
        }
        if (this.dateOfBirth != other.dateOfBirth && (this.dateOfBirth == null || !this.dateOfBirth.equals(other.dateOfBirth))) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if (!Arrays.equals(this.profilePicture, other.profilePicture)) {
            return false;
        }
        return true;
    }
    
}

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
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Fetiya
 */


@Entity
public class Waiver {
    
    @Id
    @GeneratedValue
    private int id;
    
    @NotBlank
    private String requestReason;
    
    @NotBlank
    private String remarks;
    
    @NotBlank
    private String status;
    
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name="waiverCourse_id")
    private Course waiverCourse;
    

    @ManyToOne
    @JoinColumn(name="faculty_id")
    private Faculty faculty;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Course getWaiverCourse() {
        return waiverCourse;
    }

    public void setWaiverCourse(Course waiverCourse) {
        this.waiverCourse = waiverCourse;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.requestReason != null ? this.requestReason.hashCode() : 0);
        hash = 89 * hash + (this.remarks != null ? this.remarks.hashCode() : 0);
        hash = 89 * hash + (this.status != null ? this.status.hashCode() : 0);
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
        final Waiver other = (Waiver) obj;
        if ((this.requestReason == null) ? (other.requestReason != null) : !this.requestReason.equals(other.requestReason)) {
            return false;
        }
        if ((this.remarks == null) ? (other.remarks != null) : !this.remarks.equals(other.remarks)) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        return true;
    }
    
    
}

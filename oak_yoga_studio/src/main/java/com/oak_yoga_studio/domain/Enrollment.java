/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Fetiya
 */

@Entity
public class Enrollment {
  
    
    @Id
    @GeneratedValue
    private int id;
    
    private String status;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 83 * hash + (this.enrollmentDate != null ? this.enrollmentDate.hashCode() : 0);
        return hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Enrollment other = (Enrollment) obj;
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if (this.enrollmentDate != other.enrollmentDate && (this.enrollmentDate == null || !this.enrollmentDate.equals(other.enrollmentDate))) {
            return false;
        }
        return true;
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Fetiya
 */

@Entity
public class Course {
    
    @Id
    @GeneratedValue
    private int id;
    
    private String courseNumber;
    
    private String courseName;
    
    private String description;

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.courseNumber != null ? this.courseNumber.hashCode() : 0);
        hash = 53 * hash + (this.courseName != null ? this.courseName.hashCode() : 0);
        hash = 53 * hash + (this.description != null ? this.description.hashCode() : 0);
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
        final Course other = (Course) obj;
        if ((this.courseNumber == null) ? (other.courseNumber != null) : !this.courseNumber.equals(other.courseNumber)) {
            return false;
        }
        if ((this.courseName == null) ? (other.courseName != null) : !this.courseName.equals(other.courseName)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        return true;
    }
    
    
}

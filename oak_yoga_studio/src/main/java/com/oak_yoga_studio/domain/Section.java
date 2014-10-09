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

/**
 *
 * @author Fetiya
 */

@Entity
public class Section {
    
    @Id
    @GeneratedValue
    private int id;
    private String sectionName;
    
    private int capacity;
    
    private int roomNumber;
    
    private int availableSeat;
    
    @ManyToOne
    @JoinColumn(name="professor_id")
    private Faculty professor;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.sectionName != null ? this.sectionName.hashCode() : 0);
        hash = 17 * hash + this.capacity;
        hash = 17 * hash + this.roomNumber;
        hash = 17 * hash + this.availableSeat;
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
        final Section other = (Section) obj;
        if ((this.sectionName == null) ? (other.sectionName != null) : !this.sectionName.equals(other.sectionName)) {
            return false;
        }
        if (this.capacity != other.capacity) {
            return false;
        }
        if (this.roomNumber != other.roomNumber) {
            return false;
        }
        if (this.availableSeat != other.availableSeat) {
            return false;
        }
        return true;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public Faculty getProfessor() {
        return professor;
    }

    public void setProfessor(Faculty professor) {
        this.professor = professor;
    }
    
    
}

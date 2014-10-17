/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.domain;


import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
/**
 *
 * @author Weldu
 */
@Entity
public class Faculty extends User implements Serializable {
        
    private boolean active;
    
   
    @OneToMany(mappedBy = "advisor",cascade =CascadeType.ALL )
    private List<Customer> advisees;
    
    
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Section> sections;
    
    
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Waiver> waivers;

    public Faculty() {
    }

    public Faculty(boolean active, List<Customer> advisees, List<Section> sections, List<Waiver> waivers) {
        this.active = active;
        this.advisees = advisees;
        this.sections = sections;
        this.waivers = waivers;
    }

    

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    //Add advisee
    public void addAdvisee(Customer customer)
    {
       this.advisees.add(customer);
    }

    public List<Customer> getAdvisees() {
        return advisees;
    }

    public void setAdvisees(List<Customer> advisees) {
        this.advisees = advisees;
    }
    //Add and Remove Section
    public void addSection(Section section)
    {
        this.sections.add(section);
    }
    public void removeSection(Section section)
    {
        //this.sections.remove(section);
        getSections().remove(section);
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
    
    public void addWaiver(Waiver waiver)
    {
      this.waivers.add(waiver);
      waiver.setFaculty(this);
    }
    public List<Waiver> getWaivers() {
        return waivers;
    }

    public void setWaivers(List<Waiver> waivers) {
        this.waivers = waivers;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.active ? 1 : 0);
        hash = 83 * hash + (this.advisees != null ? this.advisees.hashCode() : 0);
        hash = 83 * hash + (this.sections != null ? this.sections.hashCode() : 0);
        hash = 83 * hash + (this.waivers != null ? this.waivers.hashCode() : 0);
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
        final Faculty other = (Faculty) obj;
        if (this.active != other.active) {
            return false;
        }
        if (this.advisees != other.advisees && (this.advisees == null || !this.advisees.equals(other.advisees))) {
            return false;
        }
        if (this.sections != other.sections && (this.sections == null || !this.sections.equals(other.sections))) {
            return false;
        }
        if (this.waivers != other.waivers && (this.waivers == null || !this.waivers.equals(other.waivers))) {
            return false;
        }
        return true;
    }
    
    
    
}

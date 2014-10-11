/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Fetiya
 */

@Entity
public class Course {
    
    @Id
    @GeneratedValue
    private int id;
    @NotBlank
    private String courseNumber;
    @NotBlank
    private String courseName;
    
    private String description;
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Section> sections;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course>  prerequisites;
    
    @OneToMany(mappedBy = "waiverCourse", cascade = CascadeType.ALL)
    private List<Waiver> waivers;
    
    
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //Add a single Section
    public void addSection(Section section)
    {
        if(section.getCourse()==null){
            section.setCourse(this);
        }
        this.sections.add(section);
    }
    public void removeSection(Section section){
        if(section.getCourse()!=null){
            section.setCourse(null);
        }
        this.sections.remove(section);
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
    //add prerequisite
    public void addPrerequiste(Course course)
    {
        this.prerequisites.add(course);
    }
    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }
    public void addWaiver(Waiver waiver){
        waiver.setWaiverCourse(this);
        this.waivers.add(waiver);
        
    }
    public List<Waiver> getWaivers() {
        return waivers;
    }

    public void setWaivers(List<Waiver> waivers) {
        this.waivers = waivers;
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

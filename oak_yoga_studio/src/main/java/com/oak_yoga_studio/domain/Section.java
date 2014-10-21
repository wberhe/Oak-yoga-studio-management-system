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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Fetiya
 */

@Entity
public class Section {
    
    @Id
    @GeneratedValue
    private int id;
    
    @NotEmpty
    private String sectionName;
    
    public enum Status {
        INPROGRESS,COMPLETED,OPEN ,CANCELED 
    }
    
    //@Size(min=1, max = 15)
    private int capacity;
    
    //@NotNull
    private String roomNumber;
    
    private Status status;
    
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;
    
    @ManyToOne
    @JoinColumn(name="professor_id")
    private Faculty professor;
    
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Enrollment> enrollements;

    public Section() {
    }

    public Section(String sectionName, int capacity, String roomNumber, Status status, Course course, Faculty professor, List<Enrollment> enrollements) {
        this.sectionName = sectionName;
        this.capacity = capacity;
        this.roomNumber = roomNumber;
        this.status = status;
        this.course = course;
        this.professor = professor;
        this.enrollements = enrollements;
    }    
    

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

    

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Faculty getProfessor() {
        return professor;
    }

    public void setProfessor(Faculty professor) {
        this.professor = professor;
        //professor.addSection(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        //course.addSection(this);
    }
    public void addEnrollment(Enrollment enrollment)
    {
        enrollment.setSection(this);
        this.enrollements.add(enrollment);
    }

    public List<Enrollment> getEnrollements() {
        return enrollements;
    }

    public void setEnrollements(List<Enrollment> enrollements) {
        this.enrollements = enrollements;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.sectionName != null ? this.sectionName.hashCode() : 0);
        hash = 59 * hash + this.capacity;
        hash = 59 * hash + (this.roomNumber != null ? this.roomNumber.hashCode() : 0);
        hash = 59 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 59 * hash + (this.course != null ? this.course.hashCode() : 0);
        hash = 59 * hash + (this.professor != null ? this.professor.hashCode() : 0);
        hash = 59 * hash + (this.enrollements != null ? this.enrollements.hashCode() : 0);
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
        if ((this.roomNumber == null) ? (other.roomNumber != null) : !this.roomNumber.equals(other.roomNumber)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.course != other.course && (this.course == null || !this.course.equals(other.course))) {
            return false;
        }
        if (this.professor != other.professor && (this.professor == null || !this.professor.equals(other.professor))) {
            return false;
        }
        if (this.enrollements != other.enrollements && (this.enrollements == null || !this.enrollements.equals(other.enrollements))) {
            return false;
        }
        return true;
    }

    

    
    
    
    
}

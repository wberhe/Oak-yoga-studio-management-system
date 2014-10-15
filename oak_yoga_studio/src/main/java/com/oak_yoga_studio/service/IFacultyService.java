/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.service;

import com.oak_yoga_studio.domain.Customer;
import com.oak_yoga_studio.domain.Faculty;
import com.oak_yoga_studio.domain.Section;
import com.oak_yoga_studio.domain.Waiver;
import java.util.List;

/**
 *
 * @author Weldino
 */
public interface IFacultyService {
    
    public void addFaculty(Faculty faculty);

    public List<Faculty> getListOfFaculty();

    public void updateFaculty(Faculty faculty);

    public Faculty getFacultyById(int Id);

    public void enableOrdisableFaculty(Faculty faculty);

    public List<Section> getFacultySections(Faculty faculty);

    public void updateWaiverRequest(Waiver waiver);

    public List<Customer> facultyAdvisees(Faculty faculty);
    
}

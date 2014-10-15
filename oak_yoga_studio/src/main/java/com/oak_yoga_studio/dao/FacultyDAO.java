/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oak_yoga_studio.dao;

import com.oak_yoga_studio.domain.Faculty;
import java.util.List;

/**
 *
 * @author Weldino
 */
public interface FacultyDAO {
     public void addFaculty(Faculty faculty);
     
     public void updateFaculty(Faculty faculty);
     
     public Faculty getFaculty(int id);
     
     public List<Faculty> getAllActiveFaculties();
     
     public List<Faculty> getAllFaculties();
}

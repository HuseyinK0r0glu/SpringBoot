package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int theID);

    void deleteById(int theID);

    InstructorDetail findInstructorDetailById(int theID);

    void deleteInstructorDetailById(int theID);
    
}

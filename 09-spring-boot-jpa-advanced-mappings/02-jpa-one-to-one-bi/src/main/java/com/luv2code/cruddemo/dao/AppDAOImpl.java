package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AppDAOImpl implements AppDAO{

    // define field for EntityManager
    private EntityManager entityManager;

    // inject entity manager using constructor injection

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int theID) {
        return entityManager.find(Instructor.class,theID);
    }

    @Override
    @Transactional
    public void deleteById(int theID) {

        // retrieve the instructor
        Instructor instructor = findInstructorById(theID);
        // delete the instructor
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theID) {
        return entityManager.find(InstructorDetail.class,theID);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theID) {

        // retrieve the instuctor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class,theID);

        // remove the associated object reference
        // break bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);

    }

}

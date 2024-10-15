package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void deleteCourseById(int theID) {

        // retrieve the course
        Course course = entityManager.find(Course.class,theID);

        // delete the course
        entityManager.remove(course);

    }

    @Override
    @Transactional
    public void deleteInstructorById(int theID) {

        // find the Instructor
        Instructor instructor = findInstructorById(theID);

        // get the courses
        List<Course> courses = instructor.getCourses();

        // break the association of all courses for the Instructor
        for( Course tempCourse : courses ){
            tempCourse.setInstructor(null);
        }

        // delete the Instructor
        entityManager.remove(instructor);

    }

    @Override
    public Course findCourseById(int theID) {

        Course course = entityManager.find(Course.class,theID);

        return course;
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theID) {

        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                    "select i from Instructor i "
                                        + "JOIN FETCH i.courses "
                                        + "JOIN FETCH i.instructorDetail "
                                        + "WHERE i.id = :data",Instructor.class);
        query.setParameter("data",theID);

        // execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theID) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data",Course.class);

        query.setParameter("data", theID);

        // execute query

        List<Course> courses = query.getResultList();

        return courses;
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

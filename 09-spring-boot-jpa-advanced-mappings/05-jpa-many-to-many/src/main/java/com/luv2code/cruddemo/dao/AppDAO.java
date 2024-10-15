package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {

    void deleteStudentById(int theID);
    void updateStudent(Student student);
    Student findStudentAndCoursesByStudentId(int theID);
    Course findCourseAndStudentsByCourseId(int theID);
    Course findCourseAndReviewsByCourseId(int theID);
    void save(Course course);
    void deleteCourseById(int theID);
    void deleteInstructorById(int theID);
    Course findCourseById(int theID);
    void updateCourse(Course course);
    void update(Instructor instructor);
    Instructor findInstructorByIdJoinFetch(int theID);
    List<Course> findCoursesByInstructorId(int theID);
    void save(Instructor instructor);
    Instructor findInstructorById(int theID);

    InstructorDetail findInstructorDetailById(int theID);

    void deleteInstructorDetailById(int theID);
    
}

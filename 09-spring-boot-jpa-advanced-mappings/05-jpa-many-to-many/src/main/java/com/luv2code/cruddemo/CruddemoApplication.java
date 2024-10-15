package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.hibernate.sql.ast.tree.expression.SqlTuple;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {
			//createInstructor(appDAO);
			//findInstructorById(appDAO);
			//deleteInstructorById(appDAO);
			//findInstructorDetailById(appDAO);
			//deleteInstructorDetailById(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteCourseById(appDAO);
			//createCourseAndReviews(appDAO);
			//retrieveCourseAndReviews(appDAO);
			//deleteCourseAndReviews(appDAO);
			//createCourseAndStudents(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentAndCourses(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteCourseById(appDAO);
			deleteStudentById(appDAO);
		};
	}

	private void deleteStudentById(AppDAO appDAO) {

		int theID = 1;

		System.out.println("Deleting student id: " + theID);

		appDAO.deleteStudentById(theID);

		System.out.println("DONE!");

	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		int theID = 2;
		Student student = appDAO.findStudentAndCoursesByStudentId(theID);

		Course course1 = new Course("Rubik's cube - How to speed Cube");
		Course course2 = new Course("Atari 2600 - Game Development");

		student.addCourse(course1);
		student.addCourse(course2);

		System.out.println("Before merge to db");
		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());

		appDAO.updateStudent(student);

	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int theID = 1;

		Student student = appDAO.findStudentAndCoursesByStudentId(theID);

		System.out.println("Student: " + student);
		System.out.println("Associated courses: " + student.getCourses());

		System.out.println("DONE!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int theID = 10;

		Course course = appDAO.findCourseAndStudentsByCourseId(theID);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		// create a course
		Course course = new Course("Pacman - How to score one million points");

		// create the students
		Student student1 = new Student("John","Doe","john@luv2code.com");
		Student student2 = new Student("Mary","Public","mary@luv2code.com");

		// add students to the course
		course.addStudent(student1);
		course.addStudent(student2);

		// save the course and associated students
		System.out.println("Saving the course: " + course);
		System.out.println("Associated students: " + course.getStudents());
		appDAO.save(course);

		System.out.println("DONE!");

	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theID = 10;

		System.out.println("Deleting course id: " + theID);

		appDAO.deleteCourseById(theID);

		System.out.println("DONE!");

	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		int theId = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println("Course: " + course);

		// print the views
		System.out.println("Reviews: ");
		for(Review tempReview : course.getReviews()){
			System.out.println(tempReview);
		}

		System.out.println("DONE!");

	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a course
		Course course = new Course("Pacman - How to score one million points");

		// add some reviews
		course.addReview(new Review("Great Course ... loved it!"));
		course.addReview(new Review("Cool course, job well done"));
		course.addReview(new Review("What a dumb course, you are an idiot"));

		// save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println("Course: " + course);
		System.out.println("Associated reviews: " + course.getReviews());
		appDAO.save(course);

		System.out.println("DONE!");
	}

	private void deleteCourseById(AppDAO appDAO) {

		int theID = 10;

		System.out.println("Deleting course Id: " + theID);

		appDAO.deleteCourseById(theID);

		System.out.println("DONE!");

	}

	private void updateCourse(AppDAO appDAO) {

		int theID = 10;

		// find the course
		System.out.println("Finding course by Id: " + theID);
		Course course = appDAO.findCourseById(theID);

		// update the course
		System.out.println("Updating course by Id: " + theID);
		course.setTitle("Enjoy the simple things");

		appDAO.updateCourse(course);

		System.out.println("DONE!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theID = 1;

		// find the Instructor
		System.out.println("Finding instructor id: " + theID);
		Instructor instructor = appDAO.findInstructorById(theID);

		// update the Instructor
		System.out.println("Updating instructor id: " + theID);
		instructor.setLastName("TESTER");

		appDAO.update(instructor);

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theID = 1;

		System.out.println("Finding instructor id:" + theID);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theID);

		System.out.println("Instructor: " + instructor);
		System.out.println("associated courses: " + instructor.getCourses());

		System.out.println("DONE!");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theID = 1;
		System.out.println("Finding instructor with ID: " + theID);

		Instructor instructor = appDAO.findInstructorById(theID);

		System.out.println("Instructor:" + instructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id:" + theID);

		List<Course> courses = appDAO.findCoursesByInstructorId(theID);

		// associate the objects

		instructor.setCourses(courses);

		System.out.println("associated courses: " + instructor.getCourses());

	}

	private void findInstructorWithCourses(AppDAO appDAO) {

	int theID = 1;
		System.out.println("Finding instructor with ID: " + theID);

		Instructor instructor = appDAO.findInstructorById(theID);

		System.out.println("Instructor:" + instructor);

		System.out.println("the associated courses:" + instructor.getCourses());

		System.out.println("DONE!");

	}

	private void createInstructorWithCourses(AppDAO appDAO) {


		// create the Instructor
		Instructor tempInstructor = new Instructor("Susan","Public","susan.public@luv2code.com");

		// create the Instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com",
				"Video Games");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		// add courses to Instructor
		tempInstructor.addCourse(tempCourse1);
		tempInstructor.addCourse(tempCourse2);

		// save the Instructor
		// NOTE : This will also save the courses

		System.out.println("Saving Instructor: " + tempInstructor);
		System.out.println("List the courses : " + tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("DONE!");
	}

	private void deleteInstructorDetailById(AppDAO appDAO) {

		int theID = 3;

		System.out.println("Deleting Instructor Detail ID: " + theID);

		appDAO.deleteInstructorDetailById(theID);

		System.out.println("DONE!");

	}

	private void findInstructorDetailById(AppDAO appDAO) {

		int theID = 2;

		System.out.println("Finding Instructor Id: " + theID);

		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theID);

		System.out.println("InstructorDetail: " + instructorDetail);
		System.out.println("The associated instructor only: " + instructorDetail.getInstructor());

		System.out.println("Done!");

	}

	private void deleteInstructorById(AppDAO appDAO) {

		int theID = 1;

		System.out.println("Deleting Instructor by Id: " + theID);
		appDAO.deleteInstructorById(theID);

		System.out.println("DONE!");

	}

	private void findInstructorById(AppDAO appDAO) {

		int theId = 2;
		System.out.println("Finding Instructor Id: " + theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + instructor);
		System.out.println("The associated instructorDetail only: " + instructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {

		/*
		// create the instructor
		Instructor tempInstructor = new Instructor("Chad","Darby","darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube",
				"Luv 2 code !!!!");*/

		// create the instructor
		Instructor tempInstructor = new Instructor("Madhu","Pattel","madhu@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube",
				"Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		// this will also save the detail object because of CascadeType.ALL
		System.out.println("Saving instructor: " + tempInstructor );
		appDAO.save(tempInstructor);

	}

}

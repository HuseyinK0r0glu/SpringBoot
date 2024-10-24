package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
			deleteCourseById(appDAO);
		};
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

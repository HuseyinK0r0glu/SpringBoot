package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.dao.AppDAOImpl;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {
			// createInstructor(appDAO);
			//findInstructorById(appDAO);
			deleteInstructorById(appDAO);

		};
	}
	private void deleteInstructorById(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting Instructor Id: " + theId);

		appDAO.deleteById(theId);

		System.out.println("Done!");

	}

	private void findInstructorById(AppDAO appDAO) {

		int theId = 2;
		System.out.println("Finding Instructor Id: " + theId);

		Instructor instructor = appDAO.findById(theId);

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

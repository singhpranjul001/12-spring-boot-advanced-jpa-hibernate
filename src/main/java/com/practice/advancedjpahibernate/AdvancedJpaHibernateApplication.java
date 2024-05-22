package com.practice.advancedjpahibernate;

import com.practice.advancedjpahibernate.dao.AppDAO;
import com.practice.advancedjpahibernate.entity.Course;
import com.practice.advancedjpahibernate.entity.Instructor;
import com.practice.advancedjpahibernate.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sound.midi.Soundbank;

@SpringBootApplication
public class AdvancedJpaHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedJpaHibernateApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			createInstructorWithCourses(appDAO);
	};
}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor= new Instructor("Rahul", "Yadav", "rahul@gmail.com");

		//create instructor detail
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com/rahul",
				"Gaming");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create some courses
		Course tempCourse1= new Course("Gaming Lesson 1- How to Become an Alpha Gamer.");
		Course tempCourse2= new Course("Gaming Lesson 2- How to Become an Beta Gamer.");
		Course tempCourse3= new Course("Gaming Lesson 3- How to Become an Sigma Gamer.");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		tempInstructor.add(tempCourse3);

		//save the instructor
		//Note: This will also save the courses due to CascadeType.PERSIST
		System.out.println("Saving the instructor: "+ tempInstructor);
		System.out.println("The Courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done.");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting the instructor detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done.");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		//get the instructor detail object
		int theId=3;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		//print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		//print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
		System.out.println("Done");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId=2;
		System.out.println("Deleting the instructor id: "+ theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done.");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId=3;
		System.out.println("Finding the Instructor" + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associate instructorDetails only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		/*
		//create the instructor
		Instructor tempInstructor= new Instructor("Pranjul", "Singh", "pranjul.singh@gmail.com");

		//create instructor detail
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com/pranjul_singh",
				"Chess");
				*/

		Instructor tempInstructor= new Instructor("Ritika", "Roy", "royritika@gmail.com");

		//create instructor detail
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com/ritika",
				"Travelling");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		//NOTE: This will also save the details object because CascadeType.ALL

		System.out.println("Saving the instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done");
	}
}

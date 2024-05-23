package com.practice.advancedjpahibernate;

import com.practice.advancedjpahibernate.dao.AppDAO;
import com.practice.advancedjpahibernate.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sound.midi.Soundbank;
import java.util.List;

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
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorAndCourseWithJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructor(appDAO);
			//deleteCourse(appDAO);

			//createCourseAndReviews(appDAO);
			//retreiveCourseAndReviews(appDAO);
			//deleteCourseAndReviews(appDAO);

			//createCourseAndStudent(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentAndCourse(appDAO);
			//addMoreCoursesForStudents(appDAO);
			//deleteCourse(appDAO);
			deleteStudent(appDAO);
	};
}

	private void deleteStudent(AppDAO appDAO) {
		int theId=2;
		appDAO.deleteStudentById(theId);
		System.out.println("Done.");
	}

	private void addMoreCoursesForStudents(AppDAO appDAO) {
		int theId=2;
		Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

		//create more courses
		Course tempCourse1 = new Course("How to Speed Cube.");
		Course tempCourse2 = new Course("How to become a great developer.");

		//add courses to the student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);
		System.out.println("Update student: " + tempStudent);
		System.out.println("associated Courses: " + tempStudent.getCourses());

		appDAO.update(tempStudent);
		System.out.println("Done.");
	}

	private void findStudentAndCourse(AppDAO appDAO) {
		int theId=1;
		Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);
		System.out.println(tempStudent);
		System.out.println(tempStudent.getCourses());
		System.out.println("Done");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId=10;
		Course tempCourse = appDAO.findCourseAndStudentByCourseId(theId);
		System.out.println("tempCourse: " + tempCourse);
		System.out.println(tempCourse.getStudents());
		System.out.println("Done.");
	}

	private void createCourseAndStudent(AppDAO appDAO) {

		//create a course
		Course tempCourse = new Course("How to win friends and influence people.");

		//create the students
		Student tempStudent1 = new Student("Pranjul", "Singh", "pranjul@gmail.com");
		Student tempStudent2 = new Student("Prateek", "Patel", "prateek@gmail.com");

		//add the students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		//save the courses and associated students
		System.out.println("Saving the courses: " + tempCourse);
		System.out.println("the associated Students: " + tempCourse.getStudents());

		appDAO.save(tempCourse);
		System.out.println("Done.");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId=10;

		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done.");
	}

	private void retreiveCourseAndReviews(AppDAO appDAO) {

		//get the course and reviews
		int theId=10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		//print the course
		System.out.println(tempCourse);

		//print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done.");
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		//create a course
		Course tempCourse = new Course("How to learn Spring Boot.");

		//add some reviews
		tempCourse.addReview(new Review("Great course, learned a lot."));
		tempCourse.addReview(new Review("Average course, could be better."));
		tempCourse.addReview(new Review("Lovely!! Though could have been better."));

		//save the course...and leverage the cascade ALL
		System.out.println("Saving the courses.");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done.");

	}

	private void deleteCourse(AppDAO appDAO) {
		int theId=10;
		appDAO.deleteCourseById(theId);
		System.out.println("Done.");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId=10;
		Course tempCourse = appDAO.findCourseById(theId);
		tempCourse.setTitle("How to cultivate winner mindset.");
		appDAO.updateCourse(tempCourse);
		System.out.println("Done");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding the instructor with id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		tempInstructor.setLastName("Gotham");
		appDAO.update(tempInstructor);
		System.out.println("Done.");
	}

	private void findInstructorAndCourseWithJoinFetch(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding the Instructor: " + theId);

		Instructor tempInstructor = appDAO.findInstructorAndCourseWithJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theId=1;
		System.out.println("Finding the Instructor id: " + theId);

		Instructor tempInstructor= appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		//find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(courses);
		System.out.println("the associated Courses: " + tempInstructor.getCourses());
		System.out.println("Done.");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		//find the instructor
		int theId=1;
		Instructor tempInstructor= appDAO.findInstructorById(theId);
		//finding courses for instructor
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		System.out.println(tempInstructor.getCourses());
		System.out.println("Done.");
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
		int theId=1;
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

package com.practice.advancedjpahibernate.dao;

import com.practice.advancedjpahibernate.entity.Course;
import com.practice.advancedjpahibernate.entity.Instructor;
import com.practice.advancedjpahibernate.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    //define a field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        //retrieve the instructor
        Instructor tempInstructor=entityManager.find(Instructor.class,theId);

        //get the courses
        List<Course> courses = tempInstructor.getCourses();

        //break the association with courses
        for (Course course: courses) {
            course.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        //find the instructor detail by id
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        //remove the associated bidirectional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        //delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        //create a query
        TypedQuery<Course> query=entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);

        query.setParameter("data", theId);

        //retrieve the result
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorAndCourseWithJoinFetch(int theId) {
        //create a query
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i "
                                                        + "JOIN FETCH i.courses "
                                                        + "JOIN FETCH i.instructorDetail "
                                                        + "where i.id = :data", Instructor.class);

        query.setParameter("data", theId);

        //execute the query
        Instructor instructor=query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        //retrieve the course
        Course tempCourse = entityManager.find(Course.class, theId);

        //remove the course
        entityManager.remove(tempCourse);

    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        //create the query
        TypedQuery<Course> query = entityManager.createQuery("" +
                "select c from Course c "
                + "JOIN FETCH c.reviews "
                +"where c.id=:data", Course.class);

        query.setParameter("data", theId);

        //execute the query
        Course course = query.getSingleResult();

        return course;
    }
}

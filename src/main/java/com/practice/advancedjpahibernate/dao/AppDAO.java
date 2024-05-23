package com.practice.advancedjpahibernate.dao;

import com.practice.advancedjpahibernate.entity.Course;
import com.practice.advancedjpahibernate.entity.Instructor;
import com.practice.advancedjpahibernate.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorAndCourseWithJoinFetch(int theId);
    void update(Instructor tempInstructor);
    void updateCourse(Course tempCourse);
    Course findCourseById(int theId);
    void deleteCourseById(int theId);
}

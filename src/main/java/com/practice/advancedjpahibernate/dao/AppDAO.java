package com.practice.advancedjpahibernate.dao;

import com.practice.advancedjpahibernate.entity.Instructor;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
}

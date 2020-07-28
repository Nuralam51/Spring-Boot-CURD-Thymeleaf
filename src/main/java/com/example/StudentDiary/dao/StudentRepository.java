package com.example.StudentDiary.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentDiary.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}

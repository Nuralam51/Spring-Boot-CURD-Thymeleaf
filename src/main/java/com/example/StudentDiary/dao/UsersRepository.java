package com.example.StudentDiary.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentDiary.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByUsername(String username);
}

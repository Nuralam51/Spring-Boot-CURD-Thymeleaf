package com.example.StudentDiary.service;

import java.util.List;

import com.example.StudentDiary.entity.Users;

public interface UsersService {
	
	public List<Users> findAll();
	public Users findById(int theId);
	public void save(Users theEmployee);
	public void deleteById(int theId);
}

package com.example.StudentDiary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentDiary.dao.UsersRepository;
import com.example.StudentDiary.entity.Users;

@Service
public class UsersServiceImpl implements UsersService{

	private UsersRepository usersRepository;

	@Autowired
	public UsersServiceImpl(UsersRepository theEmployeeRepository) {
		usersRepository = theEmployeeRepository;
	}

	@Override
	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	@Override
	public Users findById(int theId) {
		Optional<Users> result = usersRepository.findById(theId);

		Users theEmployee = null;
		if(result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			throw new RuntimeException("Didn't find employee id "+theId);
		}
		return theEmployee;
	}

	@Override
	public void save(Users theEmployee) {
		usersRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		usersRepository.deleteById(theId);
	}

}

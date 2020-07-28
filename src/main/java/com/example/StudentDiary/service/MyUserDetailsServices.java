package com.example.StudentDiary.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.StudentDiary.dao.UsersRepository;
import com.example.StudentDiary.entity.MyUserDetails;
import com.example.StudentDiary.entity.Users;

@Service
public class MyUserDetailsServices implements UserDetailsService {

	@Autowired
	public UsersRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user = userRepository.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return user.map(MyUserDetails::new).get();
	}


}

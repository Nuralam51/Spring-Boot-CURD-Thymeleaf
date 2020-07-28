package com.example.StudentDiary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public UserDetailsService userDetailsService;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override 
	protected void configure(HttpSecurity http) throws Exception { 
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/login", "/register", "/forget-password", "/css/**").permitAll()
		.antMatchers("/student/add/**").hasRole("ADMIN")
		.antMatchers("/student/edit/**").hasRole("ADMIN")
		.antMatchers("/student/delete/**").hasRole("ADMIN")
		.anyRequest().authenticated() 
		.and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/student/list", true)
		.and()
		.logout().logoutSuccessUrl("/login")
		.permitAll(); 
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
